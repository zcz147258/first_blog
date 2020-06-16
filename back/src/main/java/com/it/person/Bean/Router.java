package com.it.person.Bean;

import java.io.Serializable;

public class Router implements Serializable {
    private Integer id;
    private String routername;
    private String icon;
    private String routerpath;

    public String getRouterpath() {
        return routerpath;
    }

    public void setRouterpath(String routerpath) {
        this.routerpath = routerpath;
    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                ", routername='" + routername + '\'' +
                ", icon='" + icon + '\'' +
                ", routerpath='" + routerpath + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoutername() {
        return routername;
    }

    public void setRoutername(String routername) {
        this.routername = routername;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
