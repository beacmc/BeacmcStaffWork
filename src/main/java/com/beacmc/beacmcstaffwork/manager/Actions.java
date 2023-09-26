package com.beacmc.beacmcstaffworkv2.manager;

import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Actions {

    public static void start(List<String> list, Player player) {
        if (list.isEmpty() || list == null) return;
        User user = new User(player, BeacmcStaffWork.getInstance());

        for (int o = 0; o < list.size(); o++) {
            String[] splitText = list.get(o).split(" ");
            String action = splitText[0];
            String cmd = list.get(o).replace(action + " ", "");
            cmd = PlaceholderAPI.setPlaceholders(player, cmd);

            switch (action.toLowerCase()) {
                case "[message]": {
                    player.sendMessage(
                          Color.compile(cmd)
                    );
                    break;
                }
                case "[console]": {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    break;
                }
                case "[sound]": {
                    player.playSound(player.getLocation(), Sound.valueOf(cmd), 500.0f, 1.0f);
                    break;
                }
                case "[player]": {
                    Bukkit.dispatchCommand((CommandSender) player, cmd);
                    break;
                }
                case "[broadcast]": {
                    Bukkit.broadcastMessage(cmd);
                    break;
                }
            }
        }
    }
}
