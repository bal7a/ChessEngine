
package com.chess;

import javax.swing.*;
import java.awt.*;

public class Table {

    private final JFrame gameFrame;
    private static  Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);




     public Table () {
         this.gameFrame = new JFrame("Jchess");

         this.gameFrame.setSize(OUTER_FRAME_DIMENSION);

         this.gameFrame.setVisible(true);

     }




}

public static class MoveLog {


    private final List<Move> moves;

    MoveLog(){

        this.moves = new ArrayList<>();
    }
    public  List<Move> getMoves(){
        return this.moves;
    }
    public void addMove(final Move move){
        this.moves.add(move);
    }
    public int size(){
        return this.moves.size();

    }
    public void clear(){
        this.moves.clear();

    }
    public boolean removeMove(final Move move){
        return  this.moves.remove(move);

    }
    public Move removeMove(int index){
        return this.moves.remove(index);
    }
}
