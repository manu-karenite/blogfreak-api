package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Constant.CATEGORIES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Category.findByName", query = "from Category c where c.name=:categoryName")
public class Category {
    @Id
    @Column(name = Constant.ID)
    private String id;

    @Column(name = Constant.NAME)
    private String name;

    @Column(name = Constant.VERSION)
    private Integer version;

    @Column(name = Constant.CREATED_AT)
    private Date createdAt;

    @Column(name = Constant.UPDATED_AT)
    private Date updatedAt;

    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = Constant.BLOGS_MAP_CATEGORIES,
            joinColumns = @JoinColumn(name = Constant.CATEGORY_ID),
            inverseJoinColumns = @JoinColumn(name = Constant.BLOG_ID))
    private List<Blog> listOfBlogs;
}
