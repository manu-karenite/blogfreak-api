package com.blogfreak.blog_freak_api.oas.schema.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-30T17:12:29.435+00:00\",\"statusCodeMessage\":\"UNAUTHORIZED\",\"statusCode\":401,\"message\":\"Message\"}")
public class Exception401 extends BaseException {}
