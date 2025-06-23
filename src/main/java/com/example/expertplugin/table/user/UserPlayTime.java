package com.example.expertplugin.table.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPlayTime {

    private Integer id;
    private Integer userId;
    private LocalDateTime created_at;
    private LocalDateTime recentJoinAt;
    private Integer dailyPlayTime;

    /*
        식별자
        유저 외래키
        최초 접속일
        최근 접속일
        하루 접속 시간
     */
}
