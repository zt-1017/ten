package com.ten.user.service;

import com.ten.user.dao.UserDao;
import com.ten.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClsaaName UserService
 * Version information 1.0
 * @Date 2020/7/14 09:09
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //根据用户id查询用户
    public User findById(String userId) {
        User user = userDao.selectById(userId);
        return user;
    }

    public User login(User user) {
        User one = userDao.selectOne(user);
        return one;
    }



}
