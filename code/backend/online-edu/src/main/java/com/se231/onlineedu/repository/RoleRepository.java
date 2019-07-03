package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.RoleType;
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
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * find Role by RoleType enum
     *
     * @param role
     * @return Optional<Role>
     */
    Optional<Role> findByRole(RoleType role);
}
