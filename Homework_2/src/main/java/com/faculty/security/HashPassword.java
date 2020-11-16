package com.faculty.security;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aleksandr on 24.09.2020.
 */
public class HashPassword {

    private static Logger logger = LoggerFactory.getLogger(HashPassword.class);
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
            logger.error("Exception in HashPassword method checkPassword() with parameters: {}, {}", password, hash);
            return false;
        }
    }
}
