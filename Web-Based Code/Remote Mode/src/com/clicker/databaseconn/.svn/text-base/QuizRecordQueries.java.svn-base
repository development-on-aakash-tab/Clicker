package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizRecordQueries {
	
	
	private String qrTS=null;

	public int getLatestQuizRecordID(Connection conn, int QuizID)
			throws SQLException {
		int qrID=0;
		Statement st=null;
		ResultSet rs=null;
		try
		{
		String qrecQuery = "SELECT QuizRecordID FROM quizrecord "
				+ "WHERE QuizID='" + QuizID + "'" + "ORDER BY Timestamp desc "
				+ "LIMIT 1";
		st = conn.createStatement();
		rs = st.executeQuery(qrecQuery);
		rs.next();
		qrID = rs.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return qrID;
	}

	public void addQuizRecord(Connection conn, int QuizID) {
		System.out.println("Inside...................... Add QuizRecords");
		PreparedStatement ps=null;
		String Query = "INSERT INTO quizrecord (QuizID) VALUES(?)";
		try {
			ps = conn.prepareStatement(Query);
			ps.setInt(1, QuizID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				
				if(ps!=null)ps.close();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getMaximumQuizRecordsIndex(Connection conn) {
		int QuizRecordIndex = 0;
		String Query1 = "SELECT max(QRIndex) FROM quizrecordquestion WHERE QuizRecordID IN "
				+ "(SELECT max(QuizRecordID) FROM quizrecordquestion)";

		Statement stmt=null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Query1);

			if (rs != null) {
				rs.next();
				QuizRecordIndex = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return QuizRecordIndex;
	}
	
	public String getLatestQuizRecordTime(Connection conn, int QuizID)
			throws SQLException {
		ResultSet rs=null;
		
		String qrecQuery = "SELECT TimeStamp FROM quizrecord "
				+ "where QuizID = '"+QuizID+"' ORDER BY Timestamp desc "
				+ "LIMIT 1";
		Statement st = conn.createStatement();
		rs = st.executeQuery(qrecQuery);
		if(rs.next()){
		 qrTS = rs.getString(1);
		 System.out.println(" time available "+qrTS);
		}else{
			System.out.println("no time available");
		}
		
		
		return qrTS;
	}
	
	public String getNumberofQuestions(Connection conn, int QuizID)
			throws SQLException {
		String qrecQuery = "SELECT count(*) as noofrecords FROM quizquestion where QuizID="+QuizID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(qrecQuery);
		rs.next();
		String noofquestions = rs.getString(1);
		return noofquestions;
	}
	
	
	// The following function selects Quiz Records from the quiz table
		public String[ ] getQuizTimestamp(int QuizID) throws SQLException
		{
			DatabaseConnection dbconn = new DatabaseConnection ( );
			Connection conn = dbconn.createDatabaseConnection();
			PreparedStatement ps1 = conn.prepareStatement("Select TimeStamp from quizrecord where QuizID = ? ORDER BY Timestamp desc");
			ps1.setInt(1, QuizID);
			ResultSet rs = ps1.executeQuery();
			int i = 0;
			rs.last();
			int n = rs.getRow( );
			rs.beforeFirst();
			String timestamp[ ] = new String[n];
			while(rs.next())
			{
				timestamp[i] = rs.getString("TimeStamp");
				i++;
			}
			ps1.close( );
			conn.close();
			return timestamp;
		}
}