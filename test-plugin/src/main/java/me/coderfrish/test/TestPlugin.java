package me.coderfrish.test;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener, CommandExecutor {
    @EventHandler
    public void listenEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player && e.getEntity() instanceof Creeper creeper) {
            // 点 1
            Bukkit.getGlobalRegionScheduler().runAtFixedRate(this, (t) -> {
                // 点2
                player.teleportAsync(creeper.getLocation());
            }, 5 * 20L, 30 * 20L);
        }
    }

    @EventHandler
    public void listenBlock(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.STONE) {
            Bukkit.getGlobalRegionScheduler().run(this, (t) -> {
                e.getClickedBlock().setType(Material.GOLD_BLOCK);
            });
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getGlobalRegionScheduler().cancelTasks(this);
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("cancel").setExecutor(this);
    }
}
