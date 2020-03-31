package com.tiantong.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchSingerAndMusic {
    List<Music> musicList;
    List<SingerInfo> singerList;
}
