package com.ten.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ten.article.pojo.Article;
import com.ten.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClsaaName ArticleController
 * Version information 1.0
 * @Date 2020/7/10 16:58
 */
@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    GET/article 文章全部列表
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Article> list = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功！", list);
    }

//    GET/article/{articleId}根据ID查询文章
    @RequestMapping(value = "{articleId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String articleId) {
        Article article = articleService.findById(articleId);
        return new Result(true, StatusCode.OK, "查询成功！", article);
    }

//    POST/article 增加文章
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "新增成功！");
    }

//    DELETE/article/{articleId}根据ID删除文章
    @RequestMapping(value = "{articleId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String articleId) {
        articleService.deleteById(articleId);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

//    PUT/article/{articleId}修改文章
    @RequestMapping(value = "{articleId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable String articleId,
                             @RequestBody Article article) {

        article.setId(articleId);

        articleService.updateById(article);
        return new Result(true, StatusCode.OK, "修改成功！");
    }

//    POST/article/search/{page}/{size}文章分页
    @RequestMapping(value = "search/{page}/{size}",method = RequestMethod.POST)
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer size,
                             @RequestBody Map<String, Object> map){
        //根据条件查询
        Page<Article> pageDate = articleService.findByPage(page, size, map);

        //封装分页数据
        PageResult<Article> pageResult = new PageResult<>(
                pageDate.getTotal(), pageDate.getRecords()
        );

        return new Result(true, StatusCode.OK, "查询成功！", pageResult);
    }
}
