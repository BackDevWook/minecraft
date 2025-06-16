package com.example.expertplugin;

import com.example.expertplugin.manager.server.CommandManager;
import com.example.expertplugin.manager.server.EventManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

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

        // 이벤트
        EventManager eventManager = new EventManager(this);
        eventManager.registerAllListeners(this);

    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인 실행 종료");
    }
}
