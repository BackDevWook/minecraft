package com.example.expertplugin.event.load;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.manager.user.QuestManager;
import com.example.expertplugin.mysql.MySqlUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CreatePlayerData implements Listener {

    private final JavaPlugin plugin = ExpertPlugin.getInstance();

    @EventHandler
    public void createPlayerData(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            Connection conn = null;

            try  {
                conn = MySqlUtil.getConnection();
                conn.setAutoCommit(false);

                // 유저 데이터가 이미 있다면 무시하고 없다면 새로 생성합니다.
                if (isExistUserData(conn, uuid.toString())) {
                    conn.rollback();
                    return;
                }

                // users 테이블
                String insertSql = """
                        INSERT INTO expert_plugin.users (uuid, user_name)
                        VALUES (?, ?)
                        """;

                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setString(1, uuid.toString());
                    pstmt.setString(2, player.getName());
                    pstmt.executeUpdate();
                }

                // users_eco 테이블
                String ecoSql = """
                        INSERT INTO expert_plugin.users_eco (uuid) VALUES (?)
                        """;

                try(PreparedStatement pstmt = conn.prepareStatement(ecoSql)) {
                    pstmt.setString(1, uuid.toString());
                    pstmt.executeUpdate();
                }

                // users_rpg 테이블
                String rpgSql = """
                        INSERT INTO expert_plugin.users_rpg (uuid) VALUES (?)
                        """;

                try(PreparedStatement pstmt = conn.prepareStatement(rpgSql)) {
                    pstmt.setString(1, uuid.toString());
                    pstmt.executeUpdate();
                }

                // users_play_time 테이블
                String playTimeSql = """
                        INSERT INTO expert_plugin.users_play_time (uuid) VALUES (?)
                        """;

                try(PreparedStatement pstmt = conn.prepareStatement(playTimeSql)) {
                    pstmt.setString(1, uuid.toString());
                    pstmt.executeUpdate();
                }

                // users_quest 테이블
                String questSql = """
                        INSERT INTO expert_plugin.users_quest (uuid) VALUES (?)
                        """;

                try(PreparedStatement pstmt = conn.prepareStatement(questSql)) {
                    pstmt.setString(1, uuid.toString());
                    pstmt.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException rollBackEx) {
                        rollBackEx.printStackTrace();
                    }
                }
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException closeEx) {
                        closeEx.printStackTrace();
                    }
                }
            }
        });

    }

    public boolean isExistUserData(Connection conn, String uuid) throws SQLException {

        String checkSql = """
                        SELECT 1
                        FROM expert_plugin.users
                        WHERE uuid = ?
                        """;

        try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setString(1, uuid.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @EventHandler
    public void createQuestMap(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        QuestManager.init(player.getUniqueId());
    }
}
