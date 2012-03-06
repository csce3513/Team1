package BattleChess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Game extends JPanel implements Constants, ActionListener {

    Game() {
        super(new CardLayout());
        add(createTitle(), backButton);
        add(createRegistration(), registerButton);
        add(createLogin(), loginButton);
        add(createBoard(), skipButton);
        add(createCredits(), creditsButton);
        add(createRankings(), rankButton);
    }

    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, ((JButton) e.getSource()).getText());
    }

    private JPanel createTitle() {
        // Create the title screenSiz
        JPanel panel = getPanel(imageFolder + titleBG, false);
        panel.add(getButton(registerButton, 180, 350, 200, 20), 0);
        panel.add(getButton(loginButton, 420, 350, 200, 20), 0);
        panel.add(getButton(skipButton, 300, 400, 200, 20), 0);
        panel.add(getButton(creditsButton, 5, 575, 100, 20), 0);
        panel.add(getButton(rankButton, 695, 575, 100, 20), 0);
        return panel;
    }

    private JPanel createRegistration() {
        // Create the registration screen
        JPanel panel = getPanel(imageFolder + registrationBG);
        return panel;
    }

    private JPanel createLogin() {
        // Create the login screen
        JPanel panel = getPanel(imageFolder + loginBG);
        return panel;
    }

    private JPanel createBoard() {
        // Create the board
        JPanel panel = getPanel(imageFolder + boardBG);

        JPanel board = new JPanel();
        JLabel square[] = new JLabel[64];
        GridLayout grid = new GridLayout();
        grid.setRows(8);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setColumns(8);
        board.setOpaque(false);
        board.setLayout(grid);
        board.setBounds(150, 50, 498, 498);
        panel.add(board, 0);

        // initialize pieces
        pieces[0][0] = "black_rook";
        pieces[0][1] = "black_knight";
        pieces[0][2] = "black_bishop";
        pieces[0][3] = "black_king";
        pieces[0][4] = "black_queen";
        pieces[0][5] = "black_bishop";
        pieces[0][6] = "black_knight";
        pieces[0][7] = "black_rook";
        pieces[1][0] = "black_pawn";
        pieces[1][1] = "black_pawn";
        pieces[1][2] = "black_pawn";
        pieces[1][3] = "black_pawn";
        pieces[1][4] = "black_pawn";
        pieces[1][5] = "black_pawn";
        pieces[1][6] = "black_pawn";
        pieces[1][7] = "black_pawn";
        pieces[6][0] = "white_pawn";
        pieces[6][1] = "white_pawn";
        pieces[6][2] = "white_pawn";
        pieces[6][3] = "white_pawn";
        pieces[6][4] = "white_pawn";
        pieces[6][5] = "white_pawn";
        pieces[6][6] = "white_pawn";
        pieces[6][7] = "white_pawn";
        pieces[7][0] = "white_rook";
        pieces[7][1] = "white_knight";
        pieces[7][2] = "white_bishop";
        pieces[7][3] = "white_king";
        pieces[7][4] = "white_queen";
        pieces[7][5] = "white_bishop";
        pieces[7][6] = "white_knight";
        pieces[7][7] = "white_rook";
        
        int i = 0;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                final int pi = i;
                final int px = x; 
                final int py = y;
                square[i] = new JLabel("", JLabel.CENTER);
                square[i].setOpaque(false); // add the cells but make them transparent
                square[i].setIcon(getIcon(pieces[x][y]));
                square[i].addMouseListener(new MouseAdapter(){
                    public void mouseEntered(MouseEvent e){
                        // show hover states
                        if(hi > 0){
                            // the piece was picked up
                        }
                        else {
                            // the piece is being hovered
                        }
                        if(pi != hi){
                            // hovering the piece we are holding
                        }
                    }
                });
                square[i].addMouseListener(new MouseAdapter(){
                   public void mouseExited(MouseEvent e){
                       // clear the status here
                       if(pi != hi){
                           // remove the hover effect if we are holding the hovered piece
                       }
                   } 
                });
                square[i].addMouseListener(new MouseAdapter(){
                    public void mouseReleased(MouseEvent e){
                        if(e.getModifiers() == InputEvent.BUTTON1_MASK){
                            // the cell was clicked
                        }
                        if(e.getModifiers() == InputEvent.BUTTON3_MASK){
                            // do something if button 3 clicked the square
                        }
                    }
                });
                board.add(square[i]);
                i++;
            }
        }
        return panel;
    }

    private JPanel createCredits() {
        // Create the credits screen
        JPanel panel = getPanel(imageFolder + creditsBG);
        return panel;
    }

    private JPanel createRankings() {
        // Create the rank screen
        JPanel panel = getPanel(imageFolder + rankBG);
        
        Ranking r = new Ranking();
        panel.add(r.Ranking(2, 4));
        
        //DbAccess dao = new DbAccess() {};
		//try {
        //    dao.readDataBase();
       // }
        //catch(Exception e){
            // oops
        //}
        
        return panel;
    }

    private JPanel getPanel(String bg) {
        return getPanel(bg, true);
    }

    private JPanel getPanel(String bg, Boolean hasBack) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        if (!bg.isEmpty()) {
            panel.add(getBG(bg), -1);
        }
        if (hasBack) {
            panel.add(getBackButton(), 0);
        }
        return panel;
    }

    private JLabel getBG(String imageFile) {
        JLabel bg = new JLabel(new ImageIcon(imageFile));
        bg.setOpaque(true);
        bg.setBounds(0, 0, screenSize[0], screenSize[1]);
        return bg;
    }

    private JButton getButton(String title, int x, int y, int width, int height) {
        JButton button = new JButton(title);
        button.setBounds(x, y, width, height);
        button.addActionListener(this);
        return button;
    }

    private JButton getBackButton() {
        JButton back = new JButton(backButton);
        back.setBounds(5, 5, 50, 20);
        back.addActionListener(this);
        return back;
    }
    
    private ImageIcon getIcon(String name){
        ImageIcon icon = null;
        if(name != null && !name.equals("")){
            icon = new ImageIcon(imageFolder + name + ".gif");
        }
        return icon;
    }
}
