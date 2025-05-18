package com.chess.backend.server.user.account;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GetTokenServer {
    Map<String,String> getToken(String username,String password);
}
