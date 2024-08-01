package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Blogger;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(
        example =
                "{\"timestamp\":\"2024-07-31T15:58:00.523+00:00\",\"statusCodeMessage\":\"OK\",\"statusCode\":200,\"data\":[{\"id\":\"1722440932935\",\"firstName\":\"Manavesh\",\"lastName\":\"Narendra\",\"emailId\":\"manavesh.narendra@gmail.com\",\"gender\":\"male\",\"registeredAt\":\"2024-07-30T18:30:00.000+00:00\"}]}")
public class SuccessListOfAllBloggers extends BaseSuccess {
    private List<Blogger> listOfAllBloggers;

    public List<Blogger> getListOfAllBloggers() {
        return listOfAllBloggers;
    }

    public void setListOfAllBloggers(List<Blogger> listOfAllBloggers) {
        this.listOfAllBloggers = listOfAllBloggers;
    }
}
