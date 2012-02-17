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

        int i = 0;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                square[i] = new JLabel("", JLabel.CENTER);
                square[i].setOpaque(false); // add the cells but make them transparent
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
}
