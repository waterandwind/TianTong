package com.tiantong;

import com.tiantong.mapper.MusicMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TiantongApplicationTests {
    @Autowired
    MusicMapper musicMapper;
    @Test
    void contextLoads() {
        System.out.println(musicMapper.test());
    }

}
