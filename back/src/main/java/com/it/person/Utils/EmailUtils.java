package com.it.person.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class EmailUtils {
    @Autowired
    JavaMailSenderImpl mailSender;
    //获取随机数
    public static String getRandomFourNum() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        //打乱
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }
    public String SendEmial(String email){
        String code = getRandomFourNum();
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件信息
        message.setSubject("www.zczsylm.top验证码登陆");
        message.setText("你注册www.zczsylm.top的验证码为"+code);
        message.setTo(email);
        message.setFrom("1092283755@qq.com");
        mailSender.send(message);
        return code;
    }
}
