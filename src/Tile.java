import javax.swing.*;

public class Tile extends JLabel {
    boolean isAbomb=false;
    int bombsAround=0;

    Tile(){
        setOpaque(true);
        setIcon(TileIcon.DEFAULT_TILE);
    }
}
