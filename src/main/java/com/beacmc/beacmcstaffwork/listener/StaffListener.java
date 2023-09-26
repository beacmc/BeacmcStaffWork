package com.beacmc.beacmcstaffwork.listener;

import com.beacmc.beacmcstaffwork.BeacmcStaffWork;
import com.beacmc.beacmcstaffwork.api.events.PlayerEnableWorkEvent;
import com.beacmc.beacmcstaffwork.discord.Builder;
import com.beacmc.beacmcstaffwork.manager.Color;
import com.beacmc.beacmcstaffwork.manager.Config;
import com.beacmc.beacmcstaffwork.manager.UpdateChecker;
import com.beacmc.beacmcstaffwork.manager.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class StaffListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            User damager = new User((Player) event.getDamager(), BeacmcStaffWork.getInstance());
            User entity = new User((Player) event.getEntity(), BeacmcStaffWork.getInstance());
            User.Data data = new User.Data();


            if (Config.getBoolean("settings.work.disable-entity-damage")) {
                if (data.contains(damager.getID())) {
                    event.setCancelled(true);
                    damager.sendMessage("settings.messages.entity-damage-on-work");
                }
                else if(data.contains(entity.getID())) {
                    event.setCancelled(true);
                    entity.sendMessage("settings.messages.damager-damage-on-work");
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        User player = new User(event.getPlayer(), BeacmcStaffWork.getInstance());
        if(Config.getBoolean("settings.update-check")) {
            if (player.hasPermission("beacmcstaffwork.update")) {
                String latestVersion = UpdateChecker.start();



                if(latestVersion != BeacmcStaffWork.getInstance().getDescription().getVersion()) {
                    List<String> list = Config.getStringList("settings.messages.update-check-player");
                    for (String execute : list) {
                        event.getPlayer().sendMessage(
                                Color.compile(execute
                                        .replace("{current_version}", BeacmcStaffWork.getInstance().getDescription().getVersion())
                                        .replace("{latest_version}", latestVersion)
                                ));
                    }
                }
            }
        }
    }


    @EventHandler
    public void onEnableWork(PlayerEnableWorkEvent event) {
        if(BeacmcStaffWork.getInstance().getConfig().getBoolean("settings.discord.enable")) {
            new Builder().sendNotification(event.getPlayer(), "enable");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        User user = new User(event.getPlayer(), BeacmcStaffWork.getInstance());
        User.Data data = new User.Data();

        if(Config.getBoolean("settings.work.disable-place-block")) {
            if(data.contains(user.getID())) {
                event.setCancelled(true);
                user.sendMessage("settings.messages.block-place-on-work");
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        User user = new User(event.getPlayer(), BeacmcStaffWork.getInstance());
        User.Data data = new User.Data();

        if(Config.getBoolean("settings.work.disable-break-block")) {
            if(data.contains(user.getID())) {
                event.setCancelled(true);
                user.sendMessage("settings.messages.block-break-on-work");
            }
        }
    }
}
