package me.coderfrish.test;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TestPlugin extends JavaPlugin implements Listener, CommandExecutor {
    private final Random random = new Random();
    private volatile ScheduledTask task;

    @EventHandler
    public void listenBlock(PlayerInteractEvent e) {
        Material[] materials = new Material[]{Material.DIAMOND_BLOCK, Material.GOLD_BLOCK, Material.IRON_BLOCK, Material.COMMAND_BLOCK};
        Block clickedBlock = e.getClickedBlock();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && clickedBlock.getType() == Material.STONE) {
            this.task = Bukkit.getRegionScheduler().runAtFixedRate(this, clickedBlock.getLocation(), (t) -> {
                clickedBlock.setType(materials[random.nextInt(materials.length)]);
            }, 1L, 20L);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        task.cancel();
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("cancel").setExecutor(this);
    }
}
