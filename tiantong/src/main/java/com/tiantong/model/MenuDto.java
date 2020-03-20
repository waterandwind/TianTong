package com.tiantong.model;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class MenuDto {
    Integer type;
    List<Integer> idList;
}
