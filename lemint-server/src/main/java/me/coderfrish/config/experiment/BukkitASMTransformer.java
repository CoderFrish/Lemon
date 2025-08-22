package me.coderfrish.config.experiment;

import dev.bacteriawa.mint.config.ConfigCategory;
import dev.bacteriawa.mint.config.Configuration;

@Configuration(name = "bukkit_asm_transformer", type = ConfigCategory.experiment, comment = {
        "This is order to compatible legacy bukkit plugin to write transformer."
})
public class BukkitASMTransformer {
    public static boolean enable = true;
}
