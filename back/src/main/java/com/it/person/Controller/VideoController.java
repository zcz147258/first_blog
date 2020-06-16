package com.it.person.Controller;

import com.it.person.Annotation.UserLoginToken;
import com.it.person.Utils.NonStaticResourceHttpRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
public class VideoController {
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    @GetMapping("/playvideo")
    public void videoPreview(@RequestParam("name") String name,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取请求参数
        String path = "/root/jar/person_web/uploadtest/mp4/"+ name;
        Path filePath = Paths.get(path);
        System.out.println(filePath);

        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}
