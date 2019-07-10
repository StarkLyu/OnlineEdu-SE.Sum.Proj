package com.se231.onlineedu;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.RoleType;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Manage Personal Information Test
 *
 * Test operation about managing personal information.
 *
 * @author Zhe Li
 *
 * @date 2019/07/09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ManageInfoTest {

    //related repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    //web context
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PasswordEncoder encoder;


    private static String user1 = "{ \"username\":\"user1\",\n" +
            "  \"password\":\"password\",\n" +
            "  \"email\":\"12312412@qq.com\",\n" +
            "  \"tel\":\"13345678901\",\n" +
            "  \"university\":\"sjtu\",\n" +
            "  \"major\":\"SE\",\n" +
            "  \"grade\":2,\n" +
            "  \"sno\":\"12321\",\n" +
            "  \"realName\":\"小王\",\n" +
            "  \"sex\":\"男\"\n" +
            "}";

    private static String user2 = "{ \"username\":\"user2\",\n" +
            "  \"password\":\"password\",\n" +
            "  \"email\":\"user2@163.com\",\n" +
            "  \"tel\":\"13345678902\", \n" +
            "  \"university\":\"sjtu\",\n" +
            "  \"major\":\"SE\",\n" +
            "  \"grade\":2,\n" +
            "  \"sno\":\"12321\",\n" +
            "  \"realName\":\"张三\",\n" +
            "  \"sex\":\"男\"\n" +
            "}";

    private static String signUpResponse = "User registered successfully!";

    private static boolean setUpIsDone=false;

    private MockMvc mvc;

    private User admin = new User("admin","");

    private List<Role> adminRole = new ArrayList<>();

    private String result;

    private static String adminSignIn = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String user1SignIn="{ \"username\":\"user1\",\n" +
            "  \"password\":\"password\"\n" +
            "}";

    private static String user2SignIn = "{ \"username\":\"user2\",\n" +
            "  \"password\":\"password\"\n" +
            "}";

    private static PersonalInfo personalInfo;

    private static List<Role> roles = new ArrayList<>();

    public ManageInfoTest() {
    }

    @Before
    public void setup() throws Exception {
        if (setUpIsDone) {
            mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
            return;
        }

        roles.add(roleRepository.save(new Role(RoleType.ROLE_USER)));
        adminRole.add(roleRepository.save(new Role(RoleType.ROLE_ADMIN)));
        roleRepository.save(new Role(RoleType.ROLE_SUPER_ADMIN));
        roles.add(roleRepository.save(new Role(RoleType.ROLE_TEACHING_ADMIN)));

        admin.setRoles(adminRole);
        admin.setPassword(encoder.encode("password"));
        admin.setEmail("admin@qq.com");
        userRepository.save(admin);

        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        result = mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1)).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(result.equals(signUpResponse));

        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user2)).andExpect(status().isOk());

        setUpIsDone = true;
    }

    @Test
    @WithMockUser
    public void addTeachingAdminTest()throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn))
                .andExpect(status().isOk());


        result = mvc.perform(post("/api/auth/2/teachingAdmin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("Add Teaching Admin successfully",result);
        Assert.assertEquals(roles,userRepository.getOne(2L).getRoles());

        result = mvc.perform(post("/api/auth/2/teachingAdmin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("This User has already been a teaching admin.",result);
    }

    @Test
    @WithMockUser
    public void checkAndModifyInfoTest()throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user1SignIn))
                .andExpect(status().isOk());

        personalInfo=new PersonalInfo(userRepository.getOne(2L));

        result = mvc.perform(get("/api/users/info"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals(personalInfo, JSON.parseObject(result,PersonalInfo.class));

        personalInfo.setEmail("use1@163.com");

        result = mvc.perform(post("/api/users/info/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(personalInfo)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals(personalInfo,JSON.parseObject(result,PersonalInfo.class));
    }

    @Test
    @WithMockUser
    public void getAndManageUserInfo()throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn))
                .andExpect(status().isOk());

        result = mvc.perform(get("/api/users/info/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals(userRepository.findAll(),JSON.parseArray(result,User.class));

        personalInfo.setEmail("us1@163.com");

        result = mvc.perform(post("/api/users/2/info/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(personalInfo)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals(personalInfo,JSON.parseObject(result,PersonalInfo.class));

        result = mvc.perform(get("/api/users/checkSame/username")
                .param("username","user1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("true",result);

        result = mvc.perform(get("/api/users/checkSame/username")
                .param("username","use"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("false",result);

        result = mvc.perform(get("/api/users/checkSame/email")
                .param("email","admin@qq.com"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("true",result);

        result = mvc.perform(get("/api/users/checkSame/email")
                .param("email","uses@163.com"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("false",result);

        result = mvc.perform(get("/api/users/checkSame/tel")
                .param("tel","18332112332"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("false",result);

        result = mvc.perform(get("/api/users/checkSame/tel")
                .param("tel","13345678901"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("true",result);

    }
}
