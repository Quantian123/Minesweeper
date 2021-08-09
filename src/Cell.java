import javax.swing.*;
import java.awt.*;
import static java.awt.Image.*;

public class Cell extends JLabel {
    boolean isAbomb=false;
    String bombsAround="";

    Cell(){
        //ImageIcon imageIcon = new ImageIcon("facingDown.png");
        //Image image = imageIcon.getImage();
        //Image newimg = image.getScaledInstance(40,40, SCALE_SMOOTH);
        //imageIcon = new ImageIcon(newimg);
        //setIcon(imageIcon);
        setBackground(Color.gray);
        setOpaque(true);
    }
}
