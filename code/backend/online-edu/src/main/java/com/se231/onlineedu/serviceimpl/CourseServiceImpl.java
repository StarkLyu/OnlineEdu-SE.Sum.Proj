package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
import com.se231.onlineedu.message.response.ApplyResponse;
import com.se231.onlineedu.model.ApplyPrimaryKey;
import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.model.Apply;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.ApplyRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private CoursePrototypeRepository coursePrototypeRepository;

    private ApplyRepository applyRepository;

    private UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CoursePrototypeRepository coursePrototypeRepository,
                             ApplyRepository applyRepository,
                             UserRepository userRepository){
        this.coursePrototypeRepository=coursePrototypeRepository;
        this.applyRepository=applyRepository;
        this.userRepository=userRepository;
    }

    @Override
    public ApplyResponse createCourse(CreateCourseApplicationForm form, Long userId) throws Exception{
        CoursePrototype coursePrototype=new CoursePrototype();
        coursePrototype.setTitle(form.getTitle());
        coursePrototype.setDescription(form.getDescription());
        coursePrototype.setState(0);
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        coursePrototype.setUser(user);
        coursePrototypeRepository.save(coursePrototype);
        return new ApplyResponse(1);
    }

    @Override
    public ApplyResponse applyForCourse(Long courseId,Long userId) throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user!"));
        ApplyPrimaryKey id = new ApplyPrimaryKey(user,coursePrototype);
        Apply application;
        if(!applyRepository.existsById(id)){
            application=new Apply(id);
            applyRepository.save(application);
            return new ApplyResponse(1);
        }
        else {
            return new ApplyResponse(0);
        }
    }

    @Override
    public void decideCreateCourse(Long coursePrototypeId,int decision)throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(coursePrototypeId).orElseThrow(()->new Exception("No corresponding course"));        coursePrototype.setState(1);
        coursePrototype.setState(decision);
        coursePrototypeRepository.save(coursePrototype);
    }

    @Override
    public void decideUseCourse(Long courseId,Long applicantId,int decision)throws Exception{

        CoursePrototype coursePrototype = coursePrototypeRepository.findById(courseId).orElseThrow(()->new Exception("No corresponding course"));
        User user = userRepository.findById(applicantId).orElseThrow(()->new Exception("No corresponding user"));
        ApplyPrimaryKey applyPrimaryKey=new ApplyPrimaryKey(user,coursePrototype);
        Apply apply =
                applyRepository.findById(applyPrimaryKey).orElseThrow(()->new Exception("No corresponding application"));

        apply.setApplyState(decision);
        applyRepository.save(apply);
    }


}
