package me.coderfrish.region;

import io.papermc.paper.threadedregions.RegionizedWorldData;
import it.unimi.dsi.fastutil.longs.Long2ReferenceOpenHashMap;
import net.minecraft.server.MinecraftServer;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.CraftServer;

/**
 * @author bacteriawa
 *
 * License: https://github.com/LuminolMC/LightingLuminol/blob/master/LICENSE.md
 * Sources: https://github.com/LuminolMC/LightingLuminol/blob/ver/1.21.8/lightingluminol-server/src/main/java/dev/bacteriawa/RegonizedWorldDataSwitcher.java
 */
public class RegonizedWorldDataSwitcher {
    private volatile RegionizedWorldData lastMatch = null;

    public RegionizedWorldData getLastMatch(){
        return this.lastMatch;
    }

    public void updateCurrent(RegionizedWorldData data){
        this.lastMatch = data;
    }

    private final ThreadLocal<Integer> randomIndex = ThreadLocal.withInitial(() -> 0);
    private final ThreadLocal<Integer> calledTimes = ThreadLocal.withInitial(() -> 0);

    public void onTickFinished(){
        this.randomIndex.remove();
        this.calledTimes.remove();
    }

    public void resetRandomIdx(){
        this.randomIndex.set(0);
        this.calledTimes.set(0);
    }

    public RegionizedWorldData randomIfNull(Long2ReferenceOpenHashMap<RegionizedWorldData> regionToData, RegionizedWorldData data){
        if (data != null){
            return data;
        }

        int idx = this.randomIndex.get();

        if (this.calledTimes.get() / regionToData.size() >= 400){ //TODO Is that a truly exact value?
            this.calledTimes.set(0);
            this.randomIndex.set(idx+1);
        }

        if (idx > regionToData.size()){
            this.randomIndex.set(0);
            idx = 0;
        }

        int curr = 0;
        RegionizedWorldData result = this.getLastMatch();

        for (RegionizedWorldData target : regionToData.values()){
            if (curr == idx){
                result = target;
            }

            curr++;
        }

        return result;
    }
}