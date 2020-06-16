package com.it.person.Controller;

import com.it.person.Annotation.UserLoginToken;
import com.it.person.Service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class DownloadController {
    @Autowired
    DownloadService downloadService;
    @PostMapping("/download")
    public String Download(@RequestParam("type") String type,
                                       @RequestParam("filename") String filename,
                                       HttpServletResponse response){
        return downloadService.Download(type,filename,response);
    }
}
