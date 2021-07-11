package com.wjh.dao;

import com.wjh.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDao extends MongoRepository<User, Object> {
    // 登陆
    List<User> findByIdAndPassword(String id, String password);
    // 通过id获取用户信息
    List<User> findById(String id);
}
