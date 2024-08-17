package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.exception.BlogNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Override
    public Blog getBlogById(String blogId) {
        entityManager.clear();
        Blog blog = entityManager.find(Blog.class, blogId);
        if (blog == null) throw new BlogNotFound(String.format("Blog with id %s not found", blogId));
        return blog;
    }

    @Override
    public Blog createBlog(Blog blog) {
        this.entityManager.persist(blog);
        return blog;
    }

    public List<Blog> getListOfAllBlogsInListOfCategories(final Set<String> categoryIdsSet) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT DISTINCT bmc.blogId FROM BlogMapCategory bmc WHERE bmc.categoryId IN(:categoryIdsSet)",
                String.class);
        query.setParameter("categoryIdsSet", categoryIdsSet);
        List<String> returnedBlogList = query.getResultList();
        final List<Blog> blogList = new ArrayList<>();
        for (String blogId : returnedBlogList) blogList.add(getBlogById(blogId));
        return blogList;
    }
}
