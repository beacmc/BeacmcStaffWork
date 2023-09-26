package com.beacmc.beacmcstaffworkv2.manager;


import com.beacmc.beacmcstaffworkv2.BeacmcStaffWork;

import java.util.List;

public class Config {

    private static BeacmcStaffWork plugin = BeacmcStaffWork.getInstance();

    public static String getString(String path) {
        return plugin.getConfig().getString(path);
    }

    public static boolean getBoolean(String path) {
        return plugin.getConfig().getBoolean(path);
    }

    public static List<String> getStringList(String path) {
        return plugin.getConfig().getStringList(path);
    }
}
