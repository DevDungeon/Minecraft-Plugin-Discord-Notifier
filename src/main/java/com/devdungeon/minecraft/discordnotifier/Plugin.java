package com.devdungeon.minecraft.discordnotifier;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    // This code is called after the server starts and after the /reload command
    @Override
    public void onEnable() {
        saveDefaultConfig();
        String discordWebhook = this.getConfig().getString("discord-webhook");
        String discordBotToken = this.getConfig().getString("discord-bot-token");
        String primaryChannelName = this.getConfig().getString("discord-primary-channel-id");

        Discord.setDiscordWebhook(discordWebhook);

        Thread discordBotThread = new Thread(new DiscordBot(this, discordBotToken, primaryChannelName));
        discordBotThread.start();

        getLogger().log(Level.INFO, "{0}.onEnable()", this.getClass().getName());
        getLogger().log(Level.INFO, "Initializing Discord Notifier...");
        Bukkit.getPluginManager().registerEvents(
                new com.devdungeon.minecraft.discordnotifier.EventListener(),
                this
        );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hello")) { // your command name
            if (!(sender instanceof Player)) //this makes sure its a player executing the command
                return true;
        } else {
            return false;
        }

        return true;
    }

    // This code is called before the server stops and after the /reload command
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }


}
