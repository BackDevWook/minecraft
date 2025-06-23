package com.example.expertplugin.table.towny;

import com.example.expertplugin.table.enums.TownRoles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TownMember {

    private Integer id;
    private Integer townId;
    private Integer userId;
    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;
    private TownRoles role;

    /*
        식별자
        마을 외래키
        유저 외래키
        마을 가입일
        마을 탈퇴일
        마을 내 역할
     */

}
