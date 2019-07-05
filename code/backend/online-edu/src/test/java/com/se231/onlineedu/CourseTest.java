package com.se231.onlineedu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseTest {
    //initialize test case and local variable
    private static String nullString="{}";

    private static String noTitle="{\n" +
            "\"title\":\"\"}";

    private static String LongTitle="{\n" +
            "\"title\":\"qwertyuiopasdfghjklzxcvbnmqwertyuiuop\"}";

    private static String titleAndDes="{\"title\":\"English\",\n" +
            "\"description\":\"learing English\"}";

    private static String longDes="{\"title\":\"MATH\",\n" +
            "\"description\":\"abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwkabcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwke[pqwmr;qlkq[wpekq[wp,dq[pl,eqw[],qwd[]qwd,[qwk,abcdasdedwqwdjqwpiojqdwpjpodqwjpoaxjopasdjopdjqwopjfopqjo[pjkqw[pkd[qpklp[dqwk[pqwdkdqp[wkdqw[pkq[pke[]pqwle[peqwkle[pqwkewpekq[wp,dq]pl,eqw[],qwd[]qwd,[qwk,\"}";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String result;

    /**
     * work around for static requirement of beforeClass
     * <p>
     * see https://stackoverflow.com/questions/12087959/junit-run-set-up-method-once
     */

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(roles = "Admin",username = "admin")
    public void testCreateCourse() throws Exception {
        mvc.perform(post("/api/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(nullString))
                .andExpect(status().isInternalServerError());

        mvc.perform(post("/api/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(noTitle))
                .andExpect(status().isInternalServerError());

        mvc.perform(post("/api/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(LongTitle))
                .andExpect(status().isInternalServerError());

        result = mvc.perform(post("/api/course/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(titleAndDes))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        result = mvc.perform(post("/api/course/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(longDes))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    @WithMockUser(username = "admin2",roles = "ADMIN")
    public void applyCourseTest() throws Exception{
        result = mvc.perform(post("/api/course/1/apply")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        result = mvc.perform(post("/api/course/2/apply")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

}