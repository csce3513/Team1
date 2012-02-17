package BattleChess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Game extends JPanel implements Constants, ActionListener {
    
    Game(){
        super(new CardLayout());
        add(createTitle(), backButton);
        add(createRegistration(), registerButton);
        add(createLogin(), loginButton);
        add(createBoard(), skipButton);
        add(createCredits(), creditsButton);
        add(createRankings(), rankButton);
    }
    
    public void actionPerformed(ActionEvent e){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.show(this, ((JButton) e.getSource()).getText());
    }
    
    public JPanel createTitle() {
        // Create the title screenSize
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_title.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);

        JButton registerBtn = new JButton(registerButton);
        registerBtn.setBounds(180,350,200,20);
        registerBtn.addActionListener(this);
        panel.add(registerBtn,0);
        
        JButton loginBtn = new JButton(loginButton);
        loginBtn.setBounds(420,350,200,20);
        loginBtn.addActionListener(this);
        panel.add(loginBtn,0);

        JButton skipBtn = new JButton(skipButton);
        skipBtn.setBounds(300,400,200,20);
        skipBtn.addActionListener(this);
        panel.add(skipBtn,0);
        
        JButton creditsBtn = new JButton(creditsButton);
        creditsBtn.setBounds(5,575,100,20);
        creditsBtn.addActionListener(this);
        panel.add(creditsBtn,0);
        
        JButton rankBtn = new JButton(rankButton);
        rankBtn.setBounds(695,575,100,20);
        rankBtn.addActionListener(this);
        panel.add(rankBtn,0);
        
        return panel;
    }
    
    public JPanel createRegistration() {
        // Create the registration screenSize
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_registration.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);
        JButton back = new JButton("<");
        back.setBounds(5,5,50,20);
        back.addActionListener(this);
        panel.add(back,0);
        return panel;
    }
    
    public JPanel createLogin(){
        // Create the login screenSize
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_login.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);
        JButton back = new JButton("<");
        back.setBounds(5,5,50,20);
        back.addActionListener(this);
        panel.add(back,0);
        return panel;
    }
    
    public JPanel createBoard(){
        // Create the board
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_board.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);
        JButton back = new JButton("<");
        back.setBounds(5,5,50,20);
        back.addActionListener(this);
        panel.add(back,0);
        
        JPanel board = new JPanel();
        JLabel square[] = new JLabel[64];
        GridLayout grid = new GridLayout();
        grid.setRows(8);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setColumns(8);
        board.setOpaque(false);
        board.setLayout(grid);
        board.setBounds(150,50,498,498);
        panel.add(board,0);
        
        int i = 0;
        for (int x=0; x<=7; x++) {
            for (int y=0; y<=7; y++) {
                square[i] = new JLabel("", JLabel.CENTER);
                square[i].setOpaque(false); // add the cells but make them transparent
                board.add(square[i]);
                i++;
            }
        }
        return panel;
    }
    
    public JPanel createCredits() {
        // Create the credits screenSize
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_credits.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);
        JButton back = new JButton("<");
        back.setBounds(5,5,50,20);
        back.addActionListener(this);
        panel.add(back,0);
        return panel;
    }
    
    public JPanel createRankings(){
        // Create the rank screenSize
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel bg = new JLabel(new ImageIcon(imageFolder +"screen_rankings.jpg"));
        bg.setOpaque(true);
        bg.setBounds(0,0,screenSize[0],screenSize[1]);
        panel.add(bg,-1);
        JButton back = new JButton("<");
        back.setBounds(5,5,50,20);
        back.addActionListener(this);
        panel.add(back,0);
        return panel;
    }
}
