package com.example.expertplugin.table.towny;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Town {

    private Integer id;
    private String name;
    private Short level;
    private Integer townPoint;
    private LocalDateTime createdAt;
    private LocalDateTime disbandedAt;
    private boolean isDisbanded;

    /*
        식별자
        마을 이름
        마을 레벨
        마을 포인트
        마을 생성일
        마을 해체일
        마을 해체여부
     */
}
