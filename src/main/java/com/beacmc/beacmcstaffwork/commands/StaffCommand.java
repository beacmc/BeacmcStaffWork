package com.beacmc.beacmcstaffworkv2.commands;

import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;
import com.beacmc.beacmcstaffworkv2.discord.Builder;
import com.beacmc.beacmcstaffworkv2.manager.Actions;
import com.beacmc.beacmcstaffworkv2.manager.Command;
import com.beacmc.beacmcstaffworkv2.manager.Config;
import com.beacmc.beacmcstaffworkv2.manager.User;
import net.luckperms.api.LuckPerms;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand extends Command {

    LuckPerms luckPerms;

    BeacmcStaffWork plugin = BeacmcStaffWork.getInstance();


    public StaffCommand(LuckPerms luckPerms) {
        super("staffwork");
        this.luckPerms = luckPerms;
    }

    public void execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return;
        com.beacmc.beacmcstaffworkv2.manager.User moderator = new User((Player) sender, BeacmcStaffWork.getInstance());
        User.Data data = new User.Data();
        if(!moderator.hasPermission("staffwork.use")) {
            moderator.sendMessage("settings.messages.no-permission");
            return;
        }

        if (args.length < 1 || args.length > 2) {
            moderator.sendMessageList("settings.messages.help");
            return;
        }

        if(args[0].equalsIgnoreCase("on")) {
            if (data.contains(moderator.getID())) {
                moderator.sendMessage("settings.messages.already-worked");
                return;
            } else {
                if(Config.getString("settings.actions.groups-on-work." + moderator.getPrimaryGroup()) == null) {
                    moderator.sendMessage("settings.messages.no-group");
                    return;
                }
                else {
                    data.addContains(moderator.getID());
                    moderator.sendMessage("settings.messages.on-enable-work");

                    if(plugin.getConfig().getBoolean("settings.discord.enable")) {
                        new Builder().sendNotification(moderator.getPlayer(), "enable");
                    }

                    Actions.start(Config.getStringList("settings.actions.groups-on-work." + moderator.getPrimaryGroup()), (Player) sender);
                    return;
                }
            }
        }
        else if (args[0].equalsIgnoreCase("off")) {
            if (data.contains(moderator.getID())) {
                if(Config.getString("settings.actions.groups-off-work." + moderator.getPrimaryGroup()) == null) {
                    moderator.sendMessage("settings.messages.no-group");
                    return;
                }
                else {
                    data.removeContains(moderator.getID());
                    moderator.sendMessage("settings.messages.on-disable-work");
                    if(plugin.getConfig().getBoolean("settings.discord.enable")) {
                        new Builder().sendNotification(moderator.getPlayer(), "disable");
                    }

                    Actions.start(Config.getStringList("settings.actions.groups-off-work." + moderator.getPrimaryGroup()), (Player) sender);
                    return;
                }
            } else {
                moderator.sendMessage("settings.messages.not-worked");
                return;
            }
        }
    }
}

