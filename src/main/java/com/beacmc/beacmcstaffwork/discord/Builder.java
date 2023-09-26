package com.beacmc.beacmcstaffwork.discord;


import com.beacmc.beacmcstaffwork.BeacmcStaffWork;
import com.beacmc.beacmcstaffwork.manager.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.entity.Player;

import java.awt.*;

public class Builder implements EventListener {

    public static Builder instance = null;

    public static Builder getInstance() {
        if (instance == null) {
            new Builder();
        }
        return instance;
    }


    private final JDA jda = JDABuilder.createDefault(Config.getString("settings.discord.token"))
            .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_PRESENCES)
            .build();

    private final Guild guild;
    private TextChannel channel;


    public Builder() {
        try {
            this.jda.awaitReady();

        } catch (InterruptedException e) { }

        this.guild = jda.getGuildById(BeacmcStaffWork.getInstance().getConfig().getLong("settings.discord.guild-id"));

        if (guild == null) {
            BeacmcStaffWork.getInstance().getLogger().severe("ID сообщества не является корректным");
            this.instance = null;
        } else {
            this.channel = guild.getTextChannelById(BeacmcStaffWork.getInstance().getConfig().getLong("settings.discord.channel-id"));

            if(this.channel == null) {
                BeacmcStaffWork.getInstance().getLogger().severe("ID канала не является корректным");
                instance = null;
            }
        }
    }

    public void sendNotification(Player player, String status) {
        if (player != null) {
            assert guild != null;

            BeacmcStaffWork plugin = BeacmcStaffWork.getInstance();

            String title = plugin.getConfig().getString("settings.discord.on-" + status + "-work.title");
            String description = plugin.getConfig().getString("settings.discord.on-" + status + "-work.description");

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(PlaceholderAPI.setPlaceholders(player, title));
            embed.setDescription(PlaceholderAPI.setPlaceholders(player, description));

            String color = plugin.getConfig().getString("settings.discord.on-" + status + "-work.color");
            Color decode = Color.decode(color);

            embed.setColor(decode);

            channel.sendMessageEmbeds(embed.build()).queue();
        }
    }


    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Бот запущен!");
            instance = this;
        }
    }

}
