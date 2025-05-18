package com.chess.backend.Controller.user.account;

import com.chess.backend.server.user.account.RegisterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    RegisterServer registerServer;


    @PostMapping("/user/account/register/")
    private Map<String,String> register(@RequestParam Map<String,String> data)
    {
        String username=data.get("username");
        String password=data.get("password");
        String confirmPassword=data.get("confirmPassword");
        return registerServer.register(username,password,confirmPassword);

    }


}
