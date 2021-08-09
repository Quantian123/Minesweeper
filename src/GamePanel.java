import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    final int BOARD_SIZE = 400;
    final int GAME_SIZE = 10;
    final int BOMB_NUMBER = 10;

    Random randomGenerator = new Random();
    CellCleaner cellCleaner= new CellCleaner();

    Cell[][] cells = new Cell[GAME_SIZE + 2][GAME_SIZE + 2];// +2 for countBombsAround;

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
                    cells[i][j] = new Cell();
            }
        }
        for (int i = 1; i < GAME_SIZE + 1; i++) {           //board
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                add(cells[i][j]);
                cells[i][j].addMouseListener(new MyMouseAdapter());
            }
        }
    }
    public void setBombs() {
        int n=0;
        while(n<BOMB_NUMBER){
            int i=randomGenerator.nextInt(GAME_SIZE)+1;
            int j=randomGenerator.nextInt(GAME_SIZE)+1;
            if (!cells[i][j].isAbomb){
                cells[i][j].isAbomb=true;
                n++;
            }
        }
    }
    void countBombsAround () {
        for (int i = 1; i < GAME_SIZE + 1; i++) {
            for (int j = 1; j < GAME_SIZE + 1; j++) {
                int bombsAround = 0;
                if (!cells[i][j].isAbomb) {
                    if (cells[i - 1][j].isAbomb) {bombsAround++;}
                    if (cells[i + 1][j].isAbomb) {bombsAround++;}
                    if (cells[i][j - 1].isAbomb) {bombsAround++;}
                    if (cells[i][j + 1].isAbomb) {bombsAround++;}
                    if (cells[i - 1][j - 1].isAbomb) {bombsAround++;}
                    if (cells[i - 1][j + 1].isAbomb) {bombsAround++;}
                    if (cells[i + 1][j + 1].isAbomb) {bombsAround++;}
                    if (cells[i + 1][j - 1].isAbomb) {bombsAround++;}
                    if (bombsAround==0){
                        cells[i][j].bombsAround="";
                    }else
                    cells[i][j].bombsAround = Integer.toString(bombsAround);
                }
            }
        }
    }

    class MyMouseAdapter extends MouseAdapter {
        Cell cell;
        Object o;
        @Override
        public void mousePressed(MouseEvent e) {
            o = e.getSource();
            cell = (Cell) o;

            if ((e.getButton() == MouseEvent.BUTTON1) && (!cell.getText().equals("BOMB"))) {
                if (cell.isAbomb) {
                    cell.setBackground(Color.red);
                    cell.setText("BOOM");
                } else{
                    cell.setBackground(Color.pink);
                    cell.setText(String.valueOf(cell.bombsAround));
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (cell.getText().equals("BOMB")) {
                    cell.setText("");
                } else cell.setText("BOMB");
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
                    if ((cells[i][j].bombsAround.equals(""))&&(cells[i][j].getBackground().equals(Color.pink))) {

                        cells[i - 1][j].setBackground(Color.pink);
                        cells[i + 1][j].setBackground(Color.pink);
                        cells[i][j - 1].setBackground(Color.pink);
                        cells[i][j + 1].setBackground(Color.pink);
                        cells[i - 1][j - 1].setBackground(Color.pink);
                        cells[i - 1][j + 1].setBackground(Color.pink);
                        cells[i + 1][j + 1].setBackground(Color.pink);
                        cells[i + 1][j - 1].setBackground(Color.pink);

                        cells[i - 1][j].setText(cells[i - 1][j].bombsAround);
                        cells[i + 1][j].setText(cells[i + 1][j].bombsAround);
                        cells[i][j - 1].setText(cells[i][j - 1].bombsAround);
                        cells[i][j + 1].setText(cells[i][j + 1].bombsAround);
                        cells[i - 1][j - 1].setText(cells[i - 1][j - 1].bombsAround);
                        cells[i - 1][j + 1].setText(cells[i - 1][j + 1].bombsAround);
                        cells[i + 1][j + 1].setText(cells[i + 1][j + 1].bombsAround);
                        cells[i + 1][j - 1].setText(cells[i + 1][j - 1].bombsAround);
                    }
                }
            }
        }
    }
}

