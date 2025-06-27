package com.example.expertplugin.table.user;

import com.example.expertplugin.table.enums.UserRoles;
import com.example.expertplugin.table.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class User {

    private Integer id; // 식별자
    private String userName; // 유저 닉네임
    private UserRoles role; // 서버 역할
    private Integer gold; // 골드
    private UUID uuid; // UUID 값

    // 참조용
    private Map<Quest,UserQuestProgress> userQuestProgresses = new HashMap<>(); // 유저가 받은 퀘스트

    public User(
            Integer id,
            String userName,
            UserRoles role,
            Integer gold,
            UUID uuid
    ) {
        this.id = id;
        this.userName = userName;
        this.role = role;
        this.gold = gold;
        this.uuid = uuid;
    }

    public void addProgress(Quest quest, UserQuestProgress progress) {
        userQuestProgresses.put(quest, progress);
    }
}
