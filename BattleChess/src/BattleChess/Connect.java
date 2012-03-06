/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

/**
 *
 * @author Joshua King
 */
import java.sql.*;

   public class Connect
   {
       public static void main (String[] args)
       {
           Connection conn = null;

           try
           {
               String userName = "testuser";
               String password = "testpass";
               String url = "jdbc:mysql://localhost/test";
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, userName, password);
               System.out.println ("Database connection established");
           }
           catch (Exception e)
           {
               System.err.println ("Cannot connect to database server");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Database connection terminated");
                   }
                   catch (Exception e) { /* ignore close errors */ }
               }
           }
       }
   }

