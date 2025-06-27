package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestObjectives {

    private Long id; // 식별자
    private Quest questId; // 퀘스트 외래키
    private EntityType type; // 엔티티 타입 (material, resource 등)
    private String target; // 목표
    private Integer RequiredCount; // 요구 수량

}
