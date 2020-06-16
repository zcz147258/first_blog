package com.it.person.Mapper;

import com.it.person.Bean.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BlogMapper {
    @Insert("INSERT INTO blog(username,blog_title,blog_tag,blog_content,create_time,imageurl,radio)" +
            "VALUES(#{username},#{title},#{tagList},#{content},NOW(),#{imageurl},#{radio})")
    public void SaveBlog(String username,String title,String tagList,String content,String imageurl,String radio);

    @Select("SELECT * FROM blog")
    public ArrayList<Blog> GetBlog();
}
