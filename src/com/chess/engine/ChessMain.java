package com.chess.engine;

import com.chess.engine.board.Board;
import com.chess.gui.Table;

public class ChessMain {
    public static void main(String[] args){
        Board board = Board.createStandardBoard();
        System.out.println(board);
        new Table();

    }
}
