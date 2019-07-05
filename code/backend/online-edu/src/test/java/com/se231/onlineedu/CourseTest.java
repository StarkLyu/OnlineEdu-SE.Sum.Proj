package com.se231.onlineedu;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CourseTest {
    //initialize test case and local variable
    private static String userSignUp = "{\n" +
            "\t\"username\":\"user\",\n" +
            "\t\"password\":\"password\",\n" +
            "\t\"roles\":[\"user\"]\n" +
            "}";

    private static String userSignIn = "{\n" +
            "\t\"username\":\"user\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static String adminSignUp = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\",\n" +
            "\t\"roles\":[\"admin\"]\n" +
            "}";

    private static String adminSignIn = "{\n" +
            "\t\"username\":\"admin\",\n" +
            "\t\"password\":\"password\"" +
            "}";

    private static List<Role> userRole=new ArrayList<>();

    private static List<Role> adminRole=new ArrayList<>();

    private static User user;

    private static User admin;

    private static CoursePrototype coursePrototype1= new CoursePrototype();

    private static CreateCourseApplicationForm applyCourse1 = new CreateCourseApplicationForm();

    private static String nullString= JSONObject.toJSONString(new CreateCoursePrototypeApplicationForm());

    private static String shortTitle= JSONObject.toJSONString(new CreateCoursePrototypeApplicationForm("as",""));

    private static String LongTitle="{\n" +
            "\"title\":\"qwertyuiopasdfghjklzxcvbnmqwertyuiuop\"}";

    private static String titleAndDes="{\"title\":\"English\",\n" +
            "\"description\":\"learing English\"}";

    private static String longDes="{\"title\":\"MATH\",\n" +
            "\"description\":\"abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwkabcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,\"}";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RoleRepository roleRepository;

    private MockMvc mvc;

    private String result;

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

        userRole.add(roleRepository.save(new Role(RoleType.ROLE_USER)));
        adminRole.add(roleRepository.save(new Role(RoleType.ROLE_ADMIN)));
        roleRepository.save(new Role(RoleType.ROLE_SUPER_ADMIN));
        roleRepository.save(new Role(RoleType.ROLE_TEACHING_ADMIN));

        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignUp));

        user = new User(1L,"user","password",userRole);

        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignUp));

        admin = new User(2L,"admin","password",adminRole);

        admin.setCourses(Collections.emptyList());

        coursePrototype1.setTitle("English");

        coursePrototype1.setId(1L);

        coursePrototype1.setState(CoursePrototypeState.NOT_PASS);

        Calendar startDate = Calendar.getInstance();

        Calendar endDate = Calendar.getInstance();

        startDate.add(Calendar.DATE,10);

        endDate.add(Calendar.DATE,60);

        applyCourse1.setStartDate(startDate.getTime());

        applyCourse1.setEndDate(endDate.getTime());

        setUpIsDone=true;
    }

    @Test
    @WithMockUser(roles = "ADMIN",username = "admin")
    public void testCreateCourse() throws Exception {

        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn));

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nullString))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(LongTitle))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shortTitle))
                .andExpect(status().isBadRequest());

        result = mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(titleAndDes))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(coursePrototype1.getTitle(),JSON.parseObject(result,CoursePrototype.class).getTitle());

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(longDes))
                .andExpect(status().isBadRequest());

        result = mvc.perform(post("/api/coursePrototype/1/create")
                .contentType(MediaType.APPLICATION_JSON)
                .param("decision","using"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(CoursePrototypeState.USING,JSON.parseObject(result,CoursePrototype.class).getState());

    }

    @Test
    @WithMockUser(username = "admin2",roles = "ADMIN")
    public void applyCoursePrototypeTest() throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn));

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(titleAndDes))
                .andExpect(status().isOk());

        mvc.perform(post("/api/coursePrototype/1/apply"))
                .andExpect(status().isOk());

        mvc.perform(post("/api/coursePrototype/apply")
                .param("decision","disapproval")
                .param("course_id","1")
                .param("applicant_id","2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin2",roles = "ADMIN")
    public void applyCourseTest() throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn));

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(titleAndDes))
                .andExpect(status().isOk());

        mvc.perform(post("/api/course/1/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(applyCourse1)))
                .andExpect(status().isOk());

        result = mvc.perform(post("/api/course/start")
                .param("decision","approval")
                .param("courseId","1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(CourseState.READY_TO_START,JSON.parseObject(result,Course.class).getState());

    }

    @Test
    @WithMockUser(username = "admin2",roles = "ADMIN")
    public void pickCourseTest() throws Exception{
        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminSignIn));

        mvc.perform(post("/api/coursePrototype/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(titleAndDes))
                .andExpect(status().isOk());

        mvc.perform(post("/api/course/1/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(applyCourse1)))
                .andExpect(status().isOk());

        mvc.perform(post("/api/course/start")
                .param("decision","approval")
                .param("courseId","1"))
                .andExpect(status().isOk());

        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSignIn));

        mvc.perform(post("/api/course/1/pick"))
                .andExpect(status().isOk());

    }



}