package com.se231.onlineedu.Service;


import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.SignInRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.UserService;
import com.se231.onlineedu.serviceimpl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @TestConfiguration
    static class UserServiceImplTestContextConfig{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }
    @Autowired
    UserService userService;


    @MockBean
    private PasswordEncoder passwordEncoder;


    @MockBean
    private RoleRepository roleRepository;


    @MockBean
    private SignInRepository signInRepository;

    @MockBean
    private CourseService courseService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getUserInfo()  {
        User user = new User();
        user.setTel(531L);
        user.setUsername("Liu");
        user.setEmail("cdjddzy@foxmail.com");
        user.setPassword("password");
        user.setId(1L);
        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);

        User found = userService.getUserInfo(1L);
        assertThat(found.getEmail()).isEqualTo("cdjddzy@foxmail.com");

    }
}
