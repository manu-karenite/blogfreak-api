package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogsDAOImpl implements BlogsDAO {

    @Autowired
    private EntityManager entityManager;

    public BlogsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Blog> getAllBlogs() {
        TypedQuery<Blog> query = entityManager.createQuery("from Blog", Blog.class);
        return query.getResultList();
    }
}
