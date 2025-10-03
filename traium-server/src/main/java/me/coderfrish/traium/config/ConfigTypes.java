package me.coderfrish.traium.config;

public enum ConfigTypes {
    globals("globals"),
    misc("misc"),
    fixed("fixed");

    private final String keyword;

    ConfigTypes(String keyword) {
        this.keyword = keyword;
    }

    public final String keyword() {
        return this.keyword;
    }
}
