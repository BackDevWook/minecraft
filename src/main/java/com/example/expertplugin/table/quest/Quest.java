package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.QuestTypes;
import com.example.expertplugin.table.enums.ScopeTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

    private Long id; // 식별자
    private String name; // 퀘스트 이름
    private QuestTypes questType; // 퀘스트 타입(일반, 스토리, 반복, 전직 등)
    private ScopeTypes scopeType; // 퀘스트 주체(유저, 파티, 마을 등)
    private String description; // 퀘스트 설명
    private boolean isRepeatable; // 반복 가능 여부
    private Short dailyRepeatLimit; // 하루 최대 반복 가능 횟수

    // 참조용
    private List<QuestObjectives> objectives = Collections.synchronizedList(new ArrayList<>()); // 퀘스트 목표 리스트
    private List<QuestRewards> rewards = Collections.synchronizedList(new ArrayList<>()); // 퀘스트 보상  리스트

    public Quest(Long id,
                 String name,
                 QuestTypes questType,
                 ScopeTypes scopeType,
                 String description,
                 boolean isRepeatable,
                 Short dailyRepeatLimit) {
        this.id = id;
        this.name = name;
        this.questType = questType;
        this.scopeType = scopeType;
        this.description = description;
        this.isRepeatable = isRepeatable;
        this.dailyRepeatLimit = dailyRepeatLimit;
    }

    public void addObjective(QuestObjectives objective) {
        objectives.add(objective);
    }

    public void addReward(QuestRewards reward) {
        rewards.add(reward);
    }

}
