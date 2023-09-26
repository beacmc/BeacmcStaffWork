package com.beacmc.beacmcstaffworkv2.manager;

import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;
import net.luckperms.api.LuckPerms;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {


    private final LuckPerms luckPerms = BeacmcStaffWork.getInstance().luckPerms;

    private final Player player;
    private static BeacmcStaffWork plugin;

    public User(Player player, JavaPlugin plugin) {
        this.player = player;
        this.plugin = (BeacmcStaffWork) plugin;
    }



    public String getName() {
        return player.getName();
    }

    public UUID getID() {
        return player.getUniqueId();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean hasPermission(String perm) {
        return player.hasPermission(perm);
    }

    public String getPrimaryGroup() {
        net.luckperms.api.model.user.User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        return user.getPrimaryGroup();
    }

    public void sendMessage(String path) {
        player.sendMessage(Color.compile(Config.getString(path)
                .replace("{PREFIX}", Config.getString("settings.prefix"))
        ));
    }
    public void sendMessageList(String path) {
        ArrayList<String> lines = new ArrayList<>(Config.getStringList(path));
        for (String execute : lines) {
            player.sendMessage(Color.compile(execute));
        }
    }

    public static final class Data {

        public boolean contains(UUID uuid) {
            return plugin.staff.contains(uuid);
        }

        public void addContains(UUID uuid) {
            plugin.staff.add(uuid);
        }

        public void removeContains(UUID uuid) {
            plugin.staff.remove(uuid);
        }
    }
}
