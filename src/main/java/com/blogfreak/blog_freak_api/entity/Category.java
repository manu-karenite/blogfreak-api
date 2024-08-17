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
