package com.example.expertplugin.manager;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.db.MySqlUtil;
import com.example.expertplugin.table.enums.UserRoles;
import com.example.expertplugin.table.user.User;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    @Getter
    private static Map<UUID, User> userMap = new HashMap<>();

    private final JavaPlugin plugin = ExpertPlugin.getInstance();

    public void init() {

        Map<UUID, User> tempMap = new HashMap<>();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            String sql = """
                    SELECT * FROM expert_plugin.users
                    """;

            try (Connection conn = MySqlUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    UUID uuid = UUID.fromString(rs.getString("uuid"));
                    tempMap.put(uuid, new User(
                            rs.getInt("id"),
                            rs.getString("user_name"),
                            UserRoles.valueOf(rs.getString("role")),
                            rs.getInt("gold"),
                            UUID.fromString(rs.getString("uuid"))
                    ));
                }

                Bukkit.getScheduler().runTask(plugin, () -> {
                    userMap = new HashMap<>(tempMap);
                    plugin.getLogger().info("User 정보 동기화 완료");
                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
