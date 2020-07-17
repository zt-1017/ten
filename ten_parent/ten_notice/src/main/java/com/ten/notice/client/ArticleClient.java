package com.ten.notice.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ten-article")
public interface ArticleClient {
    //GET/article/{articleId}根据ID查询文章
    @RequestMapping(value = "article/{articleId}", method = RequestMethod.GET)
    Result findById(@PathVariable("articleId") String articleId);

}
