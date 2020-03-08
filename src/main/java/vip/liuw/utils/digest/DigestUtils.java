package vip.liuw.utils.digest;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    private static char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] md5(String s) {
        return digest(MessageDigestAlgorithms.MD5, s);
    }

    public static String md5Hex(String s) {
        return encodeHexString(md5(s));
    }

    public static byte[] sha1(String s) {
        return digest(MessageDigestAlgorithms.SHA_1, s);
    }

    public static String sha1Hex(String s) {
        return encodeHexString(sha1(s));
    }

    public static byte[] sha256(String s) {
        return digest(MessageDigestAlgorithms.SHA_256, s);
    }

    public static String sha256Hex(String s) {
        return encodeHexString(sha256(s));
    }

    public static byte[] digest(String algorithms, String str) {
        try {
            return MessageDigest.getInstance(algorithms).digest(str.getBytes(Charset.forName("utf-8")));
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String encodeHexString(byte[] bs) {
        char[] res = new char[bs.length << 1];
        for (int i = 0, j = 0; i < bs.length; i++) {
            res[j++] = DIGITS[(bs[i] & 0xf0) >>> 4];
            res[j++] = DIGITS[bs[i] & 0x0f];
        }
        return new String(res);
    }
}
