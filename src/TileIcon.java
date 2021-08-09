import javax.swing.*;
import java.awt.*;

public class TileIcon {
    private static final int ICON_SIZE =GamePanel.BOARD_SIZE/GamePanel.GAME_SIZE;

    public static final ImageIcon DEFAULT_TILE = new ImageIcon(new ImageIcon("facingDown.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon FLAGGED_TILE= new ImageIcon(new ImageIcon("flagged.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_0 = new ImageIcon(new ImageIcon("0.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_1 = new ImageIcon(new ImageIcon("1.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_2 = new ImageIcon(new ImageIcon("2.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_3 = new ImageIcon(new ImageIcon("3.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_4 = new ImageIcon(new ImageIcon("4.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_5 = new ImageIcon(new ImageIcon("5.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_6 = new ImageIcon(new ImageIcon("6.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_7 = new ImageIcon(new ImageIcon("7.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon ICON_8 = new ImageIcon(new ImageIcon("8.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
    public static final ImageIcon BOMB = new ImageIcon(new ImageIcon("bomb.png")
            .getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));

}
