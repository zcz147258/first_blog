package com.it.person.Config;

import com.it.person.Bean.Permission;
import com.it.person.Bean.User;
import com.it.person.Mapper.AuthorityMapper;
import com.it.person.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    AuthorityMapper authorityMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //执行授权
        //拿到当前登录用户对象
        Subject subject = SecurityUtils.getSubject();
        //获取user
        System.out.println(subject.getPrincipal());
        //强制转化为user
        User currentUser = (User) subject.getPrincipal();
        //获取权限
        ArrayList<Permission> permissionList = null;
        try{
           permissionList = authorityMapper.GetAuthority(currentUser.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> permissions = new ArrayList<String>();
        for(Permission  str:permissionList){
            permissions.add(str.getPerms());
        }
        //设置当前用户的权限
//        info.addStringPermission(currentUser.getPerms());
        info.addStringPermissions(permissions);//拿到权限
        System.out.println("info:"+ permissions);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        /*
        * 获取用户名密码
        * */
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //链接真实数据库
        User user = userService.findUserByUsername(userToken.getUsername());
        if(user == null){//没有这个用户
            return null;//抛出异常
        }
        //密码加密   mD5 把密码加密   MD5 盐值加密  无法破解
        //密码认证shiro自己做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
