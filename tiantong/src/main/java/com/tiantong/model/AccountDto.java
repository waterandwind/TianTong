package com.tiantong.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto extends Account {
    String token;
    List<Menu> menuList;
}
