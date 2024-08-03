package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.exception.HealthCheckFail;
import com.mysql.cj.util.StringUtils;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HealthCheckDAOImpl implements HealthCheckDAO {

    @Autowired
    private EntityManager entityManager;

    public HealthCheckDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String getDatabaseVersion() {
        try {
            String dbQuery = "SELECT VERSION()";
            String dbVersion =
                    ((String) entityManager.createNativeQuery(dbQuery).getSingleResult());
            if (StringUtils.isNullOrEmpty(dbVersion)) {
                throw new HealthCheckFail("Health check failed. Database is down");
            }
            return dbVersion;
        } catch (Exception e) {
            throw new HealthCheckFail(e.getMessage());
        }
    }
}
