package com.blogfreak.blog_freak_api.dto;

import lombok.*;

@Data
@Builder
public class ReturnHealthCheck {
    private String serviceVersion;
    private String dbVersion;
}
