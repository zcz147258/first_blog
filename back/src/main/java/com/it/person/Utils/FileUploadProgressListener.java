package com.it.person.Utils;

import org.apache.tomcat.util.http.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
/*
* 上传监听器
* 进度加载
* */
@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
        session.setAttribute("upload_percent", 0);
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        int percent = (int) (pBytesRead * 100.0 / pContentLength);
        session.setAttribute("upload_percent", percent);
    }
}
