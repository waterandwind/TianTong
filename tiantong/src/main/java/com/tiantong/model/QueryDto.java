package com.tiantong.model;

import lombok.Data;

@Data
public class QueryDto extends PageInfo {
    Integer state;
    String keyWord;
}
