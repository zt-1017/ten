package com.ten.article.service;

import com.ten.article.dao.ArticleDao;
import com.ten.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClsaaName ArticleService
 * Version information 1.0
 * @Date 2020/7/15 08:59
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<Article> selectAll() {
        List<Article> list = articleDao.selectList(null);
        return list;
    }
}
