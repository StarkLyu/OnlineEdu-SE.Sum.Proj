package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.ApplyRepository;
import com.se231.onlineedu.repository.ResourceRepository;
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


    @Autowired
    private CoursePrototypeRepository coursePrototypeRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;


    @Override
    public CoursePrototype createCourse(CreateCoursePrototypeApplicationForm form, Long userId) throws Exception{
        CoursePrototype coursePrototype=new CoursePrototype();
        coursePrototype.setTitle(form.getTitle());
        coursePrototype.setDescription(form.getDescription());
        coursePrototype.setState(CoursePrototypeState.NOT_DECIDE);
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        coursePrototype.setUser(user);
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
        return applyRepository.save(apply);
    }

    @Override
    public CoursePrototype saveResource(Long coursePrototypeId, Resource resource) throws Exception {
        Resource resourceSaved = resourceRepository.save(resource);
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(coursePrototypeId).orElseThrow(()->new Exception("No corresponding course"));
        coursePrototype.getResources().add(resourceSaved);
        return coursePrototypeRepository.save(coursePrototype);
    }
}
