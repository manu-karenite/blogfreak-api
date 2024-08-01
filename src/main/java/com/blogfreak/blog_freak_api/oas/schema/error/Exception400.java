package com.blogfreak.blog_freak_api.oas.schema.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-30T17:12:29.435+00:00\",\"statusCodeMessage\":\"BAD_REQUEST\",\"statusCode\":400,\"message\":\"Message\"}")
public class Exception400 extends BaseException {}
