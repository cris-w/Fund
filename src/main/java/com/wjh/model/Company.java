package com.wjh.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Company {
    private Object id;
    private String name;
    private String type;
    private String found_time;
    @Field("fund")
    private String fund[];

    public Company(Object id, String name, String type, String found_time, String[] fund) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.found_time = found_time;
        this.fund = fund;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFound_time() {
        return found_time;
    }

    public void setFound_time(String found_time) {
        this.found_time = found_time;
    }

    public String[] getFund() {
        return fund;
    }

    public void setFund(String[] fund) {
        this.fund = fund;
    }
}
