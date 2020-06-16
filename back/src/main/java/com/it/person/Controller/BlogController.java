package com.it.person.Controller;

import com.it.person.Bean.User;
import com.it.person.Service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/postblog")
    public Map<String,String> SaveBlog(
                                       @RequestParam("title") String title,
                                       @RequestParam("tagList") String tagList,
                                       @RequestParam("content") String content,
                                       @RequestParam("imageurl") String imageurl,
                                       @RequestParam("radio") String radio){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        return blogService.SaveBlog(username,title,tagList,content,imageurl,radio);
    }
    @GetMapping("/getblog")
    public Map<String,Object> GetBlog(){
        return blogService.GetBlog();
    }
}
