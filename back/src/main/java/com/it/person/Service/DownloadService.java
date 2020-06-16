package com.it.person.Service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class DownloadService {
    Map<String, Object> map = new HashMap<>();

    public String Download(String type, String filename, HttpServletResponse response) {
        //被下载的文件在服务器中的路径,
        String downloadFilePath = "/root/jar/person_web/uploadtest/" + type + "/" + filename;
        //被下载文件的名称
        String fileName = filename;
        System.out.println(downloadFilePath);
        System.out.println(fileName);
        File file = new File(downloadFilePath);
        //设置跨域
        if (file.exists()) {
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/octet-stream");// 设置强制下载不打开  
            //response.setContentType("application/force-download");// 设置强制下载不打开//          
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            //输入流
            FileInputStream fis = null;
            //缓存区
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
            return "文件不存在";
    }
}
