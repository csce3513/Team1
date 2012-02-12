package battlechess;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BattleChess extends JFrame {
    
    private JPanel content = null;
    private JPanel panel = null;
    private JLabel cells[] = new JLabel[64];
    
    public BattleChess() {
        super();
        initialize();
    }
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BattleChess().setVisible(true);
            }
        });
    }
    private void initialize() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setTitle("Battle Chess");
        this.setContentPane(getContentPanel());
        this.setLayout(null);
        
        JLabel titleScreen = new JLabel(new ImageIcon("./images/titlescreen.jpg"));
        JButton registerButton = new JButton("Register"); //new ImageIcon("./images/register.gif")
        JButton loginButton = new JButton("Login"); //new ImageIcon("./images/login.gif")
        JButton skipButton = new JButton("Skip registration/login");
        
        titleScreen.setOpaque(true);
        this.add(titleScreen,-1);
        titleScreen.setBounds(0,0,800,600);
        
        registerButton.setBounds(180,350,200,25);
        this.add(registerButton,1);
        
        loginButton.setBounds(420,350,200,25);
        this.add(loginButton,1);
        
        skipButton.setBounds(300,400,200,25);
        this.add(skipButton,1);
    }

    private JPanel getContentPanel() {
        if (content == null) {
            content = new JPanel();
            content.setLayout(new BorderLayout());
            content.add(getPanel(), BorderLayout.CENTER);
        }
        return content;
    }
    private JPanel getPanel() {
        if (panel == null) {
            GridLayout grid = new GridLayout();
            grid.setRows(8);
            grid.setHgap(3);
            grid.setVgap(3);
            grid.setColumns(8);
            panel = new JPanel();
            panel.setLayout(grid);
        }
        return panel;
    }
    private void createBoard() {
        int rowColor = 0;
        int i = 0;
        for (int x=0; x<=7; x++) {
            rowColor++;
            for (int y=0; y<=7; y++) {
                cells[i] = new JLabel("", JLabel.CENTER);
                cells[i].setOpaque(true);
                if (rowColor % 2 == 0) cells[i].setBackground(Color.DARK_GRAY);
                else cells[i].setBackground(Color.WHITE);
                panel.add(cells[i]);
                rowColor++;
                i++;
            }
        }
    }
}
