package com.chess.backend.server.user.pkrecord;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AddPkRecordService {
    Map<String,String> AddPkRecord(Integer userid,String username,String userphoto,Integer opponentid,String opponentname,String opponentphoto,String winner);

}
