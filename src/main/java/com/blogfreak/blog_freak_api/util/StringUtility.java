package com.blogfreak.blog_freak_api.util;

import java.util.UUID;

public class StringUtility {
    public static String generateIdForEntity() {
        return UUID.randomUUID().toString().substring(0, 30);
    }
}
