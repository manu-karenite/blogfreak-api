package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.HealthCheckDAOImpl;
import com.blogfreak.blog_freak_api.dto.ReturnHealthCheck;
import com.blogfreak.blog_freak_api.exception.HealthCheckFail;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {
    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private HealthCheckDAOImpl healthCheckDAO;

    public HealthCheckServiceImpl(HealthCheckDAOImpl healthCheckDAO) {
        this.healthCheckDAO = healthCheckDAO;
    }

    @Override
    public ReturnHealthCheck getServiceVersion() {
        if (StringUtils.isNullOrEmpty(appVersion)) throw new HealthCheckFail("Health check failed. Service is down");
        ReturnHealthCheck returnHealthCheck = ReturnHealthCheck.builder()
                .serviceVersion(appVersion)
                .dbVersion(healthCheckDAO.getDatabaseVersion())
                .build();
        return returnHealthCheck;
    }
}
