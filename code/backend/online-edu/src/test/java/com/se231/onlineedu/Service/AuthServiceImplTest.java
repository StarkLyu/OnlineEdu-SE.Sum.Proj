//package com.se231.onlineedu.Service;
//
//import com.se231.onlineedu.repository.RoleRepository;
//import com.se231.onlineedu.repository.UserRepository;
//import com.se231.onlineedu.security.jwt.JwtProvider;
//import com.se231.onlineedu.service.AuthService;
//import com.se231.onlineedu.service.EmailSenderService;
//import com.se231.onlineedu.service.VerificationTokenService;
//import com.se231.onlineedu.serviceimpl.AuthServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//public class AuthServiceImplTest {
//    @TestConfiguration
//    static class AuthServiceImplTestContextConfig{
//        @Bean
//        public AuthService authService(){
//            return new AuthServiceImpl();
//        }
//    }
//
//    @Autowired
//    private AuthService authService;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private RoleRepository roleRepository;
//
//    @MockBean
//    private PasswordEncoder encoder;
//
//    @MockBean
//    private JwtProvider jwtProvider;
//
//    @MockBean
//    private VerificationTokenService verificationTokenService;
//
//    @MockBean
//    private EmailSenderService emailSenderService;
//
//    @Test
//    public void userSignIn(){
//
//    }
//}
