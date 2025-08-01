local plugin = function(plugin)
    plugin.enable = function()
        plugin.logger.info("Enabled Plugin")
    end

    plugin.disable = function()
        plugin.logger.warn("Disabled Plugin")
    end
end

pluginManager:register(plugin, {
    name = "TestPlugin",
    version = "1.0.0"
})
