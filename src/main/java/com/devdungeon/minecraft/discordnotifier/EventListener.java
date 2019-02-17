package com.devdungeon.minecraft.discordnotifier;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class EventListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Discord.discordPost(event.getPlayer().getDisplayName() + ": " + event.getMessage());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Discord.discordPost(event.getJoinMessage());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockMaterial = event.getBlock().getType();
        if (blockMaterial == Material.DIAMOND_BLOCK) {
            Discord.discordPost(event.getPlayer().getDisplayName() + " has struck diamonds!");
        }
        if (blockMaterial == Material.GOLD_BLOCK) {
            Discord.discordPost(event.getPlayer().getDisplayName() + " has struck gold!");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String message = event.getEntity().getPlayer().getDisplayName() + " died!";
        Discord.discordPost(message);
    }
}
