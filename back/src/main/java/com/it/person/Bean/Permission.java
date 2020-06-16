package com.it.person.Bean;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public class Permission implements Serializable {
    private String perms;

    @Override
    public String toString() {
        return "Permission{" +
                "perms='" + perms + '\'' +
                '}';
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
