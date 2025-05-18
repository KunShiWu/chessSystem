package com.chess.backend.server.impl.user.pkrecord;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chess.backend.mapper.PkRecordMapper;
import com.chess.backend.pojo.Pkrecord;
import com.chess.backend.server.user.pkrecord.PkRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PkRecordInfoServiceImpl implements PkRecordInfoService {
    @Autowired
    PkRecordMapper pkRecordMapper;
    @Override
    public List<Pkrecord> PkRecordInfo(Integer userId) {
        QueryWrapper<Pkrecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",userId);

        List<Pkrecord> list=pkRecordMapper.selectList(queryWrapper);

        return list;
    }
}
