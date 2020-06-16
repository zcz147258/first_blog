package com.it.person.Mapper;

import com.it.person.Bean.Router;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface RouterMapper {
    @Select("SELECT r.routername,r.id,r.icon,r.routerpath FROM user u \n" +
            "LEFT JOIN user_role ur ON ur.user_id = u.id\n" +
            "LEFT JOIN role_router rr ON rr.role_id = ur.role_id\n" +
            "LEFT JOIN router r ON r.id = rr.router_id\n" +
            "WHERE u.username = #{username}")
    public ArrayList<Router> GetRouterList(String username);
}
