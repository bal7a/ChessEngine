
package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage;

import java.util.List;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessboard;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private final static  String defaultPieceImagesPath = "art/fancy" ;
    private final Color lightTileColor =Color.decode("#FFFACD");
    private final Color darkTileColor =Color.decode("#593E1A");
    public Table () {
         this.gameFrame = new JFrame("JChess");
         this.gameFrame.setLayout(new BorderLayout());
         final JMenuBar tableMenuBar = createTableMenueBar();
         this.gameFrame.setJMenuBar(tableMenuBar);
         this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
         this.chessboard=Board.createStandardBoard();
         this.boardPanel=new BoardPanel();
         this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
         this.gameFrame.setVisible(true);

     }

    private JMenuBar createTableMenueBar() {
         final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu filesMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("load PGN file");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             System.out.println("open up that pgn file ");
            }
        });
        filesMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        filesMenu.add(exitMenuItem);



        return filesMenu;
    }

    private class BoardPanel extends JPanel {
         final List<TilePanel> boardTiles;
         BoardPanel() {
             super(new GridLayout(8,8));
             this.boardTiles = new ArrayList<>();
             for(int i=0; i< BoardUtils.NUM_TILES; i++){
                 final TilePanel tilePanel= new TilePanel(this,i);
                 this.boardTiles.add(tilePanel);
                 add(tilePanel);
             }
             setPreferredSize(BOARD_PANEL_DIMENSION);
             validate();
         }

    }

    private class TilePanel extends JPanel{

         private final int tileId;
         TilePanel(final BoardPanel boardPanel,final int tileId){
             super(new GridBagLayout());
             this.tileId = tileId;
             setPreferredSize(TILE_PANEL_DIMENSION);
             assignTileColor();
             assignTilePieceIcon(chessboard);
             validate();
         }

        private void assignTilePieceIcon(final Board board) {
            this.removeAll();
            if(board.getTile(this.tileId).isTileOccupied()) {

                try{
                    final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath +
                            board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1) + "" +
                            board.getTile(this.tileId).getPiece().toString() +
                            ".gif"));
                    add(new JLabel(new ImageIcon(image)));
                } catch(final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        private void assignTileColor() {
            if (BoardUtils.EIGTH_RANK[this.tileId] ||
                    BoardUtils.SIXTH_RANK[this.tileId] ||
                    BoardUtils.FOURTH_RANK[this.tileId] ||
                    BoardUtils.SECOND_RANK[this.tileId]) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if(BoardUtils.SEVENTH_RANK[this.tileId] ||
                    BoardUtils.FIFTH_RANK[this.tileId] ||
                    BoardUtils.THIRD_RANK[this.tileId]  ||
                    BoardUtils.FIRST_RANK[this.tileId]) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
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

}


