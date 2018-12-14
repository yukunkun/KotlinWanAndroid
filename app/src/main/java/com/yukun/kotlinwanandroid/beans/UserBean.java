package com.yukun.kotlinwanandroid.beans;

import org.litepal.crud.LitePalSupport;

import java.util.List;

/**
 * author: kun .
 * date:   On 2018/12/14
 */
public class UserBean extends LitePalSupport{
    private int id;
    private String username;
    private String password;
    private Object icon;
    private int type;
    private List<Integer> collectIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", icon=" + icon +
                ", type=" + type +
                ", collectIds=" + collectIds +
                '}';
    }
}
