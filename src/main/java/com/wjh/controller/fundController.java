package com.wjh.controller;

import com.wjh.dao.UserOneDao;
import com.wjh.model.*;
import com.wjh.service.fundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fund")
public class fundController {
    @Autowired
    fundService service;

    @Autowired
    UserOneDao userOneDao;
    // 登陆
        @RequestMapping("/Login")
    public boolean Login (String  id, String password){
        return service.login(id, password);
    }

    // 查看个人收藏
    @RequestMapping("getCollection")
    public CollectUi getCollection(String id){
        return service.getCollection(id);
    }

    // 查看个人持有基金
    //@CrossOrigin
    @RequestMapping("/getHold")
    public HoldUi getHold(String id){
        return service.getHold(id);
    }

    // 查询所有基金
    @RequestMapping("/findAllFund")
    public FundUi findAllFund(){
        return service.findAllFund();
    }

    // 查询基金基本信息
    @RequestMapping("/findByCode")
    public FundUi findByCode(String code){
        return service.findByCode(code);
    }

    //查询所有基金公司
    @RequestMapping("/findAllCompany")
    public CompanyUi findAllCompany(){
        return service.findAllCompany();
    }

    // 通过名称查询公司
    @RequestMapping("/findCompanyByName")
    public Company findCompanyByName(String name){
            return service.findCompanyByName(name);
    }

    // 查询某公司旗下基金
    @RequestMapping("/findFund")
    public FundUi findFund(String name){
        return service.findFund(name);
    }

    // 取消收藏基金
    @RequestMapping("/delCol")
    public String delCol(String id, String col){
        return service.deleteCollection(id,col);
    }

    // 收藏某基金
    @RequestMapping("/addCol")
    public String addCol(String id, String col){
        return service.addCollection(id,col);
    }

    // 购买基金
    @RequestMapping("/addHold")
    public String addHold(HttpServletRequest request,String id){
        hold h = new hold();
        h.setCode(request.getParameter("code"));
        h.setBuy_time(request.getParameter("buy_time"));
        h.setQuotient(Integer.parseInt(request.getParameter("quotient")));
        h.setNet_value(Integer.parseInt(request.getParameter("net_value")));
        h.setPay(Integer.parseInt(request.getParameter("pay")));
        h.setHold_state(request.getParameter("hold_state"));
        return service.addHold(h,id);
    }

    // 卖出基金
    @RequestMapping("/delHold")
    public String delHold(String code,String id){
        return service.delHold(code,id);
    }

    // 通过基金类型对个人持有基金份额进行汇总
    @RequestMapping("/sum")
    public List<sum> sum(String id){
        return  service.sum(id);
    }

    // 通过基金类型对个人持有基金份额进行汇总
    @RequestMapping("/sumPay")
    public List sumPay(String id){
        return  service.sumPay(id);
    }
}
