package com.example.expertplugin.manager;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.db.MySqlUtil;
import com.example.expertplugin.table.enums.QuestTypes;
import com.example.expertplugin.table.enums.ScopeTypes;
import com.example.expertplugin.table.quest.Quest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class QuestManager {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Getter
    private static List<Quest> questList = List.of();


    public void loadQuests() {

        Bukkit.getScheduler().runTaskAsynchronously(ExpertPlugin.getInstance(), () -> {
            List<Quest> tempList = new ArrayList<>();

            String sql = """
                SELECT * FROM expert_plugin.quests
                """;

            try (Connection conn = MySqlUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        tempList.add(new Quest(
                                        rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        QuestTypes.valueOf(rs.getString("quest_type")),
                                        ScopeTypes.valueOf(rs.getString("scope_type")),
                                        rs.getBoolean("is_repeatable"),
                                        rs.getShort("daily_repeat_limit"),
                                        parseJsonToMap(rs.getString("rewards")),
                                        parseJsonToMap(rs.getString("missions"))
                                )
                        );
                    }
                    questList = List.copyOf(tempList);
                    Bukkit.getLogger().info(questList.size() + "개의 퀘스트를 불러왔습니다.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // json -> map 파싱
    private LinkedHashMap<String, Integer> parseJsonToMap(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 리스트 조회
    public static void viewQuestList(Player player) {

        AtomicInteger number = new AtomicInteger(1);

        for (Quest quest : questList) {
            player.sendMessage(ChatColor.YELLOW + "[" + number.getAndIncrement() + "] " + quest.getName() + " - " + quest.getDescription());
        }
    }

    // 단일 퀘스트 조회
    public static void viewQuest(Player player, int num) {

        if (questList.get(num) == null) {
            player.sendMessage("존재하지 않는 퀘스트입니다.");
            return;
        }

        Quest quest = questList.get(num);

        player.sendMessage(
                ChatColor.GREEN + "[" + num + "] " + quest.getName() + "\n\n"

                + quest.getDescription() + "\n\n"

                + quest.getRewards() + "\n"
                + quest.getMission()
        );
    }
}

