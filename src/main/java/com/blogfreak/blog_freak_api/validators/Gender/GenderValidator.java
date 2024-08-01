package com.blogfreak.blog_freak_api.validators.Gender;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {
    @Override
    public void initialize(Gender constraintAnnotation) {}

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        if (gender == null || gender.isEmpty()) {
            return false;
        }
        // Gender must be Male/Female/Other
        return gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other");
    }
}
