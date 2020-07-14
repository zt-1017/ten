package com.ten.article.controller;

import com.ten.article.pojo.Comment;
import com.ten.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClsaaName CommentController
 * Version information 1.0
 * @Date 2020/7/13 11:18
 */
@RestController
@RequestMapping(value = "comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;



    //PUT comment/thumbup/{commentId}  根据评论id点赞
    @RequestMapping(value = "Thumbup/{commentId}",method = RequestMethod.PUT)
    public Result thumpub(@PathVariable String commentId) {

        //获取用户id
        String userId = "123";

        //
        Object flag = redisTemplate.opsForValue().get("thumbup_" + commentId + "_" + userId);

        if(flag == null) {

            commentService.thumpub(commentId);

            redisTemplate.opsForValue().set("thumbup_" + commentId + "_" + userId, 1);

            return new Result(true, StatusCode.OK, "点赞成功！");
        }

        return new Result(false, StatusCode.REPERROR, "不要重复点赞");

    }

    //GET comment/article/{articleId}  根据文章id查询数据
    @RequestMapping(value = "article/{articleId}",method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String articleId) {
        List<Comment> list = commentService.findByArticleId(articleId);
        return new Result(true, StatusCode.OK, "查询评论成功！", list);
    }

    //Get comment 查询所有数据
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){

        List<Comment> list = commentService.findAll();

        return new Result(true, StatusCode.OK, "查询成功！", list);
    }

    //Get comment/{commentID} 根据id查询数据
    @RequestMapping(value = "{commentID}",method = RequestMethod.GET)
    public Result findById(@PathVariable String commentID) {
         Comment comment = commentService.findById(commentID);
        return new Result(true, StatusCode.OK, "查询成功！", comment);
    }

    //Post comment 新增数据
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return new Result(true, StatusCode.OK, "新增成功！");
    }

    //PUT comment/{commentId}  根据id修改数据
    @RequestMapping(value = "{commentId}",method = RequestMethod.PUT)
    public Result updataById(@PathVariable String commentId,@RequestBody Comment comment) {
        comment.set_id(commentId);
        commentService.updataById(comment);

        return new Result(true, StatusCode.OK, "修改成功！");
    }

    //DELETE comment/{commentId} 根据id删除数据
    @RequestMapping(value = "{commentId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String commentId) {
        commentService.deleteById(commentId);
        return new Result(true, StatusCode.OK, "删除成功！");
    }
}
