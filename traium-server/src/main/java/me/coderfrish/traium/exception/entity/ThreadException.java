package me.coderfrish.traium.exception.entity;

import net.minecraft.world.entity.Entity;

public class ThreadException extends RuntimeException {
    private final Entity entity;

    public ThreadException(Entity entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
