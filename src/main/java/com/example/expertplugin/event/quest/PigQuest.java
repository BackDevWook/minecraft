package com.example.expertplugin.event.quest;

import com.example.expertplugin.manager.QuestManager;
import com.example.expertplugin.manager.UserManager;
import com.example.expertplugin.table.quest.Quest;
import com.example.expertplugin.table.user.User;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Map;
import java.util.UUID;

public class PigQuest implements Listener {

    @EventHandler
    public void getQuest(PlayerInteractEntityEvent event) {

        // 마을 주민인가?
        if (!(event.getRightClicked() instanceof Villager)) return;

        Villager npc = (Villager) event.getRightClicked();
        Map<Long, Quest> map = QuestManager.getQuestMap();

        // 해당 이름을 가진 NPC인가?
        if (!"&a돼지 두꺼비 서윤상".equals(npc.getCustomName())) return;
        event.setCancelled(true); // 상거래 방지 (원래 기능 없애는 거임)

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = UserManager.getUserMap().get(uuid);
        Quest quest = map.get(1);

        // 해당 퀘스트를 받지 않는 상태인가?
        if (!(user.getUserQuestProgresses().containsKey(quest))) {
            // TODO : 퀘스트 생성 로직
        }

        // 퀘스트를 이미 받은 상태라면?
        if (user.getUserQuestProgresses().containsKey(quest)) {
            // TODO : 현재 진행도 조회 로직
        }

        // 퀘스트 목표를 모두 달성한 상태라면?


    }
}
