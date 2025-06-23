package com.example.expertplugin.manager;

import com.example.expertplugin.event.join.FirstJoinCreateUserData;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final List<Listener> listeners = new ArrayList<Listener>();

    public EventManager(JavaPlugin plugin) {
        listeners.add(new FirstJoinCreateUserData());
    }

    public void registerAllListeners(JavaPlugin plugin) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            Bukkit.getLogger().info("[" + listener.toString() + "] 리스너 적용 완료");
        }
    }
}
