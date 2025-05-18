package com.chess.backend.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chess.backend.mapper.UserMapper;
import com.chess.backend.pojo.User;
import com.chess.backend.server.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//获得了输入的username传到userdetails类中去
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username);
        User user=userMapper.selectOne(queryWrapper);


        System.out.println(user);
        if(user==null)
        {
            throw new  RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
