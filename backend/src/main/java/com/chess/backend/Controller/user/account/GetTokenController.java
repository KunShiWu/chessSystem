package com.chess.backend.Controller.user.account;

import com.chess.backend.server.user.account.GetTokenServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetTokenController {

    @Autowired
    GetTokenServer getTokenServer;

    @PostMapping("/user/account/token/")
    private Map<String,String> getToken(@RequestParam Map<String,String> data)
    {
        String username=data.get("username");
        String password=data.get("password");

        return getTokenServer.getToken(username,password);
    }

}
