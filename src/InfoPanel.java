import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class InfoPanel extends JPanel {
    JLabel gameTime=new JLabel("YOUR TIME");
    JLabel resetLabel=new JLabel("RESET");
    JLabel minesLabel=new JLabel("!BOMBS!");
    Border border=BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
            BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    InfoPanel(){
        setLayout(null);
        setPreferredSize(new Dimension(400,25));

        gameTime.setBounds(0,0,150,25);
        gameTime.setOpaque(true);
        gameTime.setFont(new Font("Arial",Font.BOLD,15));
        gameTime.setBorder(border);
        gameTime.setHorizontalAlignment(SwingConstants.CENTER);

        resetLabel.setBounds(150,0,100,25);
        resetLabel.setOpaque(true);
        resetLabel.setFont(new Font("Arial",Font.BOLD,15));
        resetLabel.setBorder(border);
        resetLabel.setHorizontalAlignment(SwingConstants.CENTER);

        minesLabel.setBounds(250,0,150,25);
        minesLabel.setOpaque(true);
        minesLabel.setFont(new Font("Arial",Font.BOLD,15));
        minesLabel.setBorder(border);
        minesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(gameTime);
        add(resetLabel);
        add(minesLabel);
    }
}
