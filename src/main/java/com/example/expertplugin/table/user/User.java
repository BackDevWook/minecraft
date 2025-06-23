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
    private Integer townId;
    private Integer partyId;

    private UserEco userEco;
    private UserRpg userRpg;
    private UserPlayTime userPlayTime;
    private UserQuestProgress userQuestProgress;

    /*
        식별자
        유저 닉네임
        서버 역할
        골드
        UUID 값
        마을 외래키
        파티 외래키
     */
}
