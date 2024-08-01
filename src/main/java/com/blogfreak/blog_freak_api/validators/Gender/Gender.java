package com.blogfreak.blog_freak_api.validators.Gender;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
    String message() default "Gender Value should be one of [male/female/other]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
