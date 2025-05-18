package com.chess.backend.server.user.account;

import java.util.Map;

public interface RegisterServer {
    Map<String,String> register(String username,String password,String confirmPassword);
}
