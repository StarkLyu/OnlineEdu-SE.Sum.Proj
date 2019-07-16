package com.se231.onlineedu.serviceimpl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.se231.onlineedu.message.request.CourseApplicationForm;
import com.se231.onlineedu.message.request.TimeSlotForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * the implementation class of course service
 *
 * to finish the service logic of operation related to course
 *
 * @author Zhe Li
 *
 * @date 2019/7/4
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CoursePrototypeRepository coursePrototypeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Course applyToStartCourse(CourseApplicationForm form, Long prototypeId, Long userId) throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(prototypeId).orElseThrow(()->new Exception("No corresponding course"));
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        if(form.getEndDate().before(form.getStartDate())) {
            throw new RuntimeException("end date comes before start date!");
        }
        Course course=new Course(form.getStartDate(),form.getEndDate(),CourseState.APPLYING,coursePrototype,user);
        course.setCourseTitle(form.getCourseTitle());
        course.setLocation(form.getLocation());
        course.setTimeSlots(handleTimeSlots(form.getTimeSlots()));
        return courseRepository.save(course);
    }

    @Override
    public Course examineStartCourseApplication(Long courseId,String decision)throws Exception{
        Course course = courseRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        switch (decision) {
            case "approval":
                if(course.getStartDate().before(new Date())) {
                    course.setState(CourseState.TEACHING);
                }
                else {
                    course.setState(CourseState.READY_TO_START);
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
    public List<Course> pickCourse(Long userId,Long courseId)throws Exception{
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        Course course = courseRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        if(user.getCourses().contains(course)){
            throw new RuntimeException("You have picked this course");
        }
        user.getCourses().add(course);
        userRepository.save(user);
        return user.getCourses();
    }

    @Override
    public Set<User> getStudentsList(Long courseId) throws Exception {
        Course course=courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("No corresponding course"));
        return course.getStudents();
    }

    @Override
    public Course getCourseInfo(Long courseId) throws Exception {
        return courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("No corresponding course"));
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Boolean checkIfUserPick(Long courseId, Long userId) throws Exception {
        User user=userRepository.getOne(userId);
        Course course = courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("No corresponding course"));
        return user.getCourses().contains(course);
    }

    @Override
    public Course modifyCourseInfo(Long courseId, CourseApplicationForm form, Long userId) throws Exception {
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        Course course = courseRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        if(user.getRoles().contains(roleRepository.getByRole(RoleType.ROLE_ADMIN))){
            course.setTimeSlots(handleTimeSlots(form.getTimeSlots()));
            course.setLocation(form.getLocation());
            course.setCourseTitle(form.getCourseTitle());
            course.setStartDate(course.getStartDate());
            course.setEndDate(course.getEndDate());
            return courseRepository.save(course);
        }
        else {
            throw new RuntimeException("You don't have authority to modify this course.");
        }
    }

    private List<TimeSlot> handleTimeSlots(List<TimeSlotForm> slotFormList){
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (TimeSlotForm slotForm:slotFormList) {
            TimeSlot slot = timeSlotRepository.findByDayAndAndStartAndAndEnd(WeekDay.values()[slotForm.getDay()]
                    , Time.valueOf(slotForm.getStart()),Time.valueOf(slotForm.getEnd())).orElse(new TimeSlot(slotForm));
            timeSlots.add(timeSlotRepository.save(slot));
        }
        return timeSlots;
    }
}
