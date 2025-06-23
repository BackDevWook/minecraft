package com.example.expertplugin;

import com.example.expertplugin.manager.CommandManager;
import com.example.expertplugin.manager.EventManager;
import com.example.expertplugin.manager.QuestManager;
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
        eventManager.registerAllListeners(this);

        // 퀘스트
        QuestManager questManager = new QuestManager();
        questManager.loadQuests();

    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인 실행 종료");
    }
}
