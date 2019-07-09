package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.message.response.JwtResponse;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.RoleType;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.security.jwt.JwtProvider;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public JwtResponse userSignIn(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken((UserPrinciple) authentication.getPrincipal());
        return new JwtResponse(jwt);
    }

    @Override
    public ResponseEntity<String> userSignUp(SignUpForm form){
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

        List<Role> roles=new ArrayList<>();
        Role userRole = roleRepository.findByRole(RoleType.ROLE_USER).
                orElseThrow(()->new RuntimeException("Fail -> Cause: User Role Not Found"));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }

    @Override
    public ResponseEntity<String> addTeachingAdmin(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException(("Fail -> Case: User Not Found.")));
        Role teachingAdmin = roleRepository.findByRole(RoleType.ROLE_TEACHING_ADMIN)
                .orElseThrow(()->new RuntimeException("Fail -> Case: Teaching Admin Role Not Found"));
        if(user.getRoles().contains(teachingAdmin)){
            return ResponseEntity.ok("This User has already been a teaching admin.");
        }
        user.getRoles().add(teachingAdmin);
        userRepository.save(user);
        return ResponseEntity.ok("Add Teaching Admin successfully");
    }


}
