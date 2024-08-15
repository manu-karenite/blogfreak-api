package com.blogfreak.blog_freak_api.util;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

public class JWTUtils {
    public static SecretKey getSecretKey() {
        String jwtSecretSalt = System.getenv(Constant.JWT_SECRET_SALT);
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretSalt.getBytes(StandardCharsets.UTF_8));
        return secretKey;
    }

    public static String getJwtIssuer() {
        return Constant.APPLICATION_NAME;
    }

    public static String getJwtSubject() {
        return String.format("JsonWebToken Issued by %s for Authentication", getJwtIssuer());
    }

    public static Date getJwtIssueDate() {
        return new Date();
    }

    public static Date getJwtExpiryDate() {
        return new Date(new Date().getTime() + 24 * 60 * 60 * 1000L);
    }
}
