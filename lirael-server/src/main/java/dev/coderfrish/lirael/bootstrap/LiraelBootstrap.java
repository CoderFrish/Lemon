package dev.coderfrish.lirael.bootstrap;

import dev.coderfrish.lirael.config.LiraelConfig;
import dev.coderfrish.lirael.listener.RecipeSyncListener;
import dev.coderfrish.lirael.profiles.OnlinePlayerProfile;
import dev.coderfrish.lirael.utility.FakePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.messaging.Messenger;

public class LiraelBootstrap {
    private static final FakePlugin fakePlugin = new FakePlugin();

    public static void bootstrap() throws Exception {
        OnlinePlayerProfile.loadProfile();
        LiraelConfig.load(); /* lirael config */
    }
}
