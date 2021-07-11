package com.wjh.model;

import javax.xml.ws.Holder;
import java.util.List;

public class HoldUi {
    private List<hold> data;
    private Integer code;
    private String msg;

    public List<hold> getData() {
        return data;
    }

    public void setData(List<hold> data) {
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
