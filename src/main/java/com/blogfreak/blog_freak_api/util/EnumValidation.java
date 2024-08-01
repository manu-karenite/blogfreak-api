package com.blogfreak.blog_freak_api.util;

import com.blogfreak.blog_freak_api.exception.InvalidGender;
import org.apache.commons.lang3.StringUtils;

public class EnumValidation {
    public enum ALLOWED_GENDERS {
        MALE,
        FEMALE,
        OTHER
    }

    public static void validateGender(String inputGender) {
        for (ALLOWED_GENDERS it : ALLOWED_GENDERS.values()) {
            if (StringUtils.equalsIgnoreCase(it.toString(), inputGender.toString())) {
                return;
            }
        }
        throw new InvalidGender("Gender Value should be one of [male/female/other]");
    }
}
