package battlechess;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class BattleChess extends JFrame {
    
    private JPanel content = null;
    private JPanel panel = null;
    private JLabel cells[] = new JLabel[64];
    
    public BattleChess() {
        super();
        initialize();
        createBoard();
    }
    public static void main(String args[]) {
        new BattleChess().setVisible(true);
    }
    private void initialize() {
        this.setSize(640,640);
        this.setTitle("Battle Chess");
        this.setContentPane(getContentPanel());
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
