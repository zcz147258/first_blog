package com.it.person.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Mapper
@Repository
public interface UploadImageMapper {
    @Insert("INSERT INTO image(name,path,username) VALUES(#{fileName},#{path},#{username})")
    public void Upload(String fileName,String path,String username);
}
