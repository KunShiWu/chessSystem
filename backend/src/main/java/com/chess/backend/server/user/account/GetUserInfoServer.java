package com.chess.backend.server.user.account;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetUserInfoServer {
    Map<String,String> getUserInfo();
}
