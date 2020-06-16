package com.it.person.Controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.it.person.Bean.Router;
import com.it.person.Bean.User;
import com.it.person.Mapper.RouterMapper;
import com.it.person.Utils.EmailUtils;
import com.it.person.Utils.TokenUtil;
import com.it.person.Annotation.UserLoginToken;
import com.it.person.Service.TokenService;
import com.it.person.Service.UserService;
import com.it.person.Annotation.UserLoginToken;
import com.it.person.Service.TokenService;
import com.it.person.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

//    @Autowired
//    TokenService tokenService;
    //路由控制
    @Autowired
    RouterMapper routerMapper;

    String emailcodecheck = null;
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        Map<String,Object> map = new HashMap<>();
        //根据用户名获取token
//        User user = userService.findUserByUsername(username);
        //拿到认证的对象shiro 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //shiro的token //封装用户的登录数据
        UsernamePasswordToken token1 = new UsernamePasswordToken(username,password);
        try {
            subject.login(token1);//执行登录方法，没有异常jiu ok
//            String token = tokenService.getToken(user);
//            System.out.println("shiro:"+token1);
//            System.out.println("Token"+token);
//            map.put("token", token);
            ArrayList<Router> router = routerMapper.GetRouterList(username);
            map.put("routerList",router);
            map.put("success",true);
            return map;
        }catch (UnknownAccountException e){
            map.put("message", "用户不存在");
            return map;
        }catch (IncorrectCredentialsException e){
            map.put("message", "登录失败,密码错误");
            return map;
        }
//        //判断用户名是否存在
//        if (user == null) {
//            map.put("message", "用户不存在");
//            return map;
//        }else if(!password.equals(user.getPassword())){
//            //检验密码
//            map.put("message", "登录失败,密码错误");
//            return map;
//        }else {
//            String token = tokenService.getToken(user);
//            map.put("token", token);
//            map.put("success",true);
//            return map;
//        }
    }
    //注册接口
    @PostMapping("/sigon")
    public Map<String,String> Signon(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("email") String email,
                                     @RequestParam("emailcode") String emailcode){
        return userService.SignOn(username,password,email,emailcode,emailcodecheck);
    }
    //注册邮箱()
    @Autowired
    EmailUtils emailUtils;
    @PostMapping("/email")
    public Map<String,String> GetEmailCode(@RequestParam("email") String email){
        Map<String,String> map = new HashMap<>();
        try{
            String s = emailUtils.SendEmial(email);
            System.out.println(s);
            emailcodecheck = s;
            map.put("success","获取验证码成功");
            return map;
        }catch (Exception e){
            map.put("err","获取验证码失败");
            return map;
        }
    }
    //去登陆
    @GetMapping("/tologin")
    public Map<String,String> TOLogin(){
        Map<String,String> map = new HashMap<>();
        map.put("message","请重新登陆");
        return map;
    }
    //未授权的返回
    @RequestMapping("/noauth")
    public Map<String,String> Unauthorized(){
        Map<String,String> map = new HashMap<>();
        map.put("message","未经授权无法范访问此页面");
        System.out.println("没有授权");
        return map;
    }
}
