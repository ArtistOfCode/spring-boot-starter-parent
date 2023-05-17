package com.codeartist.component.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author 艾江南
 * @date 2022/7/25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MD5Util {

    private static final MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(byte[] data) {
        byte[] digest = md5.digest(data);
        return Hex.toHexString(digest);
    }

    public static String md5(String data) {
        return md5(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String md5(String data, String salt) {
        return md5(data + salt);
    }
}
