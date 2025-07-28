package me.coderfrish.folia;

import io.papermc.paper.plugin.configuration.PluginMeta;

public class FoliaSupported {
    public static boolean isFoliaSupported(PluginMeta meta) {
        if (!me.coderfrish.config.experiment.FoliaSupportedConfig.enabled)
            return true;

        return meta.isFoliaSupported();
    }
}
