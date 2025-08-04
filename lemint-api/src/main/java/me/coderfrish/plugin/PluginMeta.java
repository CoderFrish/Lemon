package me.coderfrish.plugin;

public class PluginMeta {
    private final String name;
    private final String version;
    private final String description;
    private final String[] authors;
    private final String[] contributors;
    private final String website;
    private final String license;

    public PluginMeta(String name, String version, String description, String[] authors, String[] contributors, String website, String license) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.contributors = contributors;
        this.website = website;
        this.license = license;
    }

    public String getWebsite() {
        return website;
    }

    public String[] getContributors() {
        return contributors;
    }

    public String getLicense() {
        return license;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
