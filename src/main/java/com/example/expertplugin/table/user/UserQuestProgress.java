package com.example.expertplugin.table.user;

import com.example.expertplugin.table.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserQuestProgress {

    private Long id;
    private Quest quest;
    private User user;
}
