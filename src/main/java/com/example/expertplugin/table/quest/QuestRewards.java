package com.example.expertplugin.table.quest;

import com.example.expertplugin.table.enums.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestRewards {

    private Long id;
    private Quest questId;
    private EntityType type;
    private String target;
    private Integer value;

    /*
       식별자
       퀘스트 외래키
       보상타입
       보상
       값
     */
}
