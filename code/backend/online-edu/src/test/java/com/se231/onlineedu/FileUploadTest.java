package com.se231.onlineedu;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadTest {
    private static String filepath1="E:\\电影\\big bang s11\\The.Big.Bang.Theory.S11E01.720p.HDTV.X264-DIMENSION.mkv";
    private static String filepath2="F:\\nodejs\\e-book\\src\\sources\\test.jpg";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void upLoadFilePublic() throws Exception{

    }
}
