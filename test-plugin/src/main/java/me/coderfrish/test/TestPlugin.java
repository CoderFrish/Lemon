package me.coderfrish.test;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TestPlugin extends JavaPlugin implements Listener, CommandExecutor {
    private final Random random = new Random();
    private volatile ScheduledTask task;

    @EventHandler
    public void listenBlock(PlayerInteractEvent e) {
//        Material[] materials = new Material[]{Material.DIAMOND_BLOCK, Material.GOLD_BLOCK, Material.IRON_BLOCK, Material.COMMAND_BLOCK};
//        Block clickedBlock = e.getClickedBlock();
//        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && clickedBlock.getType() == Material.STONE) {
//            this.task = Bukkit.getRegionScheduler().runAtFixedRate(this, clickedBlock.getLocation(), (t) -> {
//                clickedBlock.setType(materials[random.nextInt(materials.length)]);
//            }, 1L, 20L);
//        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        task.cancel();
        Location test = new Location(Bukkit.getWorld("test"), 0, 3, 0);
//        Block blockAt = Bukkit.getWorld("test").getBlockAt(test);
//        sender.sendMessage(blockAt.getType().toString());
//        if (sender instanceof Player player) {
//            player.getScheduler().run(this, (e) -> {
//                player.teleportAsync(test);
//            }, null);
//        }
        Bukkit.getScheduler().runTask(this, () -> {
            sender.sendMessage(Component.text("Hello World!!"));
        });
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("cancel").setExecutor(this);

//        WorldCreator creator = new WorldCreator("test");
//        creator.environment(World.Environment.NORMAL);
//        creator.generator(new ChunkGenerator() {
//            @Override
//            public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
//                ChunkData chunkData = createChunkData(world);
//                chunkData.setRegion(0, 0, 0, 16, 2, 16, Material.BEDROCK);
//                chunkData.setRegion(0, 2, 0, 16, 3, 16, Material.GRASS_BLOCK);
//                for (int i = 0; i < 16; i++) {
//                    for (int j = 0; j < 16; j++) {
//                        biome.setBiome(i, j, Biome.PLAINS);
//                    }
//                }
//                return chunkData;
//            }
//        });
//        creator.createWorld();
    }
}
