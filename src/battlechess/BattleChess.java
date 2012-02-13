package battlechess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class BattleChess {
    static int[] window = {816,638};
    int[] screen = {800,600};
    JPanel pages;
     
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
    }

    private static void initGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("BattleChess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(window[0],window[1]);

        // Create and set up the content pane.
        BattleChess game = new BattleChess();
        game.addOptions(frame.getContentPane());

        //Display the window.
        frame.setVisible(true);
    }

    public void addOptions(Container pane) {
        // Create the title screen
        JPanel title = new JPanel();
        title.setLayout(null);

        JLabel titleScreen = new JLabel(new ImageIcon("./images/titlescreen.jpg"));
        titleScreen.setOpaque(true);
        title.add(titleScreen,-1);
        titleScreen.setBounds(0,0,screen[0],screen[1]);

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
        board.setLayout(null);

        JLabel boardScreen = new JLabel(new ImageIcon("./images/boardscreen.jpg"));
        boardScreen.setOpaque(true);
        board.add(boardScreen,-1);
        boardScreen.setBounds(0,0,screen[0],screen[1]);
        
        JPanel chessboard = new JPanel();
        JLabel square[] = new JLabel[64];
        GridLayout grid = new GridLayout();
        grid.setRows(8);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setColumns(8);
        chessboard.setOpaque(false);
        chessboard.setLayout(grid);
        chessboard.setBounds(150,50,498,498);
        board.add(chessboard,0);
        
        int rowColor = 0;
        int i = 0;
        for (int x=0; x<=7; x++) {
            rowColor++;
            for (int y=0; y<=7; y++) {
                square[i] = new JLabel("", JLabel.CENTER);
                square[i].setOpaque(false); // add the cells but make them transparent
                chessboard.add(square[i]);
                rowColor++;
                i++;
            }
        }
        
        // Create the panel that contains the components
        pages = new JPanel(new CardLayout());
        pages.add(title, "titlescreen");
        pages.add(board, "chessboard");
        // TODO: add the high score panel
        // TODO: add the registration panel
        // TODO: add the login panel
        // TODO: add the credits panel
         
        pane.add(pages, BorderLayout.CENTER);
    }
}