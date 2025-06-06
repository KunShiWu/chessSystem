package com.chess.backend.server.impl.user.account;

import com.chess.backend.mapper.UserMapper;
import com.chess.backend.pojo.User;
import com.chess.backend.server.impl.utils.UserDetailsImpl;
import com.chess.backend.server.user.account.GetTokenServer;
import com.chess.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenServerImpl implements GetTokenServer {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication=authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();


        User user=loginUser.getUser();


        String jwt= JwtUtil.createJWT(user.getId().toString());

        Map<String,String> map=new HashMap<>();

        map.put("event","success");
        map.put("access_token",jwt);

        return map;
    }
}
