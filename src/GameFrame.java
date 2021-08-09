import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    JLabel results = new JLabel();
    JLabel timer=new JLabel();
    JButton reset = new JButton("Reset");
    JPanel display=new JPanel();
    GamePanel gamePanel;
    public static void main(String[] args) {
        new GameFrame();
    }
    GameFrame(){
        setTitle("MINE SWEEPER");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timer.setBounds(0,0,150,100);
        timer.setOpaque(true);
        timer.setBackground(Color.black);
        timer.setForeground(Color.green);
        timer.setFont(new Font("Digital-7" ,Font.ITALIC,120));
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setVerticalAlignment(SwingConstants.TOP);
        timer.setText("23");

        reset.setBounds(150,0,100,100);
        reset.setFocusable(false);
        reset.addActionListener(this);

        results.setBounds(250,0,150,100);
        results.setOpaque(true);
        results.setBackground(Color.black);
        results.setForeground(Color.red);
        results.setFont(new Font("Digital-7" ,Font.ITALIC,120));
        results.setHorizontalAlignment(SwingConstants.CENTER);
        results.setVerticalAlignment(SwingConstants.TOP);
        results.setText("0");

        display.setPreferredSize(new Dimension(400,100));
        display.setLayout(null);
        display.add(timer);
        display.add(reset);
        display.add(results);
        add(display,BorderLayout.NORTH);

        add(gamePanel=new GamePanel(), BorderLayout.CENTER);

        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==reset) {
            gamePanel.cellCleaner.stop();
            remove(gamePanel);
            gamePanel = new GamePanel();
            add(gamePanel,BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}
