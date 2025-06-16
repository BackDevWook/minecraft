package com.example.expertplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetOp implements CommandExecutor {

    /**
     * 플레이어에게 OP 권한을 설정합니다.
     * 커맨드 : /op <USER_NAME>
     * USER_NAME : 플레이어의 닉네임
     * 콘솔에서 사용 가능합니다.
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // 콘솔에서 입력했는가
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 콘솔에서만 입력 가능합니다.");
            return true;
        }

        Player player = Bukkit.getPlayerExact(args[0]);

        // 이미 OP인 경우 해제, 아닌 경우 OP 권한 부여
        Objects.requireNonNull(player)
                .setOp(!Objects.requireNonNull(player).isOp());

        return args.length == 1;
    }
}
