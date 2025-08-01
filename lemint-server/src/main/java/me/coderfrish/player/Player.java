package me.coderfrish.player;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class Player {
    public final LuaValue name;
    public final LuaFunction sendMessage;

    public Player(org.bukkit.entity.Player player) {
        this.name = LuaValue.valueOf(player.getName());
        this.sendMessage = new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue arg) {
                player.sendMessage(arg.tojstring());
                return NIL;
            }
        };
    }
}
