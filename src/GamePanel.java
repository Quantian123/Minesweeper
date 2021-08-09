import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    final static int BOARD_SIZE = 400;
    final static int GAME_SIZE = 10;
    final int BOMB_NUMBER = 10;

    Random randomGenerator = new Random();
    CellCleaner cellCleaner= new CellCleaner();

    Tile[][] tiles = new Tile[GAME_SIZE + 2][GAME_SIZE + 2];// +2 for countBombsAround;

    GamePanel() {
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setLayout(new GridLayout(GAME_SIZE, GAME_SIZE, 2, 2));
        setBackground(Color.lightGray);
        makeBoard();
        setBombs();
        countBombsAround();
       cellCleaner.start();
    }
    void makeBoard(){
        for (int i = 0; i < GAME_SIZE + 2; i++) {            //frame+board
            for (int j = 0; j < GAME_SIZE + 2; j++) {
                    tiles[i][j] = new Tile();
            }
        }
        for (int i = 1; i < GAME_SIZE + 1; i++) {           //board
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                add(tiles[i][j]);
                tiles[i][j].addMouseListener(new MyMouseAdapter());
            }
        }
    }
    public void setBombs() {
        int n=0;
        while(n<BOMB_NUMBER){
            int i=randomGenerator.nextInt(GAME_SIZE)+1;
            int j=randomGenerator.nextInt(GAME_SIZE)+1;
            if (!tiles[i][j].isAbomb){
                tiles[i][j].isAbomb=true;
                n++;
            }
        }
    }
    void countBombsAround () {
        for (int i = 1; i < GAME_SIZE + 1; i++) {
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                int bombsAround = 0;
                if (!tiles[i][j].isAbomb) {
                    if (tiles[i - 1][j].isAbomb) {bombsAround++;}
                    if (tiles[i + 1][j].isAbomb) {bombsAround++;}
                    if (tiles[i][j - 1].isAbomb) {bombsAround++;}
                    if (tiles[i][j + 1].isAbomb) {bombsAround++;}
                    if (tiles[i - 1][j - 1].isAbomb) {bombsAround++;}
                    if (tiles[i - 1][j + 1].isAbomb) {bombsAround++;}
                    if (tiles[i + 1][j + 1].isAbomb) {bombsAround++;}
                    if (tiles[i + 1][j - 1].isAbomb) {bombsAround++;}
                    tiles[i][j].bombsAround = bombsAround;
                }
            }
        }
    }

    class MyMouseAdapter extends MouseAdapter {
        Tile tile;
        Object o;

        @Override
        public void mousePressed(MouseEvent e) {
            o = e.getSource();
            tile = (Tile) o;

            if ((e.getButton() == MouseEvent.BUTTON1) && (!tile.getIcon().equals(TileIcon.FLAGGED_TILE))) {
                if (tile.isAbomb) {
                    tile.setBackground(Color.red);
                    tile.setIcon(TileIcon.BOMB);
                }
                else if (tile.bombsAround==0)tile.setIcon(TileIcon.ICON_0);
                else if (tile.bombsAround==1)tile.setIcon(TileIcon.ICON_1);
                else if (tile.bombsAround==2)tile.setIcon(TileIcon.ICON_2);
                else if (tile.bombsAround==3)tile.setIcon(TileIcon.ICON_3);
                else if (tile.bombsAround==4)tile.setIcon(TileIcon.ICON_4);
                else if (tile.bombsAround==5)tile.setIcon(TileIcon.ICON_5);
                else if (tile.bombsAround==6)tile.setIcon(TileIcon.ICON_6);
                else if (tile.bombsAround==7)tile.setIcon(TileIcon.ICON_7);
                else if (tile.bombsAround==8)tile.setIcon(TileIcon.ICON_8);

            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if(tile.getIcon().equals(TileIcon.DEFAULT_TILE)){
                    tile.setIcon(TileIcon.FLAGGED_TILE);
                }else tile.setIcon(TileIcon.DEFAULT_TILE);
            }
        }
    }

    class CellCleaner extends Thread{
        CellCleaner(){
            setDaemon(true);
        }
        @Override
        public void run() {
            while (true) {
                showCellsAround();
            }
        }
        void showCellsAround() {
            for (int i = 1; i < GAME_SIZE + 1; i++) {
                for (int j = 1; j < GAME_SIZE + 1; j++) {
                    if (tiles[i][j].getIcon().equals(TileIcon.ICON_0)) {
                        tiles[i - 1][j].setIcon(chosedIcon(tiles[i - 1][j]));
                        tiles[i + 1][j].setIcon(chosedIcon(tiles[i + 1][j]));
                        tiles[i][j - 1].setIcon(chosedIcon(tiles[i][j - 1]));
                        tiles[i - 1][j - 1].setIcon(chosedIcon(tiles[i - 1][j - 1]));
                        tiles[i - 1][j + 1].setIcon(chosedIcon(tiles[i - 1][j + 1]));
                        tiles[i + 1][j + 1].setIcon(chosedIcon(tiles[i + 1][j + 1]));
                        tiles[i - 1][j - 1].setIcon(chosedIcon(tiles[i - 1][j - 1]));
                    }
                }
            }
        }
        ImageIcon chosedIcon(Tile tile){
            ImageIcon tileIcon = new ImageIcon();
            if (tile.bombsAround==0)tileIcon=TileIcon.ICON_0;
            if (tile.bombsAround==1)tileIcon=TileIcon.ICON_1;
            if (tile.bombsAround==2)tileIcon=TileIcon.ICON_2;
            if (tile.bombsAround==3)tileIcon=TileIcon.ICON_3;
            if (tile.bombsAround==4)tileIcon=TileIcon.ICON_4;
            if (tile.bombsAround==5)tileIcon=TileIcon.ICON_5;
            if (tile.bombsAround==6)tileIcon=TileIcon.ICON_6;
            if (tile.bombsAround==7)tileIcon=TileIcon.ICON_7;
            if (tile.bombsAround==8)tileIcon=TileIcon.ICON_8;

            return tileIcon;
        }
    }


}

