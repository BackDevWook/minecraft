package com.example.expertplugin.manager;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.db.MySqlUtil;
import com.example.expertplugin.table.enums.EntityType;
import com.example.expertplugin.table.enums.QuestTypes;
import com.example.expertplugin.table.enums.ScopeTypes;
import com.example.expertplugin.table.quest.Quest;
import com.example.expertplugin.table.quest.QuestObjectives;
import com.example.expertplugin.table.quest.QuestRewards;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class QuestManager {

    @Getter
    private static Map<Long, Quest> questMap = new HashMap<>(); // 서버 퀘스트 맵


    private final JavaPlugin plugin = ExpertPlugin.getInstance();

    public void init() {

        Map<Long, Quest> tempMap = new ConcurrentHashMap<>();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            String questSql = """
                    SELECT * FROM expert_plugin.quests
                    """;
            String rewardSql = """
                    SELECT * FROM expert_plugin.quest_rewards
                    """;
            String objectiveSql = """
                    SELECT * FROM expert_plugin.quest_objectives
                    """;

            try (Connection conn = MySqlUtil.getConnection();
                 PreparedStatement questPstmt = conn.prepareStatement(questSql);
                 PreparedStatement rewardPstmt = conn.prepareStatement(rewardSql);
                 PreparedStatement objectivePstmt = conn.prepareStatement(objectiveSql);
                 ResultSet questRs = questPstmt.executeQuery();
                 ResultSet rewardRs = rewardPstmt.executeQuery();
                 ResultSet objectiveRs = objectivePstmt.executeQuery();) {

                while (questRs.next()) {
                    Quest quest = new Quest(
                            questRs.getLong("id"),
                            questRs.getString("name"),
                            QuestTypes.valueOf(questRs.getString("quest_type")),
                            ScopeTypes.valueOf(questRs.getString("scope_type")),
                            questRs.getString("description"),
                            questRs.getBoolean("is_repeatable"),
                            questRs.getShort("daily_repeat_limit")
                    );
                    tempMap.put(quest.getId(), quest);
                }

                while (rewardRs.next()) {
                    Long questId = rewardRs.getLong("quest_id");
                    Quest quest = tempMap.get(questId);
                    if (quest != null) {
                        quest.addReward(new QuestRewards(
                                rewardRs.getLong("id"),
                                tempMap.get(questId),
                                EntityType.valueOf(rewardRs.getString("type")),
                                rewardRs.getString("target"),
                                rewardRs.getInt("value")
                        ));
                    }
                }

                while (objectiveRs.next()) {
                    Long questId = objectiveRs.getLong("quest_id");
                    Quest quest = tempMap.get(questId);
                    if (quest != null) {
                        quest.addObjective(new QuestObjectives(
                                objectiveRs.getLong("id"),
                                tempMap.get(questId),
                                EntityType.valueOf(objectiveRs.getString("type")),
                                objectiveRs.getString("target"),
                                objectiveRs.getInt("required_count")
                        ));
                    }
                }

                Bukkit.getScheduler().runTask(plugin, () -> {
                    questMap = new HashMap<>(tempMap);
                    plugin.getLogger().info("[QUEST_LOG] 퀘스트 로딩 완료, 총 " + questMap.size() + "개 로드됨.");
                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
