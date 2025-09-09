package com.jobportal.cli.util;

import java.security.MessageDigest;
import java.util.HexFormat;

public class PasswordUtil {
    public static String hash(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(raw.getBytes("UTF-8"));
            return HexFormat.of().formatHex(dig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
