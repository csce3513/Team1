package BattleChess;

import javax.swing.*;

public class Main extends JApplet implements Constants {

    public void init() {
        super.init();
        setName("BattleChess");
        showStatus("Loading...");
        Game game = new Game();
        add(game);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("BattleChess");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(windowSize[0], windowSize[1]);
                Game game = new Game();
                frame.add(game);
                frame.setVisible(true);
            }
        });
    }
}