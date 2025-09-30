package me.coderfrish.traium.configuration;

public enum ConfigTypes {
    globals("globals");

    private final String keyword;

    ConfigTypes(String keyword) {
        this.keyword = keyword;
    }

    public final String keyword() {
        return this.keyword;
    }
}
