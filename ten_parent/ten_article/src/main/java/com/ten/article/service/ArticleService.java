package com.ten.article.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ten.article.dao.ArticleDao;
import com.ten.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClsaaName ArticleService
 * Version information 1.0
 * @Date 2020/7/10 16:57
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public List<Article> findAll() {
        return articleDao.selectList(null);
    }

    public Article findById(String articleId) {
        return articleDao.selectById(articleId);
    }

    public void save(Article article) {
        //自动生成id
        String id = idWorker.nextId() + "";
        article.setId(id);

        //初始化
        article.setVisits(0);
        article.setThumbup(0);
        article.setComment(0);

        articleDao.insert(article);
    }

    public void deleteById(String articleId) {
        articleDao.deleteById(articleId);
    }

    public void updateById(Article article) {
        //根据id修改
        articleDao.updateById(article);

        //根据条件修改
        /*EntityWrapper<Article> wrapper = new EntityWrapper();
        wrapper.eq("id", article.getId());
        articleDao.update(article, wrapper);*/
    }

    public Page<Article> findByPage(Integer page, Integer size, Map<String, Object> map) {
        //设置查询条件
        EntityWrapper<Article> wrapper = new EntityWrapper<>();

        //设置查询参数
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            wrapper.eq(map.get(key) != null, key, map.get(key));
        }

        Page<Article> pageDate = new Page<>(page, size);

        List<Article> list = articleDao.selectPage(pageDate, wrapper);

        pageDate.setRecords(list);

        return pageDate;
    }
}
