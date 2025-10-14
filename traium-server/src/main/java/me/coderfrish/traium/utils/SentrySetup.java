package me.coderfrish.traium.utils;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.coderfrish.traium.config.GlobalConfig;
import org.apache.logging.log4j.Level;

import static me.coderfrish.traium.config.GlobalConfig.*;

public class SentrySetup {
    public static void setup(GlobalConfig globalConfig) {
        CommentedFileConfig config = globalConfig.getCurrentConfig();
        String sentryEnvironment = System.getenv("SENTRY_DSN");

        sentryDsn = sentryEnvironment != null && !sentryEnvironment.isBlank()
                ? sentryEnvironment
                : config.getOrElse("pufferfish_sentry.sentry_dsn", sentryDsn);

        logLevel = config.getOrElse("pufferfish_sentry.log_level", logLevel);
        onlyLogThrown = config.getOrElse("pufferfish_sentry.only_log_thrown", onlyLogThrown);

        if (sentryDsn != null && !sentryDsn.isBlank()) {
            gg.pufferfish.pufferfish.sentry.SentryManager.init(Level.getLevel(logLevel));
        }
    }
}
