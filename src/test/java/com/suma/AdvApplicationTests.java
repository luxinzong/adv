package com.suma;

import com.suma.pojo.AdvInfo;
import com.suma.service.AdvInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvApplicationTests {

    @Autowired
    AdvInfoService advInfoService;
    @Test
    public void contextLoads() {
        advInfoService.selectAdvInfo( "name", "2018-12-12", "2018-12-12", 1,1l);
    }
}
