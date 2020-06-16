package com.it.person.Mapper;

import com.it.person.Bean.Upload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
@Mapper
public interface GetInfoMapper {
    @Select("SELECT * FROM upload WHERE username = #{username} AND type = #{type}")
    public ArrayList<Upload> findInfo(String username, String type);
}
