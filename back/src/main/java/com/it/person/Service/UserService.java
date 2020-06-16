package com.it.person.Service;

import com.it.person.Bean.User;
import com.it.person.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    //token检验根据id检验
    public User findUserById(Integer id){
        User user = userMapper.findUserById(id);
        if (user == null){
            return null;
        }else {
            return user;
        }
    }
    //查询用户
    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }
    //注册用户
    public Map<String,String> SignOn(String username,String password, String email,String emailcode,String emailcodecheck){
        Map<String,String> map = new HashMap<>();
        if(emailcode.equals(emailcodecheck)){
            try {
                userMapper.SignOn(username,password,email);
                userMapper.SignOnrole(username);
                map.put("success","success");
            }catch (Exception e){
                map.put("err","err");
            }
        }
        return map;
    }
}
