package com.example.expertplugin.command;

import com.example.expertplugin.manager.QuestManager;
import com.example.expertplugin.table.quest.Quest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class QuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            viewQuestList(player);
            return true;
        } else if (args.length == 1) {
            long num = Long.parseLong(args[0]);
            viewQuest(player, num);
            return true;
        }

        return true;
    }

    public void viewQuestList(Player player) {
        Map<Long, Quest> map = QuestManager.getQuestMap();
        for (Quest quest : map.values()) {
            player.sendMessage(quest.getId() + ". " + quest.getName() + " | " + quest.getDescription());
        }
    }

    public void viewQuest(Player player, long num) {
        Map<Long, Quest> map = QuestManager.getQuestMap();
        Quest quest = map.get(num);
        String rewards = quest.getRewards().stream()
                .map(reward -> reward.getTarget() + " : " + reward.getValue())
                .collect(Collectors.joining(", "));

        player.sendMessage("");
        player.sendMessage(num + ". " + quest.getName());
        player.sendMessage("");
        player.sendMessage(quest.getDescription());
        player.sendMessage("");
        player.sendMessage("보상 >>  " + rewards);

    }

}
