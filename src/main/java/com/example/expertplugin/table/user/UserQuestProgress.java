package com.example.expertplugin.table.user;

import com.example.expertplugin.table.enums.QuestStatus;
import com.example.expertplugin.table.quest.Quest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserQuestProgress {

    private Integer id;
    private Integer userId;
    private Quest quest;
    private LocalDateTime acceptedAt;
    private LocalDateTime completedAt;
    private LocalDateTime givenUpAt;
    private String progress; // json
    private QuestStatus status;

    /*
      식별자
      유저 외래키
      퀘스트 외래키
      퀘스트 수락일
      퀘스트 완료일
      퀘스트 포기일
      진행 정도
      퀘스트 상태 여부 (시작 전, 진행 중, 완료)
     */

}
