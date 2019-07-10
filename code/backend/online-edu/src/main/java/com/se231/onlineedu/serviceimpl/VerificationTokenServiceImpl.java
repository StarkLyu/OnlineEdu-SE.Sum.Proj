package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.model.VerificationToken;
import com.se231.onlineedu.service.VerificationTokenService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Override
    public boolean verify(VerificationToken verificationToken, String token){
        if(!verificationToken.getToken().equals(token)){
           return false;
        }
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return false;
        }
        return true;
    }
}
