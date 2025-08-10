package me.coderfrish.plugin.api;

import me.coderfrish.plugin.exception.InvalidScriptException;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;

public class ScriptPermission {
    public void register(String name, String defaultValue, Value meta) {
        PluginManager pluginManager = Bukkit.getPluginManager();

        if (!permissions.containsKey(defaultValue)) {
            throw new InvalidScriptException("Permission value '" + defaultValue + "' is not registered.");
        }

        PermissionDefault permission = permissions.get(defaultValue);

        Value description = meta.getMember("description");
        String jDescription = "";
        if (description != null) {
            jDescription = description.asString();
        }

        Value children = meta.getMember("children");
        Map<String, Boolean> jChildren = new HashMap<>();
        if (children != null) {
            for (String key : children.getMemberKeys()) {
                jChildren.put(key, children.getMember(key).asBoolean());
            }
        }

        pluginManager.addPermission(new Permission(name, jDescription, permission, jChildren));
    }

    private static final Map<Object, PermissionDefault> permissions = new HashMap<>() {
        {
            put("true", PermissionDefault.TRUE);
            put("false", PermissionDefault.FALSE);
            put("op", PermissionDefault.OP);
            put("isop", PermissionDefault.OP);
            put("is_op", PermissionDefault.OP);
            put("operator", PermissionDefault.OP);
            put("isoperator", PermissionDefault.OP);
            put("is_operator", PermissionDefault.OP);
            put("admin", PermissionDefault.OP);
            put("isadmin", PermissionDefault.OP);
            put("is_admin", PermissionDefault.OP);
            put("!op", PermissionDefault.NOT_OP);
            put("notop", PermissionDefault.NOT_OP);
            put("not_op", PermissionDefault.NOT_OP);
            put("!operator", PermissionDefault.NOT_OP);
            put("notoperator", PermissionDefault.NOT_OP);
            put("not_operator", PermissionDefault.NOT_OP);
            put("!admin", PermissionDefault.NOT_OP);
            put("notadmin", PermissionDefault.NOT_OP);
            put("not_admin", PermissionDefault.NOT_OP);
        }
    };
}
