package com.chess.backend.Controller.user.account;

import com.chess.backend.server.user.account.GetUserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetUserInfoController {

    @Autowired
    GetUserInfoServer getUserInfoServer;

    @GetMapping("/user/info/")
    private Map<String,String> getUserInfo()
    {
        return  getUserInfoServer.getUserInfo();
    }

}
