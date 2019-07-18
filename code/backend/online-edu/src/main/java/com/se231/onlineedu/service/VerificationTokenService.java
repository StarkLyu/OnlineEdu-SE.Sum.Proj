package com.se231.onlineedu.service;

import com.se231.onlineedu.model.VerificationToken;

/**
 * @author liu
 * @date 2019/07/11
 */
public interface VerificationTokenService {

    void verify(VerificationToken verificationToken, String token);
    VerificationToken generateToken();
}
