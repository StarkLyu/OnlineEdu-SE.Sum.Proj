package com.se231.onlineedu.serviceimpl;

import java.sql.Time;
import java.util.*;
import com.alibaba.fastjson.JSON;
import com.se231.onlineedu.exception.CoursePrototypeUnavailableException;
import com.se231.onlineedu.exception.EndBeforeStartException;
import com.se231.onlineedu.exception.IdentityException;
import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.message.request.CourseApplicationForm;
import com.se231.onlineedu.message.request.SignInCourseForm;
import com.se231.onlineedu.message.request.TimeSlotForm;
import com.se231.onlineedu.message.response.*;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.scheduler.SchedulerHandler;
import com.se231.onlineedu.service.CoursePrototypeService;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.UserService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * the implementation class of course service
 * <p>
 * to finish the service logic of operation related to course
 *
 * @author Zhe Li
 * @date 2019/7/4
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CoursePrototypeService coursePrototypeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private LearnRepository learnRepository;

    @Autowired
    private SignInRepository signInRepository;

    @Override
    public Course applyToStartCourse(CourseApplicationForm form, Long prototypeId, Long userId) {
        CoursePrototype coursePrototype = coursePrototypeService.getCoursePrototypeInfo(prototypeId);
        if(coursePrototype.getState() != CoursePrototypeState.USING){
            throw new CoursePrototypeUnavailableException();
        }
        User user = userService.getUserInfo(userId);
        if (form.getEndDate().before(form.getStartDate())) {
            throw new EndBeforeStartException("开始时间晚于结束时间。");
        }

        Course course = new Course(form.getStartDate(), form.getEndDate(), CourseState.APPLYING, coursePrototype, user);
        course.setCourseTitle(form.getCourseTitle());
        course.setLocation(form.getLocation());
        if(form.getTimeSlots()!=null&&!form.getTimeSlots().isEmpty()) {
            course.setTimeSlots(handleTimeSlots(form.getTimeSlots()));
        }
        return courseRepository.save(course);
    }

    @Override
    public Course examineStartCourseApplication(Long courseId, String decision) {
        Course course = getCourseInfo(courseId);
        switch (decision) {
            case "approval":
                if (course.getStartDate().before(new Date())) {
                    course.setState(CourseState.TEACHING);
                } else {
                    try{
                        course.setState(CourseState.READY_TO_START);
                        SchedulerHandler.setCourseState(courseId,"TEACHING",course.getStartDate());
                    }catch(SchedulerException e){
                        e.printStackTrace();
                    }
                }
                try {
                    SchedulerHandler.setCourseState(courseId, "FINISHED", course.getEndDate());
                } catch (SchedulerException e){
                    e.printStackTrace();
                }
                break;
            case "disapproval":
                course.setState(CourseState.NOT_PASS);
                break;
            default:
                course.setState(CourseState.APPLYING);
                break;
        }
        return courseRepository.save(course);
    }

    @Override
    public Learn pickCourse(Long userId, Long courseId) {
        User user = userService.getUserInfo(userId);
        Course course = getCourseInfo(courseId);
        Learn learn = new Learn();
        LearnPrimaryKey learnPrimaryKey = new LearnPrimaryKey(user,course);
        learn.setLearnPrimaryKey(learnPrimaryKey);
        return learnRepository.save(learn);
    }

    @Override
    public List<User> getStudentsList(Long courseId) {
        Course course = getCourseInfo(courseId);
        return course.getStudents();
    }

    @Override
    public Course getCourseInfo(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("课程不存在"));
    }

    @Override
    public CourseWithIdentity getCourseInfoWithIdentity(Long courseId, Long userId) {
        CourseWithIdentity courseWithIdentity = new CourseWithIdentity();
        Course course = getCourseInfo(courseId);
        courseWithIdentity.setCourse(course);

        if (userInUserList(course.getStudents(), userId).isPresent()) {
            courseWithIdentity.setIdentity(Identity.STUDENT);
            return courseWithIdentity;
        }

        if (userInUserList(course.getTeacherAssistants(), userId).isPresent()) {
            courseWithIdentity.setIdentity(Identity.TEACHER_ASSISTANT);
            return courseWithIdentity;
        }

        if (course.getTeacher().getId().equals(userId)) {
            courseWithIdentity.setIdentity(Identity.TEACHER);
            return courseWithIdentity;
        }
        courseWithIdentity.setIdentity(Identity.VISITOR);
        return courseWithIdentity;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourseAvatar(String avatarUrl, Long id) {
        Course course = getCourseInfo(id);
        course.setAvatarUrl(avatarUrl);
        return courseRepository.save(course);
    }

    @Override
    public List<String> getTeacherAssistantAndTeacherEmail(Long id) {
        Course course = getCourseInfo(id);
        List<String> emails = new ArrayList<>();
        emails.add(course.getTeacher().getEmail());
        for (User ta : course.getTeacherAssistants()) {
            emails.add(ta.getEmail());
        }
        return emails;
    }

    @Override
    public Boolean checkIfUserPick(Long courseId, Long userId) {
        User user = userService.getUserInfo(userId);
        Course course = getCourseInfo(courseId);
        return user.getLearnCourses().contains(course);
    }

    @Override
    public Course modifyCourseInfo(Long courseId, CourseApplicationForm form) {
        Course course = getCourseInfo(courseId);
        if(form.getTimeSlots()!=null&&!form.getTimeSlots().isEmpty()) {
            System.out.println(JSON.toJSONString(form));
            course.setTimeSlots(handleTimeSlots(form.getTimeSlots()));
        }
        course.setLocation(form.getLocation());
        course.setCourseTitle(form.getCourseTitle());
        course.setStartDate(course.getStartDate());
        course.setEndDate(course.getEndDate());
        return courseRepository.save(course);
    }

    private List<TimeSlot> handleTimeSlots(List<TimeSlotForm> slotFormList) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (TimeSlotForm slotForm : slotFormList) {
            TimeSlot slot = timeSlotRepository.findByDayAndStartAndEnd(WeekDay.values()[slotForm.getDay()]
                    , Time.valueOf(slotForm.getStart()+":00"),Time.valueOf(slotForm.getEnd()+":00"))
                    .orElse(new TimeSlot(slotForm));
            timeSlots.add(slot);
        }
        return timeSlots;
    }

    private Optional<User> userInUserList(List<User> users, Long userId) {
        Optional<User> userOptional;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                userOptional = Optional.of(user);
                return userOptional;
            }
        }
        return Optional.empty();
    }

    @Override
    public Course selectTeacherAssistant(Long id, Long userId) {
        Course course = getCourseInfo(id);
        User user = userInUserList(course.getStudents(), userId).orElseThrow(() -> new IdentityException("请助教先加入课程"));
        LearnPrimaryKey learnPrimaryKey = new LearnPrimaryKey(user, course);
        Learn learn = new Learn();
        learn.setLearnPrimaryKey(learnPrimaryKey);
        course.getLearns().remove(learn);
        course.getTeacherAssistants().add(user);
        return courseRepository.save(course);
    }

    @Override
    public Course saveSignIn(Long id, SignInCourseForm signInForm) {
        Course course = getCourseInfo(id);
        course.getSignIns().add(signInRepository.save(new SignIn(course, signInForm.getSignInNo(), signInForm.getStartDate(), signInForm.getEndDate())));
        return courseRepository.save(course);
    }

    @Override
    public Course setState(Long courseId,String state) {
        Course course = getCourseInfo(courseId);
        course.setState(CourseState.valueOf(state));
        return courseRepository.save(course);
    }

    @Override
    public Course saveNotice(Long id, Notice notice){
        Course course = getCourseInfo(id);
        notice.setIssueDate(new Date());
        NoticePrimaryKey noticePrimaryKey = new NoticePrimaryKey();
        noticePrimaryKey.setCourse(course);
        noticePrimaryKey.setNoticeNo(noticeRepository.currentNoticeNo(id).orElse(0) + 1);
        notice.setNoticePrimaryKey(noticePrimaryKey);
        Notice found = noticeRepository.save(notice);
        course.getNotices().add(found);
        return courseRepository.save(course);
    }

    @Override
    public GradeTable getGrade(Long courseId) {
        Course course = getCourseInfo(courseId);
        GradeTable gradeTable = new GradeTable();
        gradeTable.setCourse(course);
        List<StudentAndGrade> scoreMap = new ArrayList<>();
        course.getLearns().forEach(learn -> {
            scoreMap.add(new StudentAndGrade(learn.getLearnPrimaryKey().getStudent(),learn.getGrade()));
        });
        gradeTable.setScoreMap(scoreMap);
        return gradeTable;
    }
}
