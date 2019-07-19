package com.se231.onlineedu.repository;

import java.util.Optional;
import com.se231.onlineedu.model.SectionBranches;
import com.se231.onlineedu.model.SectionBranchesPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zhe Li
 * @date 2019/07/19
 */
public interface SectionBranchRepository extends JpaRepository<SectionBranches, SectionBranchesPrimaryKey> {
    /**
     * get max branch number of a section now
     * @param courseId course ID
     * @param secNo number of section
     * @return current section number if exists
     */
    @Query(value = "select max(branch_no) from section_branches where section_course_id = ?1 and section_sec_no=?2",nativeQuery = true)
    Optional<Integer> currentBranch(Long courseId,int secNo);
}
