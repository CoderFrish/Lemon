local plugin = function(plugin)
    local join = function(event)
        event.setJoinMessage("Welcome " .. event.player.name .. "!!")
    end

    local test = function(sender, name, args)
    end

    local events = function()
        plugin.event.listen("player_join", join)
    end

    local commands = function()
        local test_meta = {
            usage = "/<command>",
            description = "This is a test command."
        }

        plugin.command.register("test", test, test_meta)
    end

    plugin.enable = function()
        plugin.logger.info("Enabled Plugin")
        events()
        commands()
    end

    plugin.disable = function()
        plugin.logger.warn("Disabled Plugin")
    end
end

local meta = {
    name = "TestPlugin",
    version = "1.0.0",
    description = "This is a test plugin."
}

pluginManager:register(plugin, meta)
