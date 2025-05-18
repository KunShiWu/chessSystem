package com.chess.backend.server.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chess.backend.mapper.UserMapper;
import com.chess.backend.pojo.User;
import com.chess.backend.server.user.account.RegisterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterServer {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> register(String username, String password, String confirmPassword) {
       Map<String,String> map=new HashMap<>();

        if(username==null||username.length()==0)
        {
            map.put("event","用户名不能为空");
            return   map;
        }

        if(username.length()>100)
        {
            map.put("event","用户名不能设置过长");
            return  map;
        }

        if(password==null||password.length()==0)
        {
            map.put("event","密码不能为空");
            return map;
        }
        if(password.length()>100||confirmPassword.length()>100)
        {
            map.put("event","密码不能太长");
            return map;
        }

        if(!confirmPassword.equals(password))
        {
            map.put("event","两次密码不一致");
            return  map;
        }

        QueryWrapper<User>  queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("username",username);

        List<User> users=userMapper.selectList(queryWrapper);

        if(!users.isEmpty())
        {
            map.put("event","用户名已存在");
            return map;
        }

        String encodePassword=passwordEncoder.encode(password);

        String photo="https://img.wxcha.com/m00/4e/6f/909eba73f95d3f3e83038515c49a0c2a.jpg";

        User user=new User(null,username,encodePassword,photo);

        userMapper.insert(user);
        map.put("event","success");

        return map;
    }
}
