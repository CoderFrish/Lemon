package summer.foliaPhantom;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

public class FoliaPhantomExtra extends JavaPlugin {

    private static FoliaPhantomExtra instance;
    private PluginPatcher pluginPatcher;

    public static FoliaPhantomExtra getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.pluginPatcher = new PluginPatcher(getLogger());

        getLogger().info("FoliaPhantom-extra: Initialized. Checking for JARs to patch...");

        File inputDir = new File(getDataFolder(), "input");
        File outputDir = new File(getDataFolder(), "output");

        if (!inputDir.exists()) {
            inputDir.mkdirs();
        }
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] jarsToPatch = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

        if (jarsToPatch == null || jarsToPatch.length == 0) {
            getLogger().info("FoliaPhantom-extra: No JARs found in the input directory. Nothing to do.");
            return;
        }

        getLogger().info(String.format("FoliaPhantom-extra: Found %d JAR(s) to patch.", jarsToPatch.length));

        for (File originalJar : jarsToPatch) {
            String pluginName;
            try {
                pluginName = PluginPatcher.getPluginNameFromJar(originalJar);
                 if (pluginName == null) {
                    pluginName = originalJar.getName();
                }
            } catch (IOException e) {
                pluginName = originalJar.getName();
                getLogger().log(Level.WARNING, String.format("Could not read plugin.yml from %s, using filename.", originalJar.getName()), e);
            }

            File outputFile = new File(outputDir, originalJar.getName());

            try {
                getLogger().info(String.format("Patching %s...", pluginName));
                pluginPatcher.patchPlugin(originalJar, outputFile);
                getLogger().info(String.format("Successfully patched %s -> %s", originalJar.getName(), outputFile.getPath()));

                // Delete the original file from input dir
                originalJar.delete();

            } catch (Exception e) {
                getLogger().log(Level.SEVERE, String.format("Failed to patch %s. Please check the console for errors.", pluginName), e);
            }
        }

        getLogger().info("FoliaPhantom-extra: Patching process complete.");
    }

    @Override
    public void onDisable() {
        getLogger().info("FoliaPhantom-extra has been disabled.");
    }
}
