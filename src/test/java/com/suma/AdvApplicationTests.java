package com.suma;

import com.google.common.collect.Lists;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.pojo.AdvItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvApplicationTests {

    @Autowired
    AdvInfoServiceGroupMapper advInfoServiceGroupMapper;

    @Test
    public void contextLoads() {
        Long s = 3L;
        System.out.println(s.toString());

    }

}
