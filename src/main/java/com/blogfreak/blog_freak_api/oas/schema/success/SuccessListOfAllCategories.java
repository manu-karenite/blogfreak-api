package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessListOfAllCategories extends BaseSuccess {
    private List<Category> listOfAllCategories;
}
