package com.example.expertplugin.manager.server;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.command.SelectMode;
import com.example.expertplugin.command.SetOp;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CommandManager {

    public static void registerAllCommands() {

        JavaPlugin plugin = ExpertPlugin.getInstance();

        Objects.requireNonNull(plugin.getCommand("mode")).setExecutor(new SelectMode()); // 게임 모드 변경
        Objects.requireNonNull(plugin.getCommand("op")).setExecutor(new SetOp()); // op 권한 설정

    }
}
