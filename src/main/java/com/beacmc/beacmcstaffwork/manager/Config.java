package com.beacmc.beacmcstaffwork.manager;


import com.beacmc.beacmcstaffwork.BeacmcStaffWork;

import java.util.List;

public class Config {


    public static String getString(String path) {
        return BeacmcStaffWork.getInstance().getConfig().getString(path);
    }

    public static boolean getBoolean(String path) {
        return BeacmcStaffWork.getInstance().getConfig().getBoolean(path);
    }

    public static List<String> getStringList(String path) {
        return BeacmcStaffWork.getInstance().getConfig().getStringList(path);
    }
}
