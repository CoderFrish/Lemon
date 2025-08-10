package me.coderfrish.plugin.pack;

public class PackEntry {
    private final String type; // source 、 resource
    private final String name;
    private final byte[] body;

    public PackEntry(String type, String name, byte[] body) {
        this.type = type;
        this.name = name;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public byte[] getBody() {
        return body;
    }
}
