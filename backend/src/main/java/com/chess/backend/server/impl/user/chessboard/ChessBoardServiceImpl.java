package com.chess.backend.server.impl.user.chessboard;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chess.backend.mapper.ChessBoardMapper;
import com.chess.backend.pojo.Chessboard;
import com.chess.backend.server.user.chessboard.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {

    @Autowired
    ChessBoardMapper chessBoardMapper;



    @Override
    public List<Chessboard> ChanceChessBoard(String row, String col, String chess) {
        Integer r=Integer.parseInt(row);
        Integer c=Integer.parseInt(col);

        QueryWrapper<Chessboard> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",r);//SELECT * FROM table_name WHERE id = r;




        Chessboard chessBoard=chessBoardMapper.selectOne(queryWrapper);

        String s=chessBoard.getGamemap();
        String new_s=s.substring(0,c)+chess+s.substring(c+1);

        UpdateWrapper<Chessboard> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",r).set("gamemap",new_s);
        //UPDATE chessboard SET gamemap = new_s WHERE id = r;

        chessBoardMapper.update(null,updateWrapper);


        List<Chessboard> list=chessBoardMapper.selectList(null);

        return list;
    }
}
