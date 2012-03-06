package BattleChess;

import java.util.Scanner; 
import javax.swing.*;

public class Ranking {
    
    String user;
    int wins = 0; 
    int losses = 0;
    int played = 0;
    
    public String getUser()
    {
      return user;
    }
    
    public void setUser(String User)
    {
      user = User;
    }
    
    public int getWins()
    {
      return wins;
    }
    
    public void setWins(int Wins)
    {
      wins = Wins + wins;
    }
    
    public int getLosses()
    {
      return losses;
    }
    
    public void setLosses(int Losses)
    {
      losses = Losses + losses;
    }
    
    public int getPlayed()
    {
      return played;
    }
    
    public void setPlayed(int Played)
    {
      played = wins + losses;
    }
    
    public JTable Ranking(int wins, int losses)
    {
        //System.out.println("Enter your user name to see your wins and losses.");
        //Scanner scan = new Scanner(System.in);
        //user = scan.nextLine();
        user = "test";
        Ranking r = new Ranking();
        r.setUser(user);
        r.setWins(wins);
        r.setLosses(losses);
        r.setPlayed(played);

        Object[][] cellData = {{r.getUser(), r.getPlayed(), r.getWins(), r.getLosses()}};
        String[] columnNames = {"User", "Played", "Wins", "Losses"};
        JTable table = new JTable(cellData, columnNames);
        return table;
    }
}

