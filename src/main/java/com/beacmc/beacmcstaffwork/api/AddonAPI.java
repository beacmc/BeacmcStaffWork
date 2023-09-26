package com.beacmc.beacmcstaffwork.api;

import com.beacmc.beacmcstaffwork.api.manager.CustomAddonHook;

import java.util.HashMap;
import java.util.Map;

public class AddonAPI {

    private static Map<String, CustomAddonHook> addons;

    public static boolean registerAddonHook(final CustomAddonHook addon) {
        if(addon.getCommand() == null || addon == null || AddonAPI.addons.containsKey(addon.getCommand())) {
            return false;
        }
        AddonAPI.addons.put(addon.getCommand(), addon);
        return true;
    }

    public static Map<String, CustomAddonHook> getAddons() {
        return AddonAPI.addons;
    }

    static {
        AddonAPI.addons = new HashMap<String, CustomAddonHook>();
    }
}
