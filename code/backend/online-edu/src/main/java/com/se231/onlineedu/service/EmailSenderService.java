package com.se231.onlineedu.service;

import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {
    void sendEmail(SimpleMailMessage email);

    String sendEmail(String email, VerificationToken token) throws Exception;
}
