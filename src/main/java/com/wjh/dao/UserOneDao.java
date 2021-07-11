package com.wjh.dao;

import com.wjh.model.User;
import com.wjh.model.hold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserOneDao {
    @Autowired
    MongoTemplate template;
    // 购买基金（添加内嵌文档）
    public void addHold(hold h, String id){
        User user  = template.findOne(new Query(Criteria.where("_id").is(id)), User.class);
        if (user != null){
            List<hold> hold = user.getHold();
            boolean flag = true;
            for (hold ho : hold){
                if (ho.getCode().equals(h.getCode())){
                    ho.setQuotient(ho.getQuotient()+h.getQuotient());
                    ho.setNet_value(ho.getNet_value()+h.getNet_value());
                    ho.setPay(ho.getPay()+h.getPay());
                    flag = false;
                }
            }
            if (flag){
                hold.add(h);
            }
            template.save(user);
        }
    }

    // 卖出基金（删除内嵌文档）
//    public void delHold(hold h, String id){
//        User user  = template.findOne(new Query(Criteria.where("_id").is(id)), User.class);
//        if (user != null){
//            user.getHold().remove(h);
//            template.save(user);
//        }
//    }

}
