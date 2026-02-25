package dev.coderfrish.lirael.profiles;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class OnlinePlayerProfile {
    private static final Path PROFILE_FILE = Path.of("online-profiles.json");
    private static JsonObject players = new JsonObject();
    private static final Gson gson = new Gson();

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public static void addPlayer(String name, UUID uuid) {
        if (players.has(name)) return;
        players.addProperty(name, uuid.toString());
    }

    public static UUID getUUID(String name) {
        return UUID.fromString(players.get(name).getAsString());
    }

    public static boolean has(String name) {
        return players.has(name);
    }

    public static void loadProfile() throws IOException {
        if (!Files.exists(PROFILE_FILE)) return;

        String p = Files.readString(PROFILE_FILE, UTF8);
        players = gson.fromJson(p, JsonObject.class);
    }

    public static void saveProfile() throws IOException {
        saveProfile(gson.toJson(players));
    }

    private static void saveProfile(String json) throws IOException {
        if (!Files.exists(PROFILE_FILE)) {
            Files.createFile(PROFILE_FILE);
            Files.writeString(PROFILE_FILE, json, UTF8);
            return;
        }

        Files.deleteIfExists(PROFILE_FILE);
        saveProfile(json);
    }
}
