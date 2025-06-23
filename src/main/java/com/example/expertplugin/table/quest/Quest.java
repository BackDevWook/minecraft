package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.QuestTypes;
import com.example.expertplugin.table.enums.ScopeTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

    private Integer id;
    private String name;
    private String description;
    private QuestTypes questType;
    private ScopeTypes scopeType;
    private boolean isRepeatable;
    private Short dailyRepeatLimit;
    private LinkedHashMap<String, Integer> rewards; // json
    private LinkedHashMap<String, Integer> mission; // json

    /*
        식별자
        퀘스트 이름
        퀘스트 설명
        퀘스트 타입(일반, 스토리, 반복, 전직 등)
        퀘스트 주체(유저, 파티, 마을 등)
        퀘스트 반복 가능 여부
        하루 최대 반복 가능 수
        보상 JSON
        퀘스트 목표 JSON
     */
}
