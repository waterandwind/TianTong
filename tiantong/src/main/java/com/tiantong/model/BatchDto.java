package com.tiantong.model;

import lombok.Data;

import java.util.List;

@Data
public class BatchDto {
    Integer state;
    List<Integer> idList;
}
