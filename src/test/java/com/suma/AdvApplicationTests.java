package com.suma;

import com.suma.dao.AdvFlyWordMapper;
import com.suma.dao.AdvInfoMapper;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.dao.InfoFlyWordMapper;
import com.suma.pojo.AdvFlyWord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvApplicationTests {

    @Autowired
    AdvInfoServiceGroupMapper advInfoServiceGroupMapper;

    @Test
    public void contextLoads() {
    }

}
