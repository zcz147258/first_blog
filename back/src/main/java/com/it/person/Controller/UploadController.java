package com.it.person.Controller;

import com.it.person.Annotation.UserLoginToken;
import com.it.person.Bean.User;
import com.it.person.Service.UploadService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UploadController {
    @Autowired
    UploadService uploadService;
    @PostMapping("/upload")
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file,
                                          @RequestParam("type") String type){
        //获取用户id
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        return uploadService.Upload(file,type,username);
    }
}
