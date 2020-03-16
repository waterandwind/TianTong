package com.tiantong.model;

import lombok.Data;

@Data
public class PasswordDto {
    String oldPassword;
    String newPassword;
    Integer id;
}
