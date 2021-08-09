import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {
    boolean isAbomb=false;
    int bombsAround=0;

    Tile(){
        setIcon(TileIcon.DEFAULT_TILE);

        setBackground(Color.gray);
        setOpaque(true);
    }
}
