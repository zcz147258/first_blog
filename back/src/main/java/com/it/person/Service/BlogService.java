package com.it.person.Service;

import com.it.person.Bean.Blog;
import com.it.person.Mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    BlogMapper blogMapper;
    public Map<String,String> SaveBlog(String username,String title,String tagList,String content,String imageurl,String radio){
        Map<String,String> map = new HashMap<>();
        try {
            blogMapper.SaveBlog(username,title,tagList,content,imageurl,radio);
            map.put("success","添加成功");
            return map;
        }catch (Exception e){
            System.out.println(e);
            map.put("err","添加失败");
            return map;
        }
    }
    public Map<String,Object> GetBlog(){
        Map<String,Object> map = new HashMap<>();
        try{
            ArrayList<Blog> blogs = blogMapper.GetBlog();
            map.put("success","获取博客成功");
            map.put("bloginfo",blogs);
            return map;
        }catch (Exception e){
            map.put("err","获取博客失败");
            return map;
        }
    }
}
