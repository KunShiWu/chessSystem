package com.chess.backend.server.user.chessboard;

import com.chess.backend.pojo.Chessboard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChessBoardService {
    List<Chessboard> ChanceChessBoard(String row, String col, String chess);
}
