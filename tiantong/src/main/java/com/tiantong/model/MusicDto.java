package com.tiantong.model;


import com.tiantong.config.LrcAnalyze;
import lombok.Data;

import java.util.List;
@Data
public class MusicDto extends Music {
    List<LrcAnalyze.LrcData> lrc;
}
