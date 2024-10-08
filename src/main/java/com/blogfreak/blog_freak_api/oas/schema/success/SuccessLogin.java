package com.blogfreak.blog_freak_api.oas.schema.success;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class SuccessLogin extends BaseSuccess {
    private String data;
}
