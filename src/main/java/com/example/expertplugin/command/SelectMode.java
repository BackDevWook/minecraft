package com.example.expertplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectMode implements CommandExecutor {

    /**
     * 게임 모드를 변경할 수 있습니다.
     * 커맨드 : /mode <MODE_NUMBER>
     * MODE_NUMBER {
     *      1 : 서바이벌 모드
     *      2 : 크리에이티브 모드
     *      }
     * 플레이어만 사용 가능합니다.
     * OP 권한이 필요합니다.
     * */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // 플레이어가 사용했는가
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
            return true;
        }

        // OP 인가
        if (!(player.isOp())) {
            player.sendMessage(ChatColor.RED + "해당 명령어는 OP 권한이 있어야 합니다.");
            return true;
        }

        switch (args[0]) {
            case "1" -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("서바이벌 모드로 변경되었습니다.");
            }
            case "2" -> {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("크리에이티브 모드로 변경되었습니다.");
            }
            default -> {
                return false;
            }
        }

        return args.length == 1;
    }
}
