package com.wjh.model;

public class Fund {
    private Object id;
    private String code;
    private String name;
    private String found_time;
    private String fund_company;
    private String fund_manage;
    private String type;

    public Fund(Object id, String code, String name, String found_time, String fund_company, String fund_manage, String type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.found_time = found_time;
        this.fund_company = fund_company;
        this.fund_manage = fund_manage;
        this.type = type;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFound_time() {
        return found_time;
    }

    public void setFound_time(String found_time) {
        this.found_time = found_time;
    }

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getFund_manage() {
        return fund_manage;
    }

    public void setFund_manage(String fund_manage) {
        this.fund_manage = fund_manage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
