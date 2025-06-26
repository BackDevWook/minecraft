package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestObjectives {

    private Long id;
    private Quest questId;
    private EntityType type;
    private String target;
    private Integer RequiredCount;

    /*
        식별자
        퀘스트 외래키
        목표 타입
        목표물
        요구 수치  ex) 10마리 잡기
     */
}
