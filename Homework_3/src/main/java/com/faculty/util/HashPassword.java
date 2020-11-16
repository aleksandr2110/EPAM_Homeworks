package com.faculty.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by Aleksandr on 16.11.2020.
 */
public class HashPassword {
    private static int rounds = 8;

    public static String hashPassword(String plaintext) {
        String salt = BCrypt.gensalt(rounds);
        return (BCrypt.hashpw(plaintext, salt));
    }

    public static boolean checkPassword(String password, String hash) {
        if (hash == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(password, hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
