package com.se231.onlineedu.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author yuxuanLiu
 * @date 2019/07/30
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class CourseController {
    @Autowired
    private MockMvc mockMvc;


}