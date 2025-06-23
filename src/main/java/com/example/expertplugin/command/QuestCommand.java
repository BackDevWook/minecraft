package com.example.expertplugin.command;

import com.example.expertplugin.manager.QuestManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommand implements CommandExecutor {

    /**
     * 퀘스트 관련 커맨드 입니다.
     * 커맨드 : /quest , /quest <index>
     * <index> : 퀘스트 번호
     * 플레이어만 사용 가능합니다.
     * */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            QuestManager.viewQuestList(player);
        } else if (args.length == 1 && Integer.parseInt(args[0]) >= 1) {
            QuestManager.viewQuest(player, Integer.parseInt(args[0]));
        } else {
            player.sendMessage("잘못된 입력입니다.");
            return true;
        }

        return true;
    }
}
