package me.coderfrish.traium.profiler.networkprofiler;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.NotNull;

public record NetworkData(Connection connection, Packet<?> packet, NetworkBound bound) {
    @Override
    public @NotNull String toString() {
        return "Player: " +  connection + " | Bound: " + bound.getKeyword() + " | Packet: " + packet.type().id();
    }
}
