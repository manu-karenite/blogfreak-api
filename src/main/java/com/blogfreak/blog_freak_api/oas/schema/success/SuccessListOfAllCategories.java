package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-30T18:20:35.154+00:00\",\"statusCodeMessage\":\"OK\",\"statusCode\":200,\"data\":[{\"id\":\"1722277668189\",\"name\":\"message\",\"createdAt\":\"2024-07-28T18:30:00.000+00:00\"}]}")
public class SuccessListOfAllCategories extends BaseSuccess {
    private List<Category> listOfAllCategories;

    public List<Category> getListOfAllCategories() {
        return listOfAllCategories;
    }

    public void setListOfAllCategories(List<Category> listOfAllCategories) {
        this.listOfAllCategories = listOfAllCategories;
    }
}
