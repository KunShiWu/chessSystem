package com.chess.backend.consumer;

import com.chess.backend.pojo.Chessboard;
import com.chess.backend.server.user.chessboard.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Game {


    @Autowired
    public void setChessBoardMapper(ChessBoardService chessBoardService){
        WebSocketServer.chessBoardService=chessBoardService;
    }


    public List<Chessboard> Init_chessboard(){
        for(int i=0;i<15;i++)
        {
            for (int j=0;j<15;j++)
            {
                String a= Integer.toString(i);
                String b=Integer.toString(j);
                WebSocketServer.chessBoardService.ChanceChessBoard(a,b,"0");
            }

        }
        return   WebSocketServer.chessBoardService.ChanceChessBoard("0","0","0");
    }

    public List<Chessboard> Move_chess(String r,String c,String chesscolor){
        String a=r;
        String b=c;
        if("BlackChess".equals(chesscolor))
        {
        return   WebSocketServer.chessBoardService.ChanceChessBoard(a,b,"1");
        }
        else if ("WhiteChess".equals(chesscolor))
        {
        return   WebSocketServer.chessBoardService.ChanceChessBoard(a,b,"2");
        }
        return WebSocketServer.chessBoardService.ChanceChessBoard("0","0","0");
    }

}
