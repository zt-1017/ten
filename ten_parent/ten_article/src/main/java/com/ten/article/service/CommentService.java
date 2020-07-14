package com.ten.article.service;

import com.ten.article.pojo.Comment;
import com.ten.article.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ClsaaName CommentService
 * Version information 1.0
 * @Date 2020/7/13 11:17
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> findAll() {

        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    public Comment findById(String commentID) {
        Optional<Comment> optional = commentRepository.findById(commentID);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void save(Comment comment) {
        //分布式id生成器
        String id = idWorker.nextId() + "";
        comment.set_id(id);

        //初始化点赞数、发布时间
        comment.setThumbup(0);
        comment.setPublishdate(new Date());

        //新增数据
        commentRepository.save(comment);
    }

    public void updataById(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> findByArticleId(String articleId) {
        List<Comment> comments = commentRepository.findByArticleid(articleId);
        return comments;
    }

    public void thumpub(String commentId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));


        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "comment");
    }
}
