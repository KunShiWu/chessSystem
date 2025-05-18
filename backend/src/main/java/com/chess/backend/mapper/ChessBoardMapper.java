package com.chess.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chess.backend.pojo.Chessboard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChessBoardMapper extends BaseMapper<Chessboard> {
}
