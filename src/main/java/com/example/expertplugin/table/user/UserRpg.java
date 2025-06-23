package com.example.expertplugin.table.user;

import com.example.expertplugin.table.enums.RpgClass;
import lombok.Getter;

@Getter
public class UserRpg {

    private Integer id;
    private Integer userId;
    private Short level;
    private Integer exp;
    private RpgClass clazz;

    /*
        식별자
        유저 외래키
        레벨
        경험치
        직업
     */
}
