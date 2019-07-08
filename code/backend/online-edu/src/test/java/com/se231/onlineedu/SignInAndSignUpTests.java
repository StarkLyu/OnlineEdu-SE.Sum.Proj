package com.se231.onlineedu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se231.onlineedu.model.RoleType;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.message.response.JwtResponse;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

/**
 * UserLoginAndRegisterTests class
 * <p>
 * test user register, login, and authorization
 *
 * @author Yuxuan Liu
 * @date 2019/07/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SignInAndSignUpTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    private static String userSignUp = "{ \"username\":\"user1\",\n" +
            "  \"password\":\"password\",\n" +
            "  \"email\":\"12312412@qq.com\",\n" +
            "  \"tel\":\"13345676543\",\n" +
            "  \"university\":\"sjtu\",\n" +
            "  \"major\":\"SE\",\n" +
            "  \"grade\":2,\n" +
            "  \"sno\":\"12321\",\n" +
            "  \"realName\":\"小王\",\n" +
            "  \"sex\":\"男\"\n" +
            "}";

    private static String userSignIn = "{\n" +
            "\t\"username\":\"user1\",\n" +
            "\t\"password\":\"password\"" +
            "}";


    private static String adminSignIn = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String superAdminSignIn = "{\n" +
            "\t\"username\":\"super_admin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String userContent = "User Contents";
    private static String adminContent = "Admin Contents";
    private static String superAdminContent = "Super Admin Contents";

    private static String signUpResponse = "User registered successfully!";

    private static String userSignInWrong = "{\n" +
            "\t\"username\":\"user1\",\n" +
            "\t\"password\":\"password1\"" +
            "}";

    private static List<Role> adminRole=new ArrayList<>();

    private static List<Role> superAdminRole=new ArrayList<>();


    private static User admin = new User("admin","");

    private static User superAdmin = new User("super_admin","");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    private String result;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * work around for static requirement of beforeClass
     * <p>
     * see https://stackoverflow.com/questions/12087959/junit-run-set-up-method-once
     */
    private static boolean setUpIsDone = false;

    @Before
    public void setup() throws Exception {
        if (setUpIsDone) {
            mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
            return;
        }

        roleRepository.save(new Role(RoleType.ROLE_USER));
        adminRole.add(roleRepository.save(new Role(RoleType.ROLE_ADMIN)));
        superAdminRole.add(roleRepository.save(new Role(RoleType.ROLE_SUPER_ADMIN)));
        roleRepository.save(new Role(RoleType.ROLE_TEACHING_ADMIN));

        admin.setRoles(adminRole);
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setEmail("aaa@qq.com");
        userRepository.save(admin);
        superAdmin.setEmail("bbb@126.com");
        superAdmin.setPassword(passwordEncoder.encode("password"));
        superAdmin.setRoles(superAdminRole);
        userRepository.save(superAdmin);

        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        result = mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignUp)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.equals(signUpResponse));

        result = mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignUp)).andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals("Fail -> Username is already taken!"));

        setUpIsDone = true;
    }

    @Test
    public void testUserAuthorization() throws Exception {
        result = mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignIn))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JwtResponse jwtResponse = objectMapper.readValue(result, JwtResponse.class);
        result = mvc.perform(get("/api/test/user").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(userContent));

        mvc.perform(get("/api/test/admin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isForbidden());

        mvc.perform(get("/api/test/superAdmin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAdminAuthorization() throws Exception {
        result = mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JwtResponse jwtResponse = objectMapper.readValue(result, JwtResponse.class);
        result = mvc.perform(get("/api/test/user").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(userContent));

        result = mvc.perform(get("/api/test/admin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(adminContent));

        mvc.perform(get("/api/test/superAdmin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testSuperAdminAuthorization() throws Exception {
        result = mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(superAdminSignIn))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JwtResponse jwtResponse = objectMapper.readValue(result, JwtResponse.class);
        result = mvc.perform(get("/api/test/user").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(userContent));

        result = mvc.perform(get("/api/test/admin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(adminContent));

        result = mvc.perform(get("/api/test/superAdmin").header("Authorization", jwtResponse.getTokenType() + " " + jwtResponse.getAccessToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.equals(superAdminContent));
    }

    @Test
    public void testWrongLogin() throws Exception {
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignInWrong))
                .andExpect(status().isUnauthorized());
    }

}
