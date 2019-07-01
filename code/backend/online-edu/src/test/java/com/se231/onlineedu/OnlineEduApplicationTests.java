package com.se231.onlineedu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se231.onlineedu.Repository.RoleRepository;
import com.se231.onlineedu.message.response.JwtResponse;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * OnlineEduApplicationTests class
 *
 * test user register, login, and authorization
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineEduApplicationTests {

    private static String userSignUp = "{\n" +
            "\t\"username\":\"user\",\n" +
            "\t\"password\":\"password\",\n" +
            "\t\"role\":[\"user\"]\n" +
            "}";

    private static String userSignIn = "{\n" +
            "\t\"username\":\"user\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String adminSignUp = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\",\n" +
            "\t\"role\":[\"admin\"]\n" +
            "}";

    private static String adminSignIn = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String superAdminSignUp = "{\n" +
            "\t\"username\":\"superAdmin\",\n" +
            "\t\"password\":\"password\",\n" +
            "\t\"role\":[\"super_admin\"]\n" +
            "}";

    private static String superAdminSignIn = "{\n" +
            "\t\"username\":\"superAdmin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String userContent = "User Contents";
    private static String adminContent = "Admin Contents";
    private static String superAdminContent = "Super Admin Contents";

    private static String signUpResponse = "User registered successfully!";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RoleRepository roleRepository;

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

        roleRepository.save(new Role(UserRole.ROLE_USER));
        roleRepository.save(new Role(UserRole.ROLE_ADMIN));
        roleRepository.save(new Role(UserRole.ROLE_SUPER_ADMIN));

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
                .content(adminSignUp)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.equals(signUpResponse));

        result = mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(superAdminSignUp)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.equals(signUpResponse));

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

}
