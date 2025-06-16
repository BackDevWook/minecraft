package com.example.expertplugin.manager.user.enums;

import lombok.Getter;

@Getter
public enum RepeatQuests {

    PIG_KILL(1, 5),
    COW_KILL(1,10 ),
    ZOMBIE_KILL(3,20 ),
    GATHER_DIAMOND(5,128 ),
    GATHER_EMERALD(7,128 ),
    CHICKEN_KILL(1, 7 );

    private final int level;
    private final int goal;

    RepeatQuests(int level, int goal) {
        this.level = level;
        this.goal = goal;
    }
}
