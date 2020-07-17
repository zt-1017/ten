package com.ten.notice.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ten.notice.client.ArticleClient;
import com.ten.notice.client.UserClient;
import com.ten.notice.dao.NoticeDao;
import com.ten.notice.dao.NoticeFreshDao;
import com.ten.notice.pojo.Notice;
import com.ten.notice.pojo.NoticeFresh;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClsaaName NoticeService
 * Version information 1.0
 * @Date 2020/7/16 14:47
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeFreshDao noticeFreshDao;

    @Autowired
    private IdWorker idWorker;

//    @Autowired
//    private ArticleClient articleClient;
//
//    @Autowired
//    private UserClient userClient;
////
//    private void getInfo(Notice notice) {
//        //完善用户姓名
//        Result userResult = userClient.findById(notice.getOperatorId());
//        HashMap userMap = (HashMap) userResult.getData();
//        notice.setOperatorName(userMap.get("nickname").toString());
//
//        //完善文章标题
//        Result articleResult = articleClient.findById(notice.getTargetId());
//        HashMap articleMap = (HashMap) articleResult.getData();
//        notice.setTargetName(articleMap.get("title").toString());
//    }


    public Notice selectById(String id) {
        Notice notice = noticeDao.selectById(id);
//        getInfo(notice);
        return notice;
    }

    public Page<Notice> selectPage(Notice notice, Integer page, Integer size) {
        //封装分页对象
        Page<Notice> pageDate = new Page<>(page,size);
        //执行分页查询
        List<Notice> noticeList = noticeDao.selectPage(pageDate, new EntityWrapper<>(notice));

//        for (Notice n : noticeList) {
//            getInfo(n);
//        }

        //设置结果集到分页对象中
        pageDate.setRecords(noticeList);
        return pageDate;
    }

    public void save(Notice notice) {
        //生产分布式id
        String id = idWorker.nextId() + "";

        //设置初始值
        //初始状态，0表示未读，1表示已读
        notice.setState("0");
        notice.setCreatetime(new Date());
        notice.setId(id);

        //消息保存
        noticeDao.insert(notice);

        //待推送消息
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setNoticeId(id); //消息id
        noticeFresh.setUserId(notice.getReceiverId()); //待接收用户id

        noticeFreshDao.insert(noticeFresh);
    }

    public void updataById(Notice notice) {
        noticeDao.updateById(notice);
    }

    public Page<NoticeFresh> freshPage(String userId, Integer page, Integer size) {
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setUserId(userId);

        Page<NoticeFresh> pageData = new Page<>(page,size);

        List<NoticeFresh> list = noticeFreshDao.selectPage(pageData, new EntityWrapper<>(noticeFresh));

        pageData.setRecords(list);

        return pageData;

    }

    public void freshDelete(NoticeFresh noticeFresh) {
        noticeFreshDao.delete(new EntityWrapper<>(noticeFresh));
    }
}
