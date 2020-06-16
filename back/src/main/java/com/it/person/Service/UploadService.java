package com.it.person.Service;

import com.it.person.Mapper.UploadImageMapper;
import com.it.person.Mapper.UploadNoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UploadService {
    @Autowired
    UploadNoteMapper uploadNoteMapper;
    @Autowired
    UploadImageMapper uploadImageMapper;

    Map<String,Object> map = new HashMap<>();
    public Map<String,Object> Upload(MultipartFile file,String type,String username){
        if(file.isEmpty()){
            map.put("err","不能上传空信息");
            return map;
        }
        System.out.println("文件" + file);
        System.out.println("用户名" + username);
        //获取完整文件名称
        String fileName = file.getOriginalFilename();
        Integer size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
//        String path = "/root/jar/person_web/uploadtest" ;
//        String path = "
//        D:/testupload" ;
//        File dest = new File(path + "/" + fileName);
//        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在,不存在就创建
//            dest.getParentFile().mkdir();
//        }
        //判断文件后缀
        Integer last = fileName.lastIndexOf('.');
        String dotname = fileName.substring(last+1);
        System.out.println(dotname);
        //只能捕获后缀为html | mp4 | exe | zip | jpg |png |
        //存入数据库
        //定义正则表达式
        String regEx = "";
        if (type.equals("image")){
            regEx = "^((jpg)|(png))$";
        }else {
            regEx = "^((md))$";
        }
        //编译成为正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(dotname);
        boolean rs = matcher.matches();
        System.out.println(rs);
        if(rs){
            //System.out.println("正则校验成功");
            map.put("regEX",true);
            File cunchu = new File("/root/jar/person_web/uploadtest/"+type+"/"+fileName);
            String path = cunchu.getAbsolutePath();
            try {
                file.transferTo(cunchu); //保存文件
                if (type.equals("image")){
                    uploadImageMapper.Upload(fileName,path,username);
                }else {
                    uploadNoteMapper.Upload(fileName,path,username);
                }
                map.put("success","上传成功");
                return map;
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("err","上传错误");
                return map;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("err","上传错误");
                return map;
            }
        }else {
            //System.out.println("正则校验失败");
            map.put("regEX",false);
            return map;
        }
        //分类 根据type
            //存到表note
        //创建目录
        //File checkexits = new File("/root/jar/person_web/uploadtest/"+dotname+"/");
//        File checkexits = new File("D:/testupload/"+dotname+"/");
//        System.out.println(checkexits.exists());
//        if(checkexits.exists()){
//            //System.out.println("存在目录");
//        }else {
//            checkexits.mkdir();
//        }
        //File cunchu = new File("/root/jar/person_web/uploadtest/"+dotname+"/"+fileName);
    }
}
