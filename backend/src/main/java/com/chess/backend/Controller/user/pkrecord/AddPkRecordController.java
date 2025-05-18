package com.chess.backend.Controller.user.pkrecord;

import com.chess.backend.mapper.PkRecordMapper;
import com.chess.backend.server.user.pkrecord.AddPkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddPkRecordController {
    @Autowired
    AddPkRecordService addPkRecordService;


    @PostMapping("/user/pkrecord/addpkrecord/")
    private Map<String,String> addPkRecord(@RequestParam Map<String,String> data)
    {
        Integer userid=Integer.parseInt(data.get("userid"));
        String username=data.get("username");
        String userphoto=data.get("userphoto");

        Integer opponentid=Integer.parseInt(data.get("opponentid"));
        String opponentname=data.get("opponentname");
        String opponentphoto=data.get("opponentphoto");


        String winner=data.get("winner");
        return     addPkRecordService.AddPkRecord(userid,username,userphoto,opponentid,opponentname,opponentphoto,winner);




    }

}
