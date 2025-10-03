package me.coderfrish.traium.exception;

import net.minecraft.world.entity.Entity;

public class EntityThreadException extends RuntimeException {
    private final Entity entity;

    public EntityThreadException(Entity entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
