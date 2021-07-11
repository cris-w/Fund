package com.wjh.model;

import java.util.List;

public class FundUi {
    private List<Fund> data;
    private Integer code;
    private String msg;

    public List<Fund> getData() {
        return data;
    }

    public void setData(List<Fund> data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
