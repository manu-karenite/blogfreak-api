package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Constant.CATEGORIES)
public class Category {
    @Id
    @Column(name = Constant.ID)
    private String id;

    @Column(name = Constant.NAME)
    private String name;

    @Column(name = Constant.CREATED_AT)
    private Date createdAt;

    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = Constant.BLOGS_MAP_CATEGORIES,
            joinColumns = @JoinColumn(name = Constant.CATEGORY_ID),
            inverseJoinColumns = @JoinColumn(name = Constant.BLOG_ID))
    private List<Blog> listOfBlogs;

    public List<Blog> getListOfBlogs() {
        return listOfBlogs;
    }

    public void setListOfBlogs(List<Blog> listOfBlogs) {
        this.listOfBlogs = listOfBlogs;
    }

    public Category() {}

    public Category(String name, String id) {
        this.id = id;
        this.name = name;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id)
                && Objects.equals(name, category.name)
                && Objects.equals(createdAt, category.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
