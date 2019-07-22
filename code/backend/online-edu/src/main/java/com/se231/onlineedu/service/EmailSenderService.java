package com.se231.onlineedu.service;

import com.se231.onlineedu.model.VerificationToken;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(SimpleMailMessage email);

    String sendVerificationEmail(String email, VerificationToken token);

    void sendSensitiveWordsDetectedWords(String email);

    void sendNotification(String email);
}
