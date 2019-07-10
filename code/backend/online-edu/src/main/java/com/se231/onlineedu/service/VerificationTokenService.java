package com.se231.onlineedu.service;

import com.se231.onlineedu.model.VerificationToken;
import org.springframework.http.ResponseEntity;

public interface VerificationTokenService {
    boolean verify(VerificationToken verificationToken, String token);
}
