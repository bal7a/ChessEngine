package com.chess.engine;

import com.chess.engine.board.Board;
import com.chess.gui.Table;

import java.io.File;

public class ChessMain {
    public static void main(String[] args){
        Board board = Board.createStandardBoard();
        System.out.println(board);
        new Table();
        File file = new File("art/fancy/BB.gif");// write here your file path //
        if (file.exists()) {
            System.out.println(file.getPath()+(" exists"));
        }
        else
            System.out.println(file.getPath()+(" Not exists"));
    }
}

