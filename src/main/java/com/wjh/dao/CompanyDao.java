package com.wjh.dao;

import com.wjh.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyDao extends MongoRepository<Company, Object> {
    // 通过名称查询公司
    Company findByName(String name);

}
