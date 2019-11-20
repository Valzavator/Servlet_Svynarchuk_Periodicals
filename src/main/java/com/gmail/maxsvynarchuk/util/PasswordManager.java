package com.gmail.maxsvynarchuk.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordManager {
    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static boolean checkSecurePassword(String passwordToCheck,
                                              String passwordHash) {
        String checkPassHash = hashPassword(passwordToCheck);
        return passwordHash.equals(checkPassHash);
    }
}
