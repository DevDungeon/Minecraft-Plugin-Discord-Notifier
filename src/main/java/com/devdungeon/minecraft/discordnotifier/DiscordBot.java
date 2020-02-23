package com.devdungeon.minecraft.discordnotifier;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutionException;

/**
 * @author NanoDano <nanodano@devdungeon.com>
 */
public class DiscordBot implements Runnable {

    private DiscordClient client;

    DiscordBot(JavaPlugin plugin, String botToken, String channelId) {

        client = new DiscordClientBuilder(botToken).build();

        // on Ready
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(ready -> {
                    System.out.println("[*] Logged in to Discord as " + ready.getSelf().getUsername());
                });

        // on Message
        client.getEventDispatcher().on(MessageCreateEvent.class).subscribe(event -> {
            Message message = event.getMessage();

            if (message.getAuthor().isPresent()) {
                User author = message.getAuthor().get();

                client.getChannelById(message.getChannelId()).subscribe(
                        value -> {
                            String mention = value.getMention();
                            System.out.println(mention);
                            if (mention.equals("<#" + channelId + ">")) {
                                System.out.println("Matches");
                                // send message
                                if (!author.isBot()) {
                                    String command = "say [Discord] " + author.getUsername() + ": " + message.getContent().get();
                                    try {
                                        boolean success = Bukkit.getScheduler().callSyncMethod(
                                                plugin,
                                                () -> Bukkit.dispatchCommand(
                                                        plugin.getServer().getConsoleSender(),
                                                        command)).get();
                                        if (success) {
                                            System.out.println("[+] Successfully ran the say command.");
                                        }
                                    } catch (InterruptedException | ExecutionException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                );
            }
        });
    }

    @Override
    public void run() {
        System.out.println("[*] Running Discord bot thread.");
        client.login().block();
    }

}
