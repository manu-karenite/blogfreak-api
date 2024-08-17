package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.exception.BloggerNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BloggerDAOImpl implements BloggerDAO {
    @Autowired
    EntityManager entityManager;

    public BloggerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Blogger> getAllBloggers() {
        TypedQuery<Blogger> query = entityManager.createQuery("from Blogger", Blogger.class);
        List<Blogger> listOfAllBloggers = query.getResultList();
        return listOfAllBloggers;
    }

    @Override
    public Blogger getBloggerById(String bloggerId) {
        entityManager.clear();
        Blogger blogger = entityManager.find(Blogger.class, bloggerId);
        if (blogger == null)
            throw new BloggerNotFound(String.format("Blogger with id : [%s] does not exist", bloggerId));
        return blogger;
    }

    @Override
    public Blogger getBloggerByEmail(String bloggerEmail) {
        entityManager.clear();
        TypedQuery<Blogger> query =
                entityManager.createQuery("from Blogger where emailId=:bloggerEmail", Blogger.class);
        query.setParameter("bloggerEmail", bloggerEmail);
        List<Blogger> listOfBloggers = query.getResultList();
        if (listOfBloggers == null || listOfBloggers.size() == 0)
            throw new BloggerNotFound(String.format("Blogger with email : [%s] does not exist", bloggerEmail));
        return listOfBloggers.get(0);
    }

    @Override
    public Blogger createBlogger(Blogger blogger) {
        entityManager.persist(blogger);
        return blogger;
    }

    @Override
    public Blogger updateBlogger(Blogger blogger, String bloggerId) {
        entityManager.merge(blogger);
        return blogger;
    }

    @Override
    public Blogger updateBloggerPassword(Blogger blogger) {
        entityManager.merge(blogger);
        return blogger;
    }

    @Override
    public Blogger deleteBlogger(String bloggerId) {
        final Blogger blogger = getBloggerById(bloggerId);
        this.entityManager.remove(blogger);
        return blogger;
    }
}
