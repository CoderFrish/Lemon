package me.coderfrish.traium.exception.region;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class ThreadException extends RuntimeException {
    private final ServerLevel world;
    private final BlockPos blockPos;

    public ThreadException(ServerLevel world, BlockPos blockPos, String message) {
        super(message);
        this.world = world;
        this.blockPos = blockPos;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public ServerLevel getWorld() {
        return world;
    }
}
