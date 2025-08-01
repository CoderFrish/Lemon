package me.coderfrish.server.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginLogger implements me.coderfrish.plugin.PluginLogger {
    private final Logger logger;

    public PluginLogger(String name) {
        this.logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void info(String value) {
        logger.info(value);
    }

    @Override
    public void warn(String value) {
        logger.warn(value);
    }

    @Override
    public void error(String value) {
        logger.error(value);
    }
}
