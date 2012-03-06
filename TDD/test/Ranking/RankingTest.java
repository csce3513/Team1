/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ranking;

import junit.framework.TestCase;
public class RankingTest extends TestCase 
{    
    public void testRanking() 
    {
      System.out.println("Ranking");
      Ranking r = new Ranking();
      r.setWins(6);
      r.setLosses(2);
      r.setUser("jak005");
      r.setWins(12);
      r.setLosses(3);
      assertEquals(18, r.getWins());
      assertEquals(5, r.getLosses());
      assertEquals("jak005", r.getUser());
      r.Ranking(r.getWins(), r.getLosses());
    }
    }
