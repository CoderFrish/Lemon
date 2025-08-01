package me.coderfrish.events.player;

import me.coderfrish.player.Player;
import me.coderfrish.server.event.Event;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

public class PlayerJoinEvent extends Event {
    public final LuaValue player;
    public String joinMessage;

    public PlayerJoinEvent(Player player) {
        this.player = CoerceJavaToLua.coerce(player);
    }

    public final LuaFunction setJoinMessage = new OneArgFunction() {
        @Override
        public LuaValue call(LuaValue arg) {
            joinMessage = arg.tojstring();
            return NIL;
        }
    };

    public final LuaFunction getJoinMessage = new ZeroArgFunction() {
        @Override
        public LuaValue call() {
            return LuaValue.valueOf(joinMessage);
        }
    };
}
