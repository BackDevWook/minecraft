package com.example.expertplugin;

import com.example.expertplugin.manager.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.jdbc.core.JdbcTemplate;

public final class ExpertPlugin extends JavaPlugin {

    @Getter
    private static ExpertPlugin instance;

    @Override
    public void onEnable() {

        // 정적 플러그인
        instance = this;

        getLogger().info("Expert Plugin 실행");

        // 커맨드
        CommandManager.registerAllCommands();
        getLogger().info("CommandManger 등록 완료");

        // 이벤트
        EventManager eventManager = new EventManager(this);
        getLogger().info("EventManager 등록 완료");
        eventManager.registerAllListeners(this);

        // 퀘스트
        QuestManager questManager = new QuestManager();
        getLogger().info("QuestManager 등록 완료");
        questManager.init();

        // NPC
        NpcManager npcManager = new NpcManager();
        getLogger().info("NpcManager 등록 완료");
        npcManager.SpawnAllNpcs();

        // 유저
        UserManager userManager = new UserManager();
        getLogger().info("UserManager 등록 완료");
        userManager.init();
    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인 실행 종료");
    }
}
