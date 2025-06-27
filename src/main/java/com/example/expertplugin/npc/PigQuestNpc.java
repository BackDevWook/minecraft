package com.example.expertplugin.npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.Objects;

public class PigQuestNpc {

    public static void spawnNpc() {
        Location loc = new Location(Bukkit.getWorld("world"), 5001, 72, 4254);
        Villager npc = (Villager) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.VILLAGER);

        npc.setCustomName("&a돼지 두꺼비 서윤상"); // 이름 설정
        npc.setCustomNameVisible(true); // 이름 보이게
        npc.setAI(false); // 움직이지 않음
        npc.setInvulnerable(true); // 무적 상태로 만듬
        npc.setProfession((Villager.Profession.FARMER)); // 외형 변경
        npc.setPersistent(true); // 서버가 리로드되거나 청소될 때도 삭제되지 않도록 영구 존재하게 설정
        npc.setSilent(true);

        Location sub = npc.getLocation().add(0, 2.3, 0); // 머리 위
        ArmorStand stand = Objects.requireNonNull(sub.getWorld()).spawn(sub, ArmorStand.class);
        stand.setInvisible(true);
        stand.setCustomName("퀘스트");
        stand.setCustomNameVisible(true);
        stand.setMarker(true);
        stand.setGravity(false);
        stand.setSmall(true);
    }
}
