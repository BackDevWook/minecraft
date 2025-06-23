package com.example.expertplugin.event.join;

import com.example.expertplugin.ExpertPlugin;
import com.example.expertplugin.db.MySqlUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Consumer;

public class FirstJoinCreateUserData implements Listener {

    private final JavaPlugin plugin = ExpertPlugin.getInstance();

    /**
     * 서버 내에 유저의 데이터가 없다면(최초 접속) 서버 데이터를 초기화 하고
     * 이미 있는 유저라면 생략합니다.
     * @param event
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        isExist(player, exist -> {
            if (exist) return;

            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

                String query = """
                    INSERT INTO expert_plugin.users (name, uuid)
                    VALUES (?, ?)
                    """;
                String query2 = """
                     INSERT INTO expert_plugin.users_eco (user_id)
                    VALUES (?); 
                    """;
                String query3 = """
                     INSERT INTO expert_plugin.users_rpg (user_id)
                    VALUES (?);
                    """;
                String query4 = """
                    INSERT INTO expert_plugin.users_play_time (user_id)
                    VALUES (?);
                    """;
                String findById = """
                    SELECT id FROM expert_plugin.users WHERE uuid = ?;
                    """;

                try (Connection conn = MySqlUtil.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(query)) {

                    pstmt.setString(1, player.getName());
                    pstmt.setString(2, uuid.toString());
                    pstmt.executeUpdate();

                    try(PreparedStatement pstmt2 = conn.prepareStatement(findById)) {

                        pstmt2.setString(1, player.getUniqueId().toString());

                        try(ResultSet rs = pstmt2.executeQuery()) {
                            if (rs.next()) {
                                int id = rs.getInt(1);
                                try (PreparedStatement pstmt3 = conn.prepareStatement(query2);
                                     PreparedStatement pstmt4 = conn.prepareStatement(query3);
                                     PreparedStatement pstmt5 = conn.prepareStatement(query4)
                                ) {
                                    pstmt3.setInt(1, id);
                                    pstmt4.setInt(1, id);
                                    pstmt5.setInt(1, id);
                                    pstmt3.executeUpdate();
                                    pstmt4.executeUpdate();
                                    pstmt5.executeUpdate();
                                }
                            } else {
                                throw new IllegalArgumentException("사용자 UUID 로 ID를 찾을 수 없습니다." + uuid);
                            }
                        }
                    }

                    Bukkit.getLogger().info("[ " + player.getName() + " ]" + "님께서 서버에 최초 입장하셨습니다.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
        });


    }

    public void isExist(Player player, Consumer<Boolean> callback) {

        String uuid = player.getUniqueId().toString();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            String checkSql = """
                    SELECT 1 FROM expert_plugin.users
                    WHERE uuid = ?
                    """;

            try (Connection conn = MySqlUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(checkSql)) {

                pstmt.setString(1, uuid);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        callback.accept(true);
                        return;
                    };
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            callback.accept(false);
        });
    }

}
