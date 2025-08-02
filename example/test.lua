local plugin = function(plugin)
    local join = function(event)
        event:setJoinMessage("Welcome " .. event:getPlayer():getName() .. " join the server.")
    end

    local quit = function(event)
        event:setQuitMessage("Welcome " .. event:getPlayer():getName() .. " left the Server.")
    end

    local command = function(sender, name, args)
        sender:sendMessage("Hello World!!")
    end

    plugin.enable = function()
        plugin.event.listen("PlayerJoinEvent", join)
        plugin.event.listen("PlayerQuitEvent", quit)
        plugin.command.register("test", command, {
            description = "This is a test command",
            usage = "/<commands>"
        })
    end
end

local meta = {
    name = "Test",
    version = "1.0.0"
}

pluginManager:register(plugin, meta)
