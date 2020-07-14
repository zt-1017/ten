package com.ten.article.repository;

import com.ten.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {

    List<Comment> findByArticleid(String articleId);
}
