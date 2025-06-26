package com.example.expertplugin.table.user;

import com.example.expertplugin.table.enums.EcoClass;
import lombok.Getter;

@Getter
public class UserEco {

    private Integer id;
    private User userId;
    private Short level;
    private Integer exp;
    private EcoClass clazz;

    /*
        식별자
        유저 외래키
        레벨
        경험치
        직업
     */
}
