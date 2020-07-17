package com.ten.notice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ten.notice.pojo.Notice;
import com.ten.notice.pojo.NoticeFresh;
import com.ten.notice.service.NoticeService;
import entity.PageResult;
import entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 * @ClsaaName NoticeController
 * Version information 1.0
 * @Date 2020/7/16 14:48
 */
@RestController
@RequestMapping("notice")
@CrossOrigin
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //GET /notice/{id} 根据id查询消息
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result selectById(@PathVariable String id) {
        Notice notice = noticeService.selectById(id);
        return new Result(true, StatusCode.OK, "查询成功！", notice);
    }

    //POST /notice/search/{page}/{size}  根据条件分页查询
    @RequestMapping(value = "search/{page}/{size}",method = RequestMethod.POST)
    public Result selectPage(@RequestBody Notice notice,
                                 @PathVariable Integer page,
                                 @PathVariable Integer size){
        Page<Notice> pageData = noticeService.selectPage(notice, page, size);

        PageResult<Notice> result = new PageResult<>(pageData.getTotal(), pageData.getRecords());

        return new Result(true, StatusCode.OK, "查询成功！", result);
    }

    //POST /notice 新增消息
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Notice notice) {
        noticeService.save(notice);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    //PUT /notice/ 修改消息
    @RequestMapping(method = RequestMethod.PUT)
    public Result updataById(@RequestBody Notice notice) {
        noticeService.updataById(notice);
        return new Result(true, StatusCode.OK, "修改成功！");
    }

    //GET /notice/fresh/{userId}/{page}/{size}  根据用户id查询待查看消息
    @RequestMapping(value = "fresh/{userId}/{page}/{size}",method = RequestMethod.GET)
    public Result freshPage(@PathVariable String userId,
                            @PathVariable Integer page,
                            @PathVariable Integer size) {
        Page<NoticeFresh> pageData = noticeService.freshPage(userId, page, size);

        PageResult<NoticeFresh> noticeFreshPageResult = new PageResult<>(pageData.getTotal(),pageData.getRecords());

        return new Result(true, StatusCode.OK, "查询成功！", noticeFreshPageResult);


    }

    //DELETE /notice/fresh 删除待推送消息
    @RequestMapping(value = "fresh",method = RequestMethod.DELETE)
    public Result freshDelete(@RequestBody NoticeFresh noticeFresh) {
        noticeService.freshDelete(noticeFresh);
        return new Result(true, StatusCode.OK, "删除成功！");
    }
}
