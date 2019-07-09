package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User Service Implementation Class
 *
 * contain main service logic related to user.
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public PersonalInfo getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user"));
        return new PersonalInfo(user);
    }

    @Override
    public PersonalInfo modifyUserInfo(Long userId,PersonalInfo personalInfo) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user"));
        personalInfo.modifyUserInfo(user);
        return new PersonalInfo(userRepository.save(user));
    }

    @Override
    public PersonalInfo manageUserInfo(Long id, PersonalInfo personalInfo) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        personalInfo.modifyUserInfo(user);
        return new PersonalInfo(userRepository.save(user));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean checkSameUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkSameEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkSameTel(String tel) {
        return userRepository.existsByTel(Long.parseLong(tel));
    }
}
