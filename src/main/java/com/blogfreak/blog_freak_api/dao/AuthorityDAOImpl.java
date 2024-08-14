package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

    @Autowired
    private EntityManager entityManager;

    public AuthorityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Authority> getListOfAuthorityForBlogger(String bloggerId) {
        TypedQuery<Authority> query =
                entityManager.createQuery("from Authority where userId=:bloggerId", Authority.class);
        return query.getResultList();
    }

    @Override
    public Authority createAuthority(Authority authority) {
        entityManager.persist(authority);
        return authority;
    }
}
