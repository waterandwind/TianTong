package com.tiantong.model;

import lombok.Data;

@Data
public class SearchDto {
    String keyWord;
    //0 歌曲,1歌手;
    Integer type;
}
