package com.wjh.model;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String password;
    @Field("collect")
    private String collect[];
    private List<hold> hold;

    public User(String id, String name, String password, String[] collect, List<hold> hold) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.collect = collect;
        this.hold = hold;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getCollect() {
        return collect;
    }

    public void setCollect(String[] collection) {
        this.collect = collection;
    }

    public List<com.wjh.model.hold> getHold() {
        return hold;
    }

    public void setHold(List<com.wjh.model.hold> hold) {
        this.hold = hold;
    }
}
