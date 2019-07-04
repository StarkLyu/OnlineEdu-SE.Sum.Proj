package com.se231.onlineedu.serviceimpl;

import java.util.Date;
import java.util.List;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.model.CourseState;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.CourseRepository;
import com.se231.onlineedu.repository.UserRepository;
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

    private CoursePrototypeRepository coursePrototypeRepository;

    private CourseRepository courseRepository;

    private UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CoursePrototypeRepository coursePrototypeRepository,
                             CourseRepository courseRepository,UserRepository userRepository){
        this.userRepository=userRepository;
        this.coursePrototypeRepository=coursePrototypeRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public Course applyToStartCourse(Long prototypeId, Date startDate, Date endDate, Long userId) throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(prototypeId).orElseThrow(()->new Exception("No corresponding course"));
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        if(endDate.before(startDate)) {
            throw new Exception("end date comes before start date!");
        }
        Course course=new Course(startDate,endDate,CourseState.APPLYING,coursePrototype,user);
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
        user.getCourses().add(course);
        userRepository.save(user);
        return user.getCourses();
    }
}
