package com.example.expertplugin.manager.user;

import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class GoldManager {

    private static final ConcurrentHashMap<UUID, Integer> goldMap = new ConcurrentHashMap<>();


    // 골드 증가
    public void increase(UUID uuid, int amount) {
        goldMap.put(uuid, goldMap.get(uuid) + amount);
    }

    // 골드 감소
    public void decrease(UUID uuid, int amount) {
        goldMap.put(uuid, goldMap.get(uuid) - amount);
    }

    // 기본 골드 초기화
    public void init(UUID uuid, int amount) {
        goldMap.put(uuid, 0);
    }

    // 골드 변경
    public void change(UUID uuid, int amount) {
        goldMap.put(uuid, amount);
    }

    // 골드 조회
    public int get(UUID uuid) {
        return goldMap.get(uuid);
    }

}
