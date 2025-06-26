package com.example.expertplugin.table.user;

import com.example.expertplugin.table.enums.UserRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {

    private Integer id;
    private String userName;
    private UserRoles role;
    private Integer gold;
    private UUID uuid;

    /*
        식별자
        유저 닉네임
        서버 역할
        골드
        UUID 값
     */
}
