package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.CoursePrototype;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of Course prototype
 *
 * used to operate to database
 *
 * @author Zhe Li
 * @date 2019/7/3
 */
public interface CoursePrototypeRepository extends JpaRepository<CoursePrototype,Long> {
}
