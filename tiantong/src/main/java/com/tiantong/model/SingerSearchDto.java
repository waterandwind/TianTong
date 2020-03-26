package com.tiantong.model;

import lombok.Data;

@Data
public class SingerSearchDto extends PageInfo{
    String type;
    Integer sex;
}
