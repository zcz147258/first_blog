package com.it.person.Mapper;

import com.it.person.Bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    public User findUserByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User findUserById(Integer id);

    //插入注册用户信息
    @Insert("INSERT INTO user(username,password,email) VALUES(#{username},#{password},#{email})")
    public void SignOn(String username,String password,String email);

    @Insert("INSERT INTO user_role(user_id,role_id)" +
            "SELECT id,3 FROM user WHERE username = #{username}")
    public void SignOnrole(String username);
}
