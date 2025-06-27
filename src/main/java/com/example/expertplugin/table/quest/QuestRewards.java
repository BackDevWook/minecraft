package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestRewards {

    private Long id; // 식별자
    private Quest questId; // 퀘스트 외래키
    private EntityType type; // 보상 타입
    private String target; // 보상
    private Integer value; // 값

}
