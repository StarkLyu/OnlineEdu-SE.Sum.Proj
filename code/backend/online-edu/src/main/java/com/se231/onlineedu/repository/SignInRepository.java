package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.SignIn;
import com.se231.onlineedu.model.SignInPrimaryKey;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liu
 */
public interface SignInRepository extends CrudRepository<SignIn, SignInPrimaryKey> {
}
