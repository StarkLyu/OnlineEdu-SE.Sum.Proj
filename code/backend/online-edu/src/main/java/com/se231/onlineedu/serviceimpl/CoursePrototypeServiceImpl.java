package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.ApplyRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.CoursePrototypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of course service interface
 *
 * contains the main service logic of backend about course.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
@Service
public class CoursePrototypeServiceImpl implements CoursePrototypeService {

    private CoursePrototypeRepository coursePrototypeRepository;

    private ApplyRepository applyRepository;

    private UserRepository userRepository;

    private static final String approval = ApplyState.APPROVAL.toString().toLowerCase();

    @Autowired
    public CoursePrototypeServiceImpl(CoursePrototypeRepository coursePrototypeRepository,
                                      ApplyRepository applyRepository,
                                      UserRepository userRepository){
        this.coursePrototypeRepository=coursePrototypeRepository;
        this.applyRepository=applyRepository;
        this.userRepository=userRepository;
    }

    @Override
    public CoursePrototype createCourse(CreateCoursePrototypeApplicationForm form, Long userId) throws Exception{
        CoursePrototype coursePrototype=new CoursePrototype();
        coursePrototype.setTitle(form.getTitle());
        coursePrototype.setDescription(form.getDescription());
        coursePrototype.setState(CoursePrototypeState.NOT_DECIDE);
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        List<User> userList = new ArrayList<>();
        userList.add(user);
        coursePrototype.setUser(userList);
        return coursePrototypeRepository.save(coursePrototype);
    }

    @Override
    public Apply applyForCourse(Long courseId,Long userId) throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        ApplyPrimaryKey id = new ApplyPrimaryKey(user,coursePrototype);
        Apply application;
        if(!applyRepository.existsById(id)){
            application=new Apply(id);
            return applyRepository.save(application);
        }
        else {
            return applyRepository.getOne(id);
        }
    }

    @Override
    public CoursePrototype decideCreateCourse(Long coursePrototypeId,String decision)throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(coursePrototypeId).orElseThrow(()->new Exception("No corresponding course"));
        switch (decision) {
            case "approval":
                coursePrototype.setState(CoursePrototypeState.USING);
                break;
            case "disapproval":
                coursePrototype.setState(CoursePrototypeState.DENIAL);
                break;
            default:
                coursePrototype.setState(CoursePrototypeState.NOT_DECIDE);
                throw new RuntimeException("Unknown decision");
        }
        return coursePrototypeRepository.save(coursePrototype);
    }

    @Override
    public Apply decideUseCourse(Long courseId,Long applicantId,String decision)throws Exception{

        CoursePrototype coursePrototype = coursePrototypeRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        User user = userRepository.findById(applicantId).orElseThrow(()->new Exception("No corresponding user"));
        ApplyPrimaryKey applyPrimaryKey=new ApplyPrimaryKey(user,coursePrototype);
        Apply apply = applyRepository.findById(applyPrimaryKey).orElseThrow(()->new Exception("No corresponding application"));
        apply.setApplyState(ApplyState.valueOf(decision.toUpperCase()));
        if(approval.equals(decision)){
            coursePrototype.getUsers().add(user);
            coursePrototypeRepository.save(coursePrototype);
        }
        return applyRepository.save(apply);
    }

    @Override
    public List<CoursePrototype> getAllCoursePrototype() {
        return coursePrototypeRepository.findAll();
    }
}
