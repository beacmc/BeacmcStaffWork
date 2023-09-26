package com.beacmc.beacmcstaffwork.manager;

import com.beacmc.beacmcstaffwork.BeacmcStaffWork;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Work {

    public static void messageToModerator(Player player, String message){

        if(player == null || message == null) return;

        message = PlaceholderAPI.setPlaceholders(player, message);

        for (Player execute : Bukkit.getOnlinePlayers()) {
            if (execute.hasPermission("beacmcstaffwork.view")) {
                execute.sendMessage(
                        Color.compile(message)
                );
            }
        }
    }

    private static File file = new File("plugins/BeacmcStaffWork/data.yml");
    private static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);


    private static long getLong(String path) {
        return configuration.getLong(path);
    }

    private static long getTime(String player) {
        configuration = YamlConfiguration.loadConfiguration(file);
        if (String.valueOf(getLong("data." + player)) != null) {
            return getLong("data." + player);
        }

        else {
            return 0;
        }
    }

    public static String getTimeFormat(String player) {
        long totalSeconds = getTime(player);



        long days = totalSeconds / (24 * 60 * 60);
        long hours = (totalSeconds % (24 * 60 * 60)) / (60 * 60);
        long minutes = (totalSeconds % (60 * 60)) / 60;
        long seconds = totalSeconds % 60;

        String path = BeacmcStaffWork.getInstance().getConfig().getString("settings.placeholderapi.placeholders.time-in-work");
        String replace = path
                .replace("{days}", String.valueOf(days))
                .replace("{hours}", String.valueOf(hours))
                .replace("{minutes}", String.valueOf(minutes))
                .replace("{seconds}", String.valueOf(seconds));
        return replace;
    }
}
