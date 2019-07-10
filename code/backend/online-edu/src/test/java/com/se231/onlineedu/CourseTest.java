//package com.se231.onlineedu;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.util.*;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
//import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
//import com.se231.onlineedu.model.*;
//import com.se231.onlineedu.repository.RoleRepository;
//import com.se231.onlineedu.repository.UserRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// * Course Test
// *
// * Test operation about opening a new course(and prototype)
// *
// * @author Zhe Li
// *
// * @date 2019/7/5
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//public class CourseTest {
//    //initialize test case and local variable
//    private static String userSignUp = "{ \"username\":\"user\",\n" +
//            "  \"password\":\"password\",\n" +
//            "  \"email\":\"12312412@qq.com\",\n" +
//            "  \"tel\":\"13345676543\",\n" +
//            "  \"university\":\"sjtu\",\n" +
//            "  \"major\":\"SE\",\n" +
//            "  \"grade\":2,\n" +
//            "  \"sno\":\"12321\",\n" +
//            "  \"realName\":\"小王\",\n" +
//            "  \"sex\":\"男\"\n" +
//            "}";
//
//    private static String userSignIn = "{\n" +
//            "\t\"username\":\"user\",\n" +
//            "\t\"password\":\"password\""+
//            "}";
//
//    private static String adminSignUp = "{\"username\":\"admin\",\n" +
//            "  \"password\":\"password\",\n" +
//            "  \"email\":\"12312@qq.com\","+
//            "  \"tel\":\"13345676043\"}";
//
//    private static String adminSignIn = "{\n" +
//            "\t\"username\":\"admin\",\n" +
//            "\t\"password\":\"password\"" +
//            "}";
//
//    private static List<Role> userRole=new ArrayList<>();
//
//    private static List<Role> adminRole=new ArrayList<>();
//
//    private static CoursePrototype coursePrototype1= new CoursePrototype();
//
//    private static CreateCourseApplicationForm applyCourse1 = new CreateCourseApplicationForm();
//
//    private static String nullString= JSONObject.toJSONString(new CreateCoursePrototypeApplicationForm());
//
//    private static String shortTitle= JSONObject.toJSONString(new CreateCoursePrototypeApplicationForm("as",""));
//
//    private static String LongTitle="{\n" +
//            "\"title\":\"qwertyuiopasdfghjklzxcvbnmqwertyuiuop\"}";
//
//    private static String titleAndDes="{\"title\":\"English\",\n" +
//            "\"description\":\"learing English\"}";
//
//    private static String longDes="{\"title\":\"MATH\",\n" +
//            "\"description\":\"abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwkabcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,\"}";
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private MockMvc mvc;
//
//    private String result;
//
//    /**
//     * work around for static requirement of beforeClass
//     * <p>
//     * see https://stackoverflow.com/questions/12087959/junit-run-set-up-method-once
//     */
//
//    private static boolean setUpIsDone = false;
//
//    @Before
//    public void setup() throws Exception {
//        if (setUpIsDone) {
//            mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//            return;
//        }
//
//        userRole.add(roleRepository.save(new Role(RoleType.ROLE_USER)));
//        adminRole.add(roleRepository.save(new Role(RoleType.ROLE_ADMIN)));
//        roleRepository.save(new Role(RoleType.ROLE_SUPER_ADMIN));
//        roleRepository.save(new Role(RoleType.ROLE_TEACHING_ADMIN));
//
//        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//
//        mvc.perform(post("/api/auth/signup")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(userSignUp)).andExpect(status().isOk());
//
//        mvc.perform(post("/api/auth/signup")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(adminSignUp)).andExpect(status().isOk());
//
//        User admin = userRepository.getOne(2L);
//        admin.setRoles(adminRole);
//        userRepository.save(admin);
//
//        coursePrototype1.setTitle("English");
//
//        coursePrototype1.setId(1L);
//
//        coursePrototype1.setState(CoursePrototypeState.NOT_PASS);
//
//        Calendar startDate = Calendar.getInstance();
//
//        Calendar endDate = Calendar.getInstance();
//
//        startDate.add(Calendar.DATE,10);
//
//        endDate.add(Calendar.DATE,60);
//
//        applyCourse1.setStartDate(startDate.getTime());
//
//        applyCourse1.setEndDate(endDate.getTime());
//
//        setUpIsDone=true;
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN",username = "admin")
//    public void testCreateCourse() throws Exception {
//
//        mvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(adminSignIn));
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(nullString))
//                .andExpect(status().isBadRequest());
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(LongTitle))
//                .andExpect(status().isBadRequest());
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(shortTitle))
//                .andExpect(status().isBadRequest());
//
//        result = mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(titleAndDes))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        Assert.assertEquals(coursePrototype1.getTitle(),JSON.parseObject(result,CoursePrototype.class).getTitle());
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(longDes))
//                .andExpect(status().isBadRequest());
//
//        result = mvc.perform(post("/api/coursePrototypes/1/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("decision","using"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        Assert.assertEquals(CoursePrototypeState.USING,JSON.parseObject(result,CoursePrototype.class).getState());
//
//    }
//
//    @Test
//    @WithMockUser(username = "admin2",roles = "ADMIN")
//    public void applyCoursePrototypeTest() throws Exception{
//        mvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(adminSignIn));
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(titleAndDes))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/coursePrototypes/1/apply"))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/coursePrototypes/apply")
//                .param("decision","disapproval")
//                .param("course_id","1")
//                .param("applicant_id","2"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "admin2",roles = "ADMIN")
//    public void applyCourseTest() throws Exception{
//        mvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(adminSignIn));
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(titleAndDes))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/courses/start")
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("prototypeId","1")
//                .content(JSONObject.toJSONString(applyCourse1)))
//                .andExpect(status().isOk());
//
//        result = mvc.perform(post("/api/courses/1/start")
//                .param("decision","approval"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
//        Assert.assertEquals(CourseState.READY_TO_START,JSON.parseObject(result,Course.class).getState());
//
//    }
//
//    @Test
//    @WithMockUser(username = "admin2",roles = "ADMIN")
//    public void pickCourseTest() throws Exception{
//        mvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(adminSignIn));
//
//        mvc.perform(post("/api/coursePrototypes/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(titleAndDes))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/courses/start")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSONObject.toJSONString(applyCourse1))
//                .param("prototypeId","1"))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/courses/1/start")
//                .param("decision","approval"))
//                .andExpect(status().isOk());
//
//        mvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(userSignIn));
//
//        mvc.perform(post("/api/courses/1/pick"))
//                .andExpect(status().isOk());
//
//    }
//
//
//
//}