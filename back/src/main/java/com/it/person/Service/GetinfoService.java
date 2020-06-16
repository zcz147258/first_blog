package com.it.person.Service;

import com.it.person.Bean.Upload;
import com.it.person.Mapper.GetInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetinfoService {
    @Autowired
    GetInfoMapper getInfoMapper;
    public Map<String,Object> findInfo(String username,String type){
        Map<String,Object> map = new HashMap<>();
        ArrayList<Upload> uploads= getInfoMapper.findInfo(username,type);
        System.out.println(uploads);
        map.put("success","获取数据");
        map.put("Info",uploads);
        return map;
    }
}
