import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    static JLabel unflaggedBombs = new JLabel();
    static JLabel timerDisplay =new JLabel();
    JButton resetButton = new JButton("Reset");
    JPanel display =new JPanel();
    GamePanel gamePanel;

    public static void main(String[] args) {
        new GameFrame();
    }
    GameFrame(){
        setTitle("MINE SWEEPER");
        setIconImage(new ImageIcon("bomb.png").getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timerDisplay.setBounds(0,0,150,100);
        timerDisplay.setOpaque(true);
        timerDisplay.setBackground(Color.black);
        timerDisplay.setForeground(Color.green);
        timerDisplay.setFont(new Font("Digital-7" ,Font.ITALIC,120));
        timerDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        timerDisplay.setVerticalAlignment(SwingConstants.TOP);

        resetButton.setBounds(150,0,100,100);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        unflaggedBombs.setBounds(250,0,150,100);
        unflaggedBombs.setOpaque(true);
        unflaggedBombs.setBackground(Color.black);
        unflaggedBombs.setForeground(Color.red);
        unflaggedBombs.setFont(new Font("Digital-7" ,Font.ITALIC,120));
        unflaggedBombs.setHorizontalAlignment(SwingConstants.CENTER);
        unflaggedBombs.setVerticalAlignment(SwingConstants.TOP);

        display.setPreferredSize(new Dimension(400,100));
        display.setLayout(null);
        display.add(timerDisplay);
        display.add(resetButton);
        display.add(unflaggedBombs);
        add(display,BorderLayout.NORTH);

        add(gamePanel=new GamePanel(), BorderLayout.CENTER);

        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== resetButton) {
            gamePanel.cellCleaner.stop();
            remove(gamePanel);
            gamePanel = new GamePanel();
            add(gamePanel,BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}
