package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dto.ReturnHealthCheck;

public interface HealthCheckService {

    public ReturnHealthCheck getServiceVersion();
}
