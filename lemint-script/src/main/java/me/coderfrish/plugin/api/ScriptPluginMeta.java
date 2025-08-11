package me.coderfrish.plugin.api;

import me.coderfrish.plugin.api.enabled.LoadOrder;
import me.coderfrish.plugin.api.meta.Author;
import org.graalvm.polyglot.Value;

import java.util.List;

public class ScriptPluginMeta {
    private final String name;
    private final String version;
    private final String description;
    private final LoadOrder loadOrder;
    private final List<Author> authors;
    private final String home;
    private final String issues;
    private final String source;
    private final String license;

    public ScriptPluginMeta(String name, String version, String description, Value installer, LoadOrder loadOrder, List<Author> authors, String home, String issues, String source, String license) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.loadOrder = loadOrder;
        this.authors = authors;
        this.home = home;
        this.issues = issues;
        this.source = source;
        this.license = license;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public LoadOrder getLoadOrder() {
        return loadOrder;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getIssues() {
        return issues;
    }

    public String getHome() {
        return home;
    }

    public String getSource() {
        return source;
    }

    public String getLicense() {
        return license;
    }
}
