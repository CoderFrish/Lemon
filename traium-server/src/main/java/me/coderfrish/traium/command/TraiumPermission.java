package me.coderfrish.traium.command;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.HashMap;
import java.util.Map;

public enum TraiumPermission {
    TRAIUM_ADMIN_PERMISSION("traium.command.admin", PermissionDefault.OP),
    TRAIUM_USER_PERMISSION("traium.command.user", PermissionDefault.TRUE);

    private final String keyword;
    private final Permission permission;

    TraiumPermission(String keyword, PermissionDefault value, String... child) {
        Map<String, Boolean> children = new HashMap<>();
        for (String s : child) {
            children.put(s, true);
        }

        this.keyword = keyword;
        this.permission = new Permission(keyword, value, children);
    }

    public final Permission permission() {
        return this.permission;
    }

    public final String keyword() {
        return this.keyword;
    }
}
