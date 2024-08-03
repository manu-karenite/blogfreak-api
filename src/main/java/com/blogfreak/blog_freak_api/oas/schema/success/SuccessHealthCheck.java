package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.dto.ReturnHealthCheck;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        example = "{\n" + "  \"timestamp\": \"2024-08-03T08:15:26.219+00:00\",\n"
                + "  \"statusCodeMessage\": \"OK\",\n"
                + "  \"statusCode\": 200,\n"
                + "  \"data\": {\n"
                + "    \"serviceVersion\": \"0.0.1\",\n"
                + "    \"dbVersion\": \"8.0.35\"\n"
                + "  }\n"
                + "}")
public class SuccessHealthCheck extends BaseSuccess {
    private ReturnHealthCheck returnHealthCheck;
}
