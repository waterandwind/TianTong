package com.tiantong;

import com.tiantong.config.LrcAnalyze;
import com.tiantong.config.SendMail;
import com.tiantong.mapper.MusicMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TiantongApplicationTests {

    @Test
    void contextLoads() throws Exception {
        LrcAnalyze lrcAnalyze = new LrcAnalyze("E:\\IdeaProject\\TianTong\\tiantong\\src\\main\\resources\\profile\\周杰伦 - 菊花台.lrc");
        for (LrcAnalyze.LrcData lrc : lrcAnalyze.LrcGetList()
        ) {
            String lyric = "";
            if (lrc.Time != null) {
                lyric = lyric + lrc.Time;
            }
            if (lrc.LrcLine != null) {
                lyric = lyric + " " + lrc.LrcLine;
            }

            System.out.println(lyric);
        }
    }

}
