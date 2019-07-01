package com.se231.onlineedu.Repository;


import com.se231.onlineedu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository interface
 *
 * used to operation with database
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find user by username
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * check if username is valid
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
}
