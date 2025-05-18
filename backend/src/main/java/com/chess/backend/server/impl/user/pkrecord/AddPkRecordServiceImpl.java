package com.chess.backend.server.impl.user.pkrecord;

import com.chess.backend.mapper.PkRecordMapper;
import com.chess.backend.pojo.Pkrecord;
import com.chess.backend.server.user.pkrecord.AddPkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AddPkRecordServiceImpl implements AddPkRecordService {
    @Autowired
    PkRecordMapper pkRecordMapper;

    @Override
    public Map<String, String> AddPkRecord(Integer userid, String username, String userphoto, Integer opponentid, String opponentname, String opponentphoto,String winner) {

        Pkrecord pkrecord=new Pkrecord(userid,username,userphoto,opponentid,opponentname,opponentphoto,winner);

        pkRecordMapper.insert(pkrecord);

        Map<String,String> map=new HashMap<>();
        map.put("success","addSuccess");
        return map;

    }
}
