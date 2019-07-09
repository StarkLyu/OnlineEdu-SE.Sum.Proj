package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.model.SectionPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;


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
}
