package com.se231.onlineedu.repository;

import java.util.Optional;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.model.SectionPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * SectionRepository Repository Interface
 *
 * used to operate with database
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
public interface SectionRepository extends JpaRepository<Section, SectionPrimaryKey> {
    /**
     * get max section number of a course now
     * @param courseId course ID
     * @return current section number if exists
     */
    @Query(value = "select max(sec_no) from section where course_id = ?1",nativeQuery = true)
    Optional<Integer> currentSec(Long courseId);
}
