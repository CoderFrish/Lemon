package me.coderfrish.traium.unsafe;

import io.papermc.paper.threadedregions.RegionizedWorldData;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeWorldData {
    private static final Map<Level, RegionizedWorldData> unsafeWorldData = new ConcurrentHashMap<>();

    public static RegionizedWorldData getUnsafeWorldData(Level world) {
        return unsafeWorldData.get(world);
    }

    public static void addUnsafeWorldData(RegionizedWorldData world) {
        unsafeWorldData.put(world.world, world);
    }

    public static RegionizedWorldData updateUnsafeWorldData(RegionizedWorldData world) {
        return unsafeWorldData.replace(world.world, world);
    }
}
