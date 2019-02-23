package com.devdungeon.minecraft.discordnotifier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;


public class EventListener implements Listener {


    private String formattedUsername(Player player) {
        return "**" + player.getName() + "**";
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Discord.discordPost(formattedUsername(event.getPlayer()) + ": " + event.getMessage());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        //event.getJoinMessage();
        event.getPlayer().getDisplayName();
        System.out.println(event.getPlayer().getDisplayName());
        Discord.discordPost(formattedUsername(event.getPlayer()) + " has joined the party!");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockMaterial = event.getBlock().getType();
        if (blockMaterial == Material.DIAMOND) {
            Discord.discordPost(formattedUsername(event.getPlayer()) + " has struck diamonds!");
        }
        if (blockMaterial == Material.GOLD_BLOCK) {
            Discord.discordPost(formattedUsername(event.getPlayer()) + " has struck gold!");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String message = formattedUsername(event.getEntity().getPlayer()) + " died! Someone help them!";
        Discord.discordPost(message);
    }
}
