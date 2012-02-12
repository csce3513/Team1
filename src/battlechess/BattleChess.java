package battlechess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class BattleChess {
    JPanel pages;
     
    public void addOptions(Container pane) {
        // Create the title screen
        JPanel title = new JPanel();
        title.setLayout(null);

        JLabel titleScreen = new JLabel(new ImageIcon("./images/titlescreen.jpg"));
        titleScreen.setOpaque(true);
        title.add(titleScreen,-1);
        titleScreen.setBounds(0,0,800,600);

        JButton registerButton = new JButton("Register"); //new ImageIcon("./images/register.gif")
        registerButton.setBounds(180,350,200,25);
        title.add(registerButton,0);
        
        JButton loginButton = new JButton("Login"); //new ImageIcon("./images/login.gif")
        loginButton.setBounds(420,350,200,25);
        title.add(loginButton,0);

        JButton skipButton = new JButton("Skip registration/login");
        skipButton.setBounds(300,400,200,25);
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                CardLayout cl = (CardLayout)(pages.getLayout());
                cl.show(pages, "chessboard");
            }
        });
        title.add(skipButton,0);

        // Create the board
        JPanel board = new JPanel();
        JLabel cells[] = new JLabel[64];
        GridLayout grid = new GridLayout();
        grid.setRows(8);
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setColumns(8);
        board.setLayout(grid);
        board.setSize(640,640);
        
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
        }
        
        // Create the panel that contains the components
        pages = new JPanel(new CardLayout());
        pages.add(title, "titlescreen");
        pages.add(board, "chessboard");
         
        pane.add(pages, BorderLayout.CENTER);
    }
     
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("BattleChess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        // Create and set up the content pane.
        BattleChess game = new BattleChess();
        game.addOptions(frame.getContentPane());

        //Display the window.
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}