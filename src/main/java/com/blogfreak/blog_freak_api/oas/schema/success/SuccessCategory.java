package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-30T18:20:35.154+00:00\",\"statusCodeMessage\":\"Message\",\"statusCode\":0,\"data\":{\"id\":\"1722277668189\",\"name\":\"message\",\"createdAt\":\"2024-07-28T18:30:00.000+00:00\"}}")
public class SuccessCategory extends BaseSuccess {
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
