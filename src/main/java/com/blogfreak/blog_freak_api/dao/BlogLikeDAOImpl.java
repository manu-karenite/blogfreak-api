package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.BlogLike;
import com.blogfreak.blog_freak_api.entity.Blogger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogLikeDAOImpl implements BlogLikeDAO {

    @Autowired
    private EntityManager entityManager;

    public BlogLikeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean isLikeAlreadyExistingForABloggerOnABlog(final Blogger blogger, final String blogId) {
        TypedQuery<BlogLike> query =
                entityManager.createQuery("from BlogLike WHERE blogId=:blogId AND blogger=:blogger", BlogLike.class);
        query.setParameter("blogId", blogId);
        query.setParameter("blogger", blogger);
        List<BlogLike> resultList = query.getResultList();
        if (resultList == null || resultList.size() == 0) return false;
        return true;
    }

    public void createALike(BlogLike blogLike) {
        this.entityManager.persist(blogLike);
    }
}
