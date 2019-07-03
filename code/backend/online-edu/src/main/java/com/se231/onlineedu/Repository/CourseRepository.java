package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Course Repository interface
 *
 * used to operate with database
 *
 * @author zhe li
 * @date 2019/7/1
 */

public interface CourseRepository extends JpaRepository<Course,Long> {

}
