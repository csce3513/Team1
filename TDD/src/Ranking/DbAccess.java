/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ranking;

/**
 *
 * @author Joshua King
 */

import java.sql.*;
public abstract class DbAccess extends Ranking 
{
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://jarvis.cast.uark.edu/battlechess"
							+ "user=battlechess&password=b@ttl3CHe$$");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement
					.executeQuery("select * from FEEDBACK.COMMENTS");
			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into  FEEDBACK.COMMENTS values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
                        //"id, username, wins, losses, played"
			// Parameters start with 1
//			preparedStatement.setString(1, "Test");
//			preparedStatement.setString(2, "TestEmail");
//			preparedStatement.setString(3, "TestWebpage");
//			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//			preparedStatement.setString(5, "TestSummary");
//			preparedStatement.setString(6, "TestComment");
                        preparedStatement.setString(1, user);
                        preparedStatement.setInt(2, wins);
                        preparedStatement.setInt(3, losses);
                        preparedStatement.setInt(4, played);
			preparedStatement.executeUpdate();
                        

//			preparedStatement = connect
//					.prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
////                        preparedStatement = connect
////                                        .prepareStatement(user, wins, losses, played);
                        preparedStatement = connect
                                        .prepareStatement("SELECT username, wins, losses, played.");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect
			.prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();
			
			resultSet = statement
			.executeQuery("select * from FEEDBACK.COMMENTS");
			writeMetaData(resultSet);
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// 	Now get some metadata from the database
		// Result set get the result of the SQL query
		
		System.out.println("The columns in the table are: ");
		
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
//			String user = resultSet.getString("myuser");
//			String website = resultSet.getString("webpage");
//			String summery = resultSet.getString("summery");
//			Date date = resultSet.getDate("datum");
//			String comment = resultSet.getString("comments");
//			System.out.println("User: " + user);
//			System.out.println("Website: " + website);
//			System.out.println("Summery: " + summery);
//			System.out.println("Date: " + date);
//			System.out.println("Comment: " + comment);
                        resultSet.getString(user);
                        resultSet.getInt(wins);
                        resultSet.getInt(losses);
                        resultSet.getInt(played);
			System.out.println("User: " + user);
			System.out.println("Wins: " + wins);
			System.out.println("Losses: " + losses);
			System.out.println("Games Played: " + played);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}    

