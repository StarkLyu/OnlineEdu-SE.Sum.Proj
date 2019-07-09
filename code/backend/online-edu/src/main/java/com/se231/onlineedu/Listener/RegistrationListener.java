package com.se231.onlineedu.Listener;

import com.se231.onlineedu.event.OnRegistrationCompleteEvent;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.service.AuthService;
import com.se231.onlineedu.service.EmailSenderService;
import com.se231.onlineedu.serviceimpl.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private AuthService service;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm?token=" + token;
        String message = "您已成功注册。       请点击链接以激活账号邮件。";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setFrom("18621107375@163.com");
        email.setText(message + "： " + "http://localhost:8081/" + confirmationUrl);
        emailSenderService.sendEmail(email);
    }
}
