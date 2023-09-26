package com.beacmc.beacmcstaffworkv2.data;

import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;
import com.beacmc.beacmcstaffworkv2.manager.Color;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {

    private static BeacmcStaffWork plugin = BeacmcStaffWork.getInstance();


    public void load() {
        register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "beacmcstaffwork";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public @NotNull boolean canRegister() {
        return true;
    }

    public String onPlaceholderRequest(Player player, String params) {
        assert player != null;
        if (params.equals("moderators_on_work")) {
            return String.valueOf(plugin.staff.size());
        }
        else if (params.equals("player_work")) {

            if (plugin.staff.contains(player.getUniqueId())) {
                return Color.compile(BeacmcStaffWork.getInstance().getConfig().getString("settings.placeholderapi.placeholders.on-work"));
            } else {
                return Color.compile(BeacmcStaffWork.getInstance().getConfig().getString("settings.placeholderapi.placeholders.not-work"));
            }
        }
        else {
            return "Неизвестный заполнитель";
        }
    }
}
