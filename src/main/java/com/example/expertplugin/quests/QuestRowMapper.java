package com.example.expertplugin.quests;


import com.example.expertplugin.table.enums.QuestTypes;
import com.example.expertplugin.table.enums.ScopeTypes;
import com.example.expertplugin.table.quest.Quest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class QuestRowMapper implements RowMapper<Quest> {

    ObjectMapper objectMapper = new ObjectMapper();

    // 파싱
    @Override
    public @Nullable Quest mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            QuestTypes questType = QuestTypes.valueOf(rs.getString("quest_type"));
            ScopeTypes scopeType = ScopeTypes.valueOf(rs.getString("scope_type"));
            boolean isRepeatable = rs.getBoolean("is_repeatable");
            Short dailyRepeatLimit = rs.getShort("daily_repeat_limit");

            // json
            String rewardsJson = rs.getString("rewards");
            String missionsJson = rs.getString("missions");

            LinkedHashMap<String, Integer> rewards = objectMapper.readValue(rewardsJson, new TypeReference<>() {});
            LinkedHashMap<String, Integer> missions = objectMapper.readValue(missionsJson, new TypeReference<>() {});

            return new Quest(id, name, description, questType, scopeType, isRepeatable, dailyRepeatLimit, rewards, missions);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
