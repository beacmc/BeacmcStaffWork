package com.beacmc.beacmcstaffworkv2.listener;

import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;
import com.beacmc.beacmcstaffworkv2.manager.Config;
import com.beacmc.beacmcstaffworkv2.manager.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class StaffListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player || event.getEntity() instanceof Player) {
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
}
