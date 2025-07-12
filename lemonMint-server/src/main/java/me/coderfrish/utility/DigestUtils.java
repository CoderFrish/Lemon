package me.coderfrish.utility;

import net.minecraft.world.level.levelgen.WorldOptions;

import java.security.MessageDigest;

public class DigestUtils {
    public static long randomSeed() {
        long seed = WorldOptions.randomSeed();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(new byte[] {
                    (byte) (seed >> 56),
                    (byte) (seed >> 48),
                    (byte) (seed >> 40),
                    (byte) (seed >> 32),
                    (byte) (seed >> 24),
                    (byte) (seed >> 16),
                    (byte) (seed >> 8),
                    (byte) seed
            });
            long result = 0;
            for (int i = 0; i < 8; i++) {
                result |= (long) (hashBytes[i] & 0xff) << (56 - 8 * i);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
