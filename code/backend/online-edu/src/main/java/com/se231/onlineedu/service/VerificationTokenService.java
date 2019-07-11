package com.se231.onlineedu.service;

import com.se231.onlineedu.model.VerificationToken;

public interface VerificationTokenService {
    boolean verify(VerificationToken verificationToken, String token);
    VerificationToken generateToken();
}
