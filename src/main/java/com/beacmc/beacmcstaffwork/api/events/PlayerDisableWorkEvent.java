package com.beacmc.beacmcstaffwork.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerDisableWorkEvent extends Event {
    public PlayerDisableWorkEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


    @Override
    public @NotNull HandlerList getHandlers(){
        return handlers;
    }


    public static HandlerList getHandlerList(){
        return handlers;
    }

    private Player player;
    private static final HandlerList handlers = new HandlerList();
}
