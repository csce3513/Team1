package battlechess;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BattleChess extends JFrame {
    
    private JPanel content = new JPanel();;
    private JPanel title = new JPanel();;
    private JPanel board = new JPanel();;
    private JLabel titleScreen = new JLabel(new ImageIcon("./images/titlescreen.jpg"));
    private JLabel cells[] = new JLabel[64];
    private JButton registerButton = new JButton("Register"); //new ImageIcon("./images/register.gif")
    private JButton loginButton = new JButton("Login"); //new ImageIcon("./images/login.gif")
    private JButton skipButton = new JButton("Skip registration/login");
        
    public BattleChess() {
        super();
        initialize();
        
        // Show titlescreen
        title.setVisible(true);
        
        // these don't work, look into springlayout and cardlayout
        //content.repaint();
        //content.updateUI();
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
        content.setLayout(new BorderLayout());
        this.setContentPane(content);
        createTitle();
        createBoard();
    }
    private void createTitle() {
        title.setLayout(null);
        
        titleScreen.setOpaque(true);
        title.add(titleScreen,-1);
        titleScreen.setBounds(0,0,800,600);
        
        registerButton.setBounds(180,350,200,25);
        title.add(registerButton,0);
        
        loginButton.setBounds(420,350,200,25);
        title.add(loginButton,0);
        
        skipButton.setBounds(300,400,200,25);
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                title.setVisible(false);
                board.setVisible(true);
            }
        });
        title.add(skipButton,0);
        
        content.add(title);
        title.setVisible(false);
    }
    private void createBoard() {
        GridLayout grid = new GridLayout();
        grid.setRows(8);
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setColumns(8);
        board.setLayout(grid);
        
        int rowColor = 0;
        int i = 0;
        for (int x=0; x<=7; x++) {
            rowColor++;
            for (int y=0; y<=7; y++) {
                cells[i] = new JLabel("", JLabel.CENTER);
                cells[i].setOpaque(true);
                if (rowColor % 2 == 0) cells[i].setBackground(Color.DARK_GRAY);
                else cells[i].setBackground(Color.WHITE);
                board.add(cells[i]);
                rowColor++;
                i++;
            }
            skipButton.setText(String.valueOf(i));
        }
        content.add(board);
        board.setVisible(false);
    }
}
