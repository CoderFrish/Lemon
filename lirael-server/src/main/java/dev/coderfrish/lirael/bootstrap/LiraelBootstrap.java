package dev.coderfrish.lirael.bootstrap;

import dev.coderfrish.lirael.config.LiraelConfig;

public class LiraelBootstrap {
    public static void bootstrap() throws Exception {
        LiraelConfig.load(); /* lirael config */
    }
}
