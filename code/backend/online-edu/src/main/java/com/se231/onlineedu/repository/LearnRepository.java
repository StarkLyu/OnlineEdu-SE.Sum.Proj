package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Learn;
import com.se231.onlineedu.model.LearnPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Learn Repository Interface
 *
 * used to operate with db.
 *
 * @author Zhe Li
 * @date 2019/07/16
 */
public interface LearnRepository extends JpaRepository<Learn, LearnPrimaryKey> {
}
