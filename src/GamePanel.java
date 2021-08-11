import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    final static int BOARD_SIZE = 400;
    final static int GAME_SIZE = 10;
    final int BOMB_NUMBER = 10;

    int gameTime=0;
    Timer gameTimer=new Timer(1000,e -> gameTime++);
    MyMouseAdapter myMouseAdapter=new MyMouseAdapter();

    Random randomGenerator = new Random();
    CellCleaner cellCleaner= new CellCleaner();

    Tile[][] tiles = new Tile[GAME_SIZE + 2][GAME_SIZE + 2];// +2 for countBombsAround;

    GamePanel() {
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        setLayout(new GridLayout(GAME_SIZE, GAME_SIZE, 2, 2));
        makeBoard();
        setBombs();
        GameFrame.resetButton.setIcon(new ImageIcon("GameDef.png"));
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
                tiles[i][j].addMouseListener(myMouseAdapter);
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
    void gameLost(){
        gameTimer.stop();
        for (int i = 1; i < GAME_SIZE + 1; i++) {
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                tiles[i][j].removeMouseListener(myMouseAdapter);
            }
        }
        GameFrame.resetButton.setIcon(new ImageIcon("GameLost.png"));
    }
    void gameWon(){
        gameTimer.stop();
        for (int i = 1; i < GAME_SIZE + 1; i++) {
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                tiles[i][j].removeMouseListener(myMouseAdapter);
                if (tiles[i][j].isAbomb)tiles[i][j].setIcon(TileIcons.BOMB);
            }
        }
        GameFrame.resetButton.setIcon(new ImageIcon("GameWon.png"));
    }

    class MyMouseAdapter extends MouseAdapter {
        Tile tile;
        Object o;

        @Override
        public void mousePressed(MouseEvent e) {
            o = e.getSource();
            tile = (Tile) o;

            gameTimer.start();

            if ((e.getButton() == MouseEvent.BUTTON1) && (!tile.getIcon().equals(TileIcons.FLAGGED_TILE))) {
                if (tile.isAbomb) {
                    tile.setBackground(Color.red);
                    tile.setIcon(TileIcons.BOMB);
                    gameLost();
                }
                else if (tile.bombsAround==0)tile.setIcon(TileIcons.ICON_0);
                else if (tile.bombsAround==1)tile.setIcon(TileIcons.ICON_1);
                else if (tile.bombsAround==2)tile.setIcon(TileIcons.ICON_2);
                else if (tile.bombsAround==3)tile.setIcon(TileIcons.ICON_3);
                else if (tile.bombsAround==4)tile.setIcon(TileIcons.ICON_4);
                else if (tile.bombsAround==5)tile.setIcon(TileIcons.ICON_5);
                else if (tile.bombsAround==6)tile.setIcon(TileIcons.ICON_6);
                else if (tile.bombsAround==7)tile.setIcon(TileIcons.ICON_7);
                else if (tile.bombsAround==8)tile.setIcon(TileIcons.ICON_8);

            }
            if (e.getButton() == MouseEvent.BUTTON3){
                if(((tile.getIcon().equals(TileIcons.DEFAULT_TILE)))
                        &&(!GameFrame.unflaggedBombs.getText().equals("0"))){
                    tile.setIcon(TileIcons.FLAGGED_TILE);
                }else tile.setIcon(TileIcons.DEFAULT_TILE);
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
                countFlaggedTiles();
                countTilesLeft();
                GameFrame.timerDisplay.setText(String.valueOf(gameTime));
            }
        }
        void showCellsAround() {
            for (int i = 1; i < GAME_SIZE + 1; i++) {
                for (int j = 1; j < GAME_SIZE + 1; j++) {
                    if (tiles[i][j].getIcon().equals(TileIcons.ICON_0)) {
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
        void countFlaggedTiles(){
            int flaggedTiles=0;
            for (int i = 1; i < GAME_SIZE + 1; i++) {
                for (int j = 1; j < GAME_SIZE + 1; j++) {
                    if ((tiles[i][j].getIcon().equals(TileIcons.FLAGGED_TILE))
                        && (flaggedTiles<BOMB_NUMBER)){
                        flaggedTiles++;
                    }
                }
            }
            GameFrame.unflaggedBombs.setText(Integer.toString(BOMB_NUMBER-flaggedTiles));
        }
        ImageIcon chosedIcon(Tile tile){
            ImageIcon tileIcon = new ImageIcon();
            if (tile.bombsAround==0)tileIcon= TileIcons.ICON_0;
            if (tile.bombsAround==1)tileIcon= TileIcons.ICON_1;
            if (tile.bombsAround==2)tileIcon= TileIcons.ICON_2;
            if (tile.bombsAround==3)tileIcon= TileIcons.ICON_3;
            if (tile.bombsAround==4)tileIcon= TileIcons.ICON_4;
            if (tile.bombsAround==5)tileIcon= TileIcons.ICON_5;
            if (tile.bombsAround==6)tileIcon= TileIcons.ICON_6;
            if (tile.bombsAround==7)tileIcon= TileIcons.ICON_7;
            if (tile.bombsAround==8)tileIcon= TileIcons.ICON_8;

            return tileIcon;
        }
        void countTilesLeft(){
            int defaultTiles=0;
            for (int i = 1; i < GAME_SIZE + 1; i++) {
                for (int j = 1; j < GAME_SIZE + 1; j++) {
                    if(tiles[i][j].getIcon().equals(TileIcons.DEFAULT_TILE)){
                        defaultTiles++;
                    }
                }
            }
            if (defaultTiles==BOMB_NUMBER) gameWon();
        }
    }
}

