package mate.academy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String CRYPTO_ALGORITHM = "SHA-512";
    private static final int SALT_SIZE = 16;

    private HashUtil() {

    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CRYPTO_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                stringBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Couldn't create hash using "
                    + CRYPTO_ALGORITHM + "algorithm", e);
        }
        return stringBuilder.toString();
    }
}