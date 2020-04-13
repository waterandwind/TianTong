package com.tiantong.model;

import lombok.Data;

@Data
public class  SingerInfo extends AccountDto {
    String info;
    Integer checkState;
    String singerType;
    Integer singerId;
    String remark;
}
