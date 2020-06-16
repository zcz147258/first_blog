package com.it.person.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    private Integer blog_id;
    private String username;
    private String blog_title;
    private String blog_tag;
    private String blog_content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date create_time;
    private String view;
    private String comment;
    private String dianzan;
    private String imageurl;

    @Override
    public String toString() {
        return "Blog{" +
                "blog_id=" + blog_id +
                ", username='" + username + '\'' +
                ", blog_title='" + blog_title + '\'' +
                ", blog_tag='" + blog_tag + '\'' +
                ", blog_content='" + blog_content + '\'' +
                ", create_time=" + create_time +
                ", view='" + view + '\'' +
                ", comment='" + comment + '\'' +
                ", dianzan='" + dianzan + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", radio='" + radio + '\'' +
                '}';
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    private String radio;

    public Integer getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Integer blog_id) {
        this.blog_id = blog_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_tag() {
        return blog_tag;
    }

    public void setBlog_tag(String blog_tag) {
        this.blog_tag = blog_tag;
    }

    public String getBlog_content() {
        return blog_content;
    }

    public void setBlog_content(String blog_content) {
        this.blog_content = blog_content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDianzan() {
        return dianzan;
    }

    public void setDianzan(String dianzan) {
        this.dianzan = dianzan;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
