package com.blogfreak.blog_freak_api.entity;

import com.blogfreak.blog_freak_api.util.Constant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blogs_map_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogMapCategory {
    @Id
    @Column(name = Constant.ID)
    private Integer id;

    @Column(name = Constant.BLOG_ID)
    private String blogId;

    @Column(name = Constant.CATEGORY_ID)
    private String categoryId;
}
