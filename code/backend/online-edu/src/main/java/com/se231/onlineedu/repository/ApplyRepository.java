package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Apply;
import com.se231.onlineedu.model.ApplyPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Teacher Apply For Course Entity repository
 *
 * used to operate with database about the table TeacherApplyFotCourse
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
public interface ApplyRepository extends JpaRepository<Apply, ApplyPrimaryKey> {
}
