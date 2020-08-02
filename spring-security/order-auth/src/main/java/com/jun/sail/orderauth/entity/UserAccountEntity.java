package com.jun.sail.orderauth.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAccountEntity {
    private long id;
    private String email;
    private String password;
    private String nickname;
    private String lastLogin;
    private String lastIp;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
