package com.beacmc.beacmcstaffworkv2;

import com.beacmc.beacmcstaffworkv2.commands.StaffCommand;
import net.luckperms.api.LuckPerms;
import com.beacmc.beacmcstaffworkv2.data.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public final class BeacmcStaffWork extends JavaPlugin {


    public LuckPerms luckPerms;
    public HashSet<UUID> staff = new HashSet<>();
    private static BeacmcStaffWork instance;



    @Override
    public void onEnable() {

        this.luckPerms = this.getServer().getServicesManager().load(LuckPerms.class);
        instance = this;
        new StaffCommand(this.luckPerms);
        saveDefaultConfig();

        if(this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Placeholder papi = new Placeholder();
            papi.load();
        }

        if(!this.getServer().getPluginManager().isPluginEnabled("LuckPerms")) {
            this.getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        instance = null;
    }


    public static BeacmcStaffWork getInstance() {
        return instance;
    }


}
