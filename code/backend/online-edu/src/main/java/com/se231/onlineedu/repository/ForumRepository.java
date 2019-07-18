package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Forum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Forum repository
 *
 * @date 2019/07/11
 *
 * @author YuxuanLiu
 */
@Repository
public interface ForumRepository extends CrudRepository<Forum, String> {
    List<Forum> findByCourseIdAndSecNo(Long courseId, int secNo);
    List<Forum> findByCourseId(Long courseId);
}
