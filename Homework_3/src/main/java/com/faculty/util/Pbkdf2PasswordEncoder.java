package com.faculty.util;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.GeneralSecurityException;

import static org.springframework.security.crypto.util.EncodingUtils.concatenate;
import static org.springframework.security.crypto.util.EncodingUtils.subArray;

/**
 * Created by Aleksandr on 16.11.2020.
 */
public class Pbkdf2PasswordEncoder implements PasswordEncoder {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int DEFAULT_HASH_WIDTH = 256;
    private static final int DEFAULT_ITERATIONS = 185000;

    private final byte[] secret;
    private final int hashWidth;
    private final int iterations;

    public Pbkdf2PasswordEncoder() {
        this("");
    }
    public Pbkdf2PasswordEncoder(CharSequence secret) {
        this(secret, DEFAULT_ITERATIONS, DEFAULT_HASH_WIDTH);
    }
    public Pbkdf2PasswordEncoder(CharSequence secret, int iterations, int hashWidth) {
        this.secret = Utf8.encode(secret);
        this.iterations = iterations;
        this.hashWidth = hashWidth;
    }
    private final BytesKeyGenerator saltGenerator = KeyGenerators.secureRandom();


    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = this.saltGenerator.generateKey(); // salt
        byte[] encoded = encode(rawPassword, salt); // vastly salt
        return String.valueOf(Hex.encode(encoded));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digested = Hex.decode(encodedPassword);
        byte[] salt = subArray(digested, 0, this.saltGenerator.getKeyLength());
        return matches(digested, encode(rawPassword, salt));
    }

    private static boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    private byte[] encode(CharSequence rawPassword, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(rawPassword.toString().toCharArray(),
                    concatenate(salt, this.secret), this.iterations, this.hashWidth);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return concatenate(salt, skf.generateSecret(spec).getEncoded());
        }
        catch (GeneralSecurityException e) {
            throw new IllegalStateException("Could not create hash", e);
        }
    }
}
