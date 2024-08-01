package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Blogger;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-31T15:48:52.946+00:00\",\"statusCodeMessage\":\"string\",\"statusCode\":0,\"data\":{\"id\":\"1722440932935\",\"firstName\":\"Manavesh\",\"lastName\":\"Narendra\",\"emailId\":\"manavesh.narendra@gmail.com\",\"gender\":\"male\",\"registeredAt\":\"2024-07-31T15:48:52.935+00:00\"}}")
public class SuccessBlogger extends BaseSuccess {
    private Blogger blogger;

    public Blogger blogger() {
        return blogger;
    }

    public void setCategory(Blogger blogger) {
        this.blogger = blogger;
    }
}
