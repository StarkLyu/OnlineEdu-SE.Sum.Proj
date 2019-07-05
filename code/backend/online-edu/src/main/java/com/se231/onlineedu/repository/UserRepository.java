package com.se231.onlineedu.repository;


import com.se231.onlineedu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find user by username
     *
     * @param username finding user with this exact username
     * @return the user with this username
     * */
    Optional<User> findByUsername(String username);

    /**
     * check if username is valid
     *
     * @param username pending username
     * @return  true if exists same username
     */
    Boolean existsByUsername(String username);
}