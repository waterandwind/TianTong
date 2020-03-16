package com.tiantong.model;

import lombok.Data;

@Data
public class SingerInfo extends Account {
    String info;
    Integer checkState;
    String singerType;
    Integer singerId;
}
