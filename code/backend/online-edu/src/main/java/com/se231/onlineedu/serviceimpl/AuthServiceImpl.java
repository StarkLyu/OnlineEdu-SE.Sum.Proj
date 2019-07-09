package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.se231.onlineedu.event.OnRegistrationCompleteEvent;
import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.message.response.JwtResponse;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.RoleType;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.VerificationToken;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.repository.VerificationTokenRepository;
import com.se231.onlineedu.security.jwt.JwtProvider;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

/**
 * Service Implementation related to auth.
 *
 * contain the main service logic related to auth control
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtProvider jwtProvider;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity<?> userSignIn(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(! userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("fail -> username not found")).isEnabled()){
            return ResponseEntity.badRequest().body("请先激活账号");
        }

        String jwt = jwtProvider.generateJwtToken((UserPrinciple) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @Override
    public ResponseEntity<?> userSignUp(SignUpForm form, WebRequest webRequest){
        if(userRepository.existsByUsername(form.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(form.getEmail())) {
            return new ResponseEntity<>("Fail -> Email Address is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByTel(Long.parseLong(form.getTel()))) {
            return new ResponseEntity<>("Fail -> Telephone Number is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(form);
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEnabled(false);

        List<Role> roles=new ArrayList<>();
        Role userRole = roleRepository.findByRole(RoleType.ROLE_USER).
                orElseThrow(()->new RuntimeException("Fail -> Cause: User Role Not Found"));
        roles.add(userRole);
        user.setRoles(roles);
        user = userRepository.save(user);
        VerificationToken verificationToken = new VerificationToken(user);

        verificationTokenRepository.save(verificationToken);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, webRequest.getLocale(), webRequest.getContextPath()));
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<String> addTeachingAdmin(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException(("Fail -> Case: User Not Found.")));
        user.getRoles().add(roleRepository.findByRole(RoleType.ROLE_TEACHING_ADMIN)
                .orElseThrow(()->new RuntimeException("Fail -> Case: Teaching Admin Role Not Found")));
        userRepository.save(user);
        return ResponseEntity.ok("Add Teaching Admin successfully");
    }

    @Override
    public User getUser(String verificationToken) {
        User user = verificationTokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }
    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

}
