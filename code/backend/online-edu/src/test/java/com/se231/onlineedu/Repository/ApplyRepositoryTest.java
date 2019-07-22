//package com.se231.onlineedu.Repository;
//
//import com.se231.onlineedu.model.Apply;
//import com.se231.onlineedu.model.ApplyPrimaryKey;
//import com.se231.onlineedu.model.CoursePrototype;
//import com.se231.onlineedu.repository.ApplyRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ApplyRepositoryTest {
//    @Autowired
//    private ApplyRepository applyRepository;
//
//
//    @Test
//    public void findAppliesByPrototypeId(){
//        CoursePrototype coursePrototype = new CoursePrototype();
//        coursePrototype.setId(1L);
//        Apply apply = new Apply();
//        ApplyPrimaryKey applyPrimaryKey = new ApplyPrimaryKey();
//        applyPrimaryKey.setCoursePrototype();
//        apply.setApplicationForCoursePK();
//
//        applyRepository.findAppliesByPrototypeId()
//        Long coursePrototypeId;
//    }
//}
