package com.it.person.Service;

import com.it.person.Bean.Note;
import com.it.person.Mapper.UploadNoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class NoteService {
    @Autowired
    UploadNoteMapper uploadNoteMapper;
    public Map<String,Object> GetNote(){
        Map<String,Object> map = new HashMap<>();
        try{
            ArrayList<Note> notes = uploadNoteMapper.GetNote();
            map.put("success","success");
            map.put("noteinfo",notes);
            return map;
        }catch (Exception e){
            map.put("err",e);
            return map;
        }
    }
}
