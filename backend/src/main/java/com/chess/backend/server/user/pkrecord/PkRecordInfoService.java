package com.chess.backend.server.user.pkrecord;

import com.chess.backend.pojo.Pkrecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PkRecordInfoService {
    List<Pkrecord> PkRecordInfo(Integer userId);
}
