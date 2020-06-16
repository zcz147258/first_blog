package com.it.person.Mapper;

import com.it.person.Bean.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Component
@Mapper
@Repository
public interface UploadNoteMapper {
    @Insert("INSERT INTO note(name,path,username) VALUES(#{fileName},#{path},#{username})")
    public void Upload(String fileName, String path, String username);

    @Select("SELECT * FROM note")
    public ArrayList<Note> GetNote();
}
