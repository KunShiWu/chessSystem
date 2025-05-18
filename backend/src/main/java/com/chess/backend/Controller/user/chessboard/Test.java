package com.chess.backend.Controller.user.chessboard;

import com.chess.backend.pojo.Chessboard;
import com.chess.backend.server.user.chessboard.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;


@RestController
public class Test {

    @Autowired
    ChessBoardService chessBoardService;

    @PostMapping("/user/chessboard/test/")
    private List<Chessboard> test(@RequestParam Map<String,String> data){
        String r=data.get("row");
        String c=data.get("col");
        String chess=data.get("chess");

        return chessBoardService.ChanceChessBoard(r,c,chess);


    }
}
