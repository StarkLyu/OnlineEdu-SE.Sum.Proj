package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Forum;
import org.springframework.data.repository.CrudRepository;

/**
 * Forum repository
 *
 * @date 2019/07/11
 *
 * @author YuxuanLiu
 */
public interface ForumRepository extends CrudRepository<Forum, String> {
}
