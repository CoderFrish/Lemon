package dev.coderfrish.lirael.bootstrap;

import dev.coderfrish.lirael.config.LiraelConfig;
import dev.coderfrish.lirael.listener.RecipeSyncListener;
import dev.coderfrish.lirael.utility.FakePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.messaging.Messenger;

public class LiraelBootstrap {
    private static final FakePlugin fakePlugin = new FakePlugin();

    public static void bootstrap() throws Exception {
        LiraelConfig.load(); /* lirael config */
    }

    public static void startup() {
        final Server server = Bukkit.getServer();
        final Messenger messenger = server.getMessenger();

        server.getPluginManager().registerEvents(new RecipeSyncListener(), fakePlugin);

        messenger.registerOutgoingPluginChannel(fakePlugin, "neoforge:recipe_content");
        messenger.registerOutgoingPluginChannel(fakePlugin, "fabric:recipe_sync");
    }
}
