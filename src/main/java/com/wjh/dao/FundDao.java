package com.wjh.dao;

import com.wjh.model.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FundDao extends MongoRepository<Fund, Object> {
    // 通过名称查询基金
    Fund findByCode (String code);
}
