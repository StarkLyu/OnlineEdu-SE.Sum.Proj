package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}