package com.example.expertplugin.manager.user;

import com.example.expertplugin.manager.user.enums.ArchivementQuests;
import com.example.expertplugin.manager.user.enums.RepeatQuests;
import com.example.expertplugin.manager.user.enums.StoryQuests;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class QuestManager {

    // 스토리 퀘스트 맵
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<StoryQuests, Boolean>> storyQuestMap = new ConcurrentHashMap<>();

    // 반복 퀘스트 맵
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<RepeatQuests, Short>> repeatQuestMap = new ConcurrentHashMap<>();

    // 업적 퀘스트 맵
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<ArchivementQuests, Boolean>> AchievementQuestMap = new ConcurrentHashMap<>();


    // 유저 최초 접속 시 퀘스트 데이터 생성
    public static void init(UUID uuid) {

        ConcurrentHashMap<StoryQuests, Boolean> storyMap =
                storyQuestMap.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>());
        ConcurrentHashMap<RepeatQuests, Short> repeatMap =
                repeatQuestMap.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>());
        ConcurrentHashMap<ArchivementQuests, Boolean> archiveMap =
                AchievementQuestMap.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>());

        for (StoryQuests quest : StoryQuests.values()) {
            storyMap.putIfAbsent(quest, false);
        }

        for (RepeatQuests quest : RepeatQuests.values()) {
            repeatMap.putIfAbsent(quest, (short) 0);
        }

        for (ArchivementQuests quest : ArchivementQuests.values()) {
            archiveMap.putIfAbsent(quest, false);
        }

    }
}
