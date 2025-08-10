package me.coderfrish.scheduler;

import org.bukkit.plugin.Plugin;

public interface LemonMintScheduledTask {
    void cancel();

    boolean isCancelled();

    Plugin getOwningPlugin();
}
