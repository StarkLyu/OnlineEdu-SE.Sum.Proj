package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.model.VerificationToken;
import com.se231.onlineedu.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liu
 * @date 2019/07/11
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public String sendVerificationEmail(String email, VerificationToken token){
        String subject = "验证码服务,请在20分钟内完成验证";
        String message = "您的验证码为：";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("sjtu_se231_1@126.com");
        simpleMailMessage.setText(message + token.getToken());
        sendEmail(simpleMailMessage);
        return token.getToken();
    }

    @Override
    public void sendSensitiveWordsDetectedWords(String email) {
        String subject = "处理违规言论";
        String message = "系统监测到您的学生在论坛中发布了不当言论请前往处理。";
    }
}
