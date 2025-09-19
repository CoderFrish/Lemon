package me.coderfrish.lemonmint.unsafe;

import io.papermc.paper.threadedregions.RegionizedWorldData;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeWorldDataMap {
    private static final Map<Level, RegionizedWorldData> dataMap = new ConcurrentHashMap<>();

    public static void register(RegionizedWorldData data) {
        dataMap.put(data.world, data);
    }

    public static RegionizedWorldData get(Level world) {
        if (!dataMap.containsKey(world))
            return null;

        return dataMap.get(world);
    }
}
