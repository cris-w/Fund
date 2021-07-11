package com.wjh.service;

import com.alibaba.fastjson.JSON;
import com.wjh.dao.CompanyDao;
import com.wjh.dao.FundDao;
import com.wjh.dao.UserDao;
import com.wjh.dao.UserOneDao;
import com.wjh.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class fundService {
    @Autowired
    UserDao userDao;
    @Autowired
    FundDao fundDao;
    @Autowired
    CompanyDao companyDao;
    @Autowired
    UserOneDao dao;
    // 登陆 通过ID 和 pwd查询 存在返回true
    public boolean login(String id, String password){
        List<User> flag = userDao.findByIdAndPassword(id, password);
//        System.out.println("flag:"+flag);
        if (flag.size() > 0){

            return true;
        }
        return false;
    }
    // 查看个人收藏基金
    public CollectUi getCollection(String id){
        CollectUi ui = new CollectUi();
        String[] collection = userDao.findById(id).get(0).getCollect();
        List<Fund> list = new ArrayList<Fund>();
        for(String c : collection){
            Fund fund = fundDao.findByCode(c);
            list.add(fund);
        }
        ui.setCode(0);
        ui.setData(list);
        ui.setMsg(" ");
        return ui;
    }
    // 查看个人持有基金
    public HoldUi getHold(String id){
        HoldUi ui = new HoldUi();
        String msg = " ";
        List<hold> hold = userDao.findById(id).get(0).getHold();
        ui.setCode(0);
        ui.setData(hold);
        ui.setMsg(msg);
        return ui;
    }
    // 查询基金基本信息
    public FundUi findByCode(String code){
        FundUi ui = new FundUi();
        List<Fund> data = new ArrayList<Fund>();
        ui.setMsg(" ");
        ui.setCode(0);
        data.add(fundDao.findByCode(code));
        ui.setData(data);
        return ui;
    }
    // 查询所有基金
    public FundUi findAllFund() {
        FundUi ui = new FundUi();
        List<Fund> all = fundDao.findAll();
        ui.setMsg(" ");
        ui.setCode(0);
        ui.setData(all);
        return ui;
    }
    // 查询基金公司基本信息
    public Company findCompanyByName(String name){
        return companyDao.findByName(name);
    }
    // 查询所有基金公司
    public CompanyUi findAllCompany(){
        CompanyUi ui = new CompanyUi();
        List<Company> all = companyDao.findAll();
        ui.setMsg(" ");
        ui.setCode(0);
        ui.setData(all);
        return ui;
    }
    //查询基某金公司旗下基金
    public FundUi findFund(String name){
        FundUi ui = new FundUi();
        Company company = companyDao.findByName(name);
        String[] fund = company.getFund();
        List<Fund> list = new ArrayList<Fund>();
        for(String c : fund){
            Fund f = fundDao.findByCode(c);
            list.add(f);
        }
        ui.setCode(0);
        ui.setMsg(" ");
        ui.setData(list);
        return ui;
    }
    // 取消收藏某基金
    public String deleteCollection(String id, String col){
        Map<String ,String> map = new HashMap<String, String>();
        map.put("mode","delete one collection");
        List<User> list = userDao.findById(id);
        User user = list.get(0);
        String[] collection = user.getCollect();
//        将String数组转换为 List的两种方法
        List<String> newCOl = new ArrayList<>(Arrays.asList(collection));
//        List<String> newCOl = Arrays.stream(collection).collect(Collectors.toList());
        newCOl.remove(col);
        String[] coll = newCOl.toArray(new String[newCOl.size()]);
        user.setCollect(coll);
        try{
            userDao.save(user);
            map.put("mode","success");
        }catch(Exception e){
            map.put("mode",e.getMessage());
        }
        return JSON.toJSONString(map);
    }
    // 收藏某基金
    public String addCollection(String id, String col){
        Map<String ,String> map = new HashMap<String, String>();
        map.put("mode","add one collection");
        List<User> list = userDao.findById(id);
        User user = list.get(0);
        String[] collection = user.getCollect();
        for (String s: collection) {
            if (s.equals(col)){
                map.put("mode","fail");
                return JSON.toJSONString(map);
            }
        }
        List<String> newCOl = new ArrayList<>(Arrays.asList(collection));
        newCOl.add(col);
        String[] coll = newCOl.toArray(new String[newCOl.size()]);
        user.setCollect(coll);
        try{
            userDao.save(user);
            map.put("mode","success");
        }catch(Exception e){
            map.put("mode",e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    // 买入基金
    public String addHold (hold h,String id){
        Map<String ,String> map = new HashMap<String, String>();
        map.put("mode","add one hold");
        try{
            dao.addHold(h,id);
            map.put("mode","success");
        }catch(Exception e){
            map.put("mode",e.getMessage());
        }
        return JSON.toJSONString(map);
    }
    // 卖出基金
    public String delHold (String code,String id){
        Map<String ,String> map = new HashMap<String, String>();
        map.put("mode","del one hold");
        List<User> list = userDao.findById(id);
        User user = list.get(0);
        List<hold> list1 = user.getHold();
        for (int i = 0; i < list1.size(); i++){
            if (list1.get(i).getCode().equals(code)){
                list1.remove(list1.get(i));
            }
        }
        user.setHold(list1);
        try{
            userDao.save(user);
            map.put("mode","success");
        }catch(Exception e){
            map.put("mode",e.getMessage());
        }
        return JSON.toJSONString(map);
    }

    // 通过基金类型对个人持有基金份额进行汇总
    public List<sum> sum (String id){
        List<sum> list = new ArrayList<sum>();
        sum share = new sum(0,"股票基金"); // 股票
        sum debenture = new sum(0,"债券基金"); // 债券
        sum mix = new sum(0,"混合基金"); // 混合
        sum numb = new sum(0,"指数基金"); // 指数
        List<User> users = userDao.findById(id);
        User user = users.get(0);
        List<hold> hold = user.getHold();
        for(hold h : hold){
            String code = h.getCode();
            if(code.equals("001") || code.equals("002") ){
                debenture.setValue(debenture.getValue()+h.getQuotient());
            }else if(code.equals("005") || code.equals("008")){
                mix.setValue(mix.getValue()+h.getQuotient());
            }else if (code.equals("009") || code.equals("010")){
                numb.setValue(numb.getValue()+h.getQuotient());
            }
            else {
                share.setValue(share.getValue()+h.getQuotient());
            }
        }
        list.add(share);
        list.add(mix);
        list.add(debenture);
        list.add(numb);
        return list;
    }
    // 通过基金类型对个人持有基金总金额进行汇总
    public List sumPay (String id){
        List list = new ArrayList();
        int share = 0; // 股票
        int debenture = 0; // 债券
        int mix = 0; // 混合
        int numb = 0; // 指数
        List<User> users = userDao.findById(id);
        User user = users.get(0);
        List<hold> hold = user.getHold();
        for(hold h : hold){
            String code = h.getCode();
            if(code.equals("001") || code.equals("002") ){
                debenture += h.getPay();
            }else if(code.equals("005") || code.equals("008")){
                mix += h.getPay();
            }else if (code.equals("009") || code.equals("010")){
                numb += h.getPay();
            }
            else {
                share += h.getPay();
            }
        }
        list.add(share);
        list.add(mix);
        list.add(debenture);
        list.add(numb);
        return list;
    }
}
