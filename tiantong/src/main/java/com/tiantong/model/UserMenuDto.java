package com.tiantong.model;

import lombok.Data;

import java.util.List;

@Data
public class UserMenuDto {
    Integer type;
    List<Menu> menuList;
}
