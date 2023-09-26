package com.beacmc.beacmcstaffwork.commands;

import com.beacmc.beacmcstaffwork.BeacmcStaffWork;
import com.beacmc.beacmcstaffwork.manager.Command;
import com.beacmc.beacmcstaffwork.manager.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffAdminCommand extends Command {
    public StaffAdminCommand() {
        super("staffworkadmin");
    }

    @Override
    public void execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        User user = new User((Player) sender, BeacmcStaffWork.getInstance());

        if(user.hasPermission("beacmcstaffwork.admin")) {
            user.sendMessage("settings.messages.no-permission");
            return;
        }

        if (args.length < 1 || args.length > 1) {
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            BeacmcStaffWork.getInstance().reloadConfig();
            user.sendMessage("settings.messages.reload");
        }
    }
}
