package me.coderfrish.traium.configuration;

public enum ConfigTypes {
    globals("globals"),
    misc("misc");

    private final String keyword;

    ConfigTypes(String keyword) {
        this.keyword = keyword;
    }

    public final String keyword() {
        return this.keyword;
    }
}
