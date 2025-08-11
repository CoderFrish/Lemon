package me.coderfrish.plugin.api.meta;

public class Author {
    private final String name;
    private final String email;
    private final String website;

    public Author(String name, String email, String website) {
        this.name = name;
        this.email = email;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }
}
