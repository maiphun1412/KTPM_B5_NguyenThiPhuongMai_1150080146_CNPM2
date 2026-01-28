package B5;

import java.security.MessageDigest;

public class PasswordUtil {
    public static String sha256Hex(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString(); // 64 ký tự
        } catch (Exception e) {
            throw new RuntimeException("Không thể hash mật khẩu", e);
        }
    }
}
