package com.it.person.Mapper;

import com.it.person.Bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface AuthorityMapper {
    @Select("SELECT p.perms \n" +
            "FROM user a\n" +
            "LEFT JOIN user_role ur on a.id = ur.user_id\n" +
            "LEFT JOIN role r on ur.user_id = r.role_id\n" +
            "LEFT JOIN role_perms rp on rp.role_id = ur.role_id\n" +
            "LEFT JOIN permission p on p.perms_id = rp.perms_id\n" +
            "WHERE r.role_id = #{id}")
    public ArrayList<Permission> GetAuthority(Integer id);
}
