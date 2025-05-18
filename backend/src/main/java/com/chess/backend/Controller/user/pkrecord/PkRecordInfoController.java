package com.chess.backend.Controller.user.pkrecord;

import com.chess.backend.pojo.Pkrecord;
import com.chess.backend.server.user.pkrecord.PkRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PkRecordInfoController {
    @Autowired
    PkRecordInfoService pkRecordInfoService;


    @PostMapping("/user/pkrecord/pkrecordinfo/")
    private List<Pkrecord> getpkrecordinfo(@RequestParam Map<String,String> data)
    {
        Integer userId=Integer.parseInt(data.get("userid")) ;

        return pkRecordInfoService.PkRecordInfo(userId);
    }


}
