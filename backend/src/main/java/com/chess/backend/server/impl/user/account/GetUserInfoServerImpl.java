package com.chess.backend.server.impl.user.account;

import com.chess.backend.mapper.UserMapper;
import com.chess.backend.pojo.User;
import com.chess.backend.server.impl.utils.UserDetailsImpl;
import com.chess.backend.server.user.account.GetUserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetUserInfoServerImpl implements GetUserInfoServer {

    @Override
    public Map<String, String> getUserInfo() {
        UsernamePasswordAuthenticationToken authenticationToken=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();

        User user=loginUser.getUser();

        Map<String,String> map=new HashMap<>();

        map.put("event","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("photo",user.getPhoto());

        return   map;
    }
}
