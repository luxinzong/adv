package com.suma;

import com.suma.dao.AdvFlyWordMapper;
import com.suma.dao.AdvInfoMapper;
import com.suma.dao.InfoFlyWordMapper;
import com.suma.pojo.AdvFlyWord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvApplicationTests {

    @Autowired
    AdvFlyWordMapper advFlyWordMapper;

    @Test
    public void contextLoads() {
        AdvFlyWord advFlyWord = new AdvFlyWord();
        advFlyWord.setId(1l);
        advFlyWord.setContent("sda");
        List<AdvFlyWord> advFlyWords = new ArrayList<>();
        advFlyWords.add(advFlyWord);
        advFlyWordMapper.saveAll(advFlyWords);
        System.out.println( advFlyWord.getId());
    }

}
