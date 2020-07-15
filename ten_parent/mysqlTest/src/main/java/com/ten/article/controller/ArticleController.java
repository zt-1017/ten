package com.ten.article.controller;

import com.ten.article.pojo.Article;
import com.ten.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClsaaName ArticleController
 * Version information 1.0
 * @Date 2020/7/15 08:59
 */
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //GET article
    @RequestMapping(method = RequestMethod.GET)
    public Result selectAll() {

        List<Article> list = articleService.selectAll();
        return new Result(true, StatusCode.OK, "查询成功！", list);
    }

}
