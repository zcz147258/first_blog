package com.it.person.Controller;

import com.it.person.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping("/getnote")
    public Map<String, Object> GetNote() {
        return noteService.GetNote();
    }

    @RequestMapping("/scannote")
    public String readImg(@RequestParam("path") String path,HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        String url = path;
        try {
            //获取图片存放路径 
            File file = new File(url);
            if (!file.exists()) {
                return null;
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流  
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }
        return null;
    }
}
