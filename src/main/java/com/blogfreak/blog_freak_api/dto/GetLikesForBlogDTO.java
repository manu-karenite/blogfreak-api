package com.blogfreak.blog_freak_api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetLikesForBlogDTO {
    String id;
    Integer likesCount;
    List<String> listOfLikesWithBloggerIds;
}
