package com.tiantong.model;

import lombok.Data;

import java.util.List;

@Data
public class FormMusicListDto {
    List<Music> musicList;
    String posterUrl;
    String formName;
    Integer formId;
}
