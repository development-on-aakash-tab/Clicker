package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.clicker.databaseconn.*;

public class QuizRecordQueries {
	
	private int qrID=0;
	private String qrTS=null;
	
	public int getLatestQuizRecordID(Connection conn, int QuizID)
			throws SQLException {
		
		ResultSet rs=null;
		String qrecQuery = "SELECT QuizRecordID FROM quizrecord "
				+ "where QuizID = '"+QuizID+"' ORDER BY Timestamp desc "
				+ "LIMIT 1";
		Statement st = conn.createStatement();
		rs = st.executeQuery(qrecQuery);
		if(rs.next()){
			qrID = rs.getInt(1);	
		}else{
			//qrID=1;
			System.out.println("value is not thr value = 1");
		}
		
		return qrID;
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
	
	public int getLatestRemoteQuizRecordID(Connection conn, int QuizID)
			throws SQLException {
		String qrecQuery = "SELECT QuizRecordID FROM remotequizrecord "
				+ "where QuizID = '"+QuizID+"' ORDER BY QuizTimestamp desc "
				+ "LIMIT 1";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(qrecQuery);
		rs.next();
		int qrID = rs.getInt(1);
		return qrID;
	}

	public void addQuizRecord(Connection conn, int QuizID) {
		PreparedStatement ps;
		String Query = "INSERT INTO quizrecord (QuizID) VALUES(?)";
		try {
			ps = conn.prepareStatement(Query);
			ps.setInt(1, QuizID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addRemoteQuizRecord(Connection conn, int QuizID) {
		PreparedStatement ps;
		String Query = "INSERT INTO remotequizrecord (QuizID) VALUES(?)";
		try {
			ps = conn.prepareStatement(Query);
			ps.setInt(1, QuizID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getMaximumQuizRecordsIndex(Connection conn) {
		int QuizRecordIndex = 0;
		String Query1 = "SELECT max(QRIndex) FROM quizrecordquestion WHERE QuizRecordID IN "
				+ "(SELECT max(QuizRecordID) FROM quizrecordquestion)";

		Statement stmt;
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
		return QuizRecordIndex;
	}
	
	public int getRemoteQuestionID(Connection conn, int QuizID)
	{
		int QuestionID=0;
		ResultSet rs=null;
		String Query = "SELECT QuestionID FROM quizquestion " +
				"WHERE QuizID = '"+QuizID+"'";
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next())
			{
				QuestionID=rs.getInt("QuestionID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return QuestionID;
	}
	
	public int getRemoteOptionID(Connection conn, int QuizID, int QuestionID, String Response)
	{
		int OptionID=0;
		ResultSet rs=null;
		
		int OptionIDs[]=null;
		
		String Query = "SELECT OptionID from quizquestionoption " +
				"WHERE 	QuizID='"+QuizID+"' " +
						"and QuestionID='"+QuestionID+"'";
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DatabaseResultSetQueries rsq = new DatabaseResultSetQueries();
		OptionIDs = rsq.runIntArrayQuery(conn, rs);
		
		if (Response.equals("Z"))
		{
			OptionID=0;
		}
		else
		{
			String ResponseSet="ABCDEF";
			int OptionIDIndex = ResponseSet.indexOf(Response);			
			OptionID = OptionIDs[OptionIDIndex];
		}
		
		return OptionID;
	}
	
	public boolean isParticipantAvailable(Connection conn, int ParticipantID)
	{
		String Query = "SELECT * FROM participant WHERE ParticipantID='"+ParticipantID+"'";
		ResultSet rs=null;
		int CenterID=0;
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(Query);
			
			while (rs.next())
			{
				CenterID = rs.getInt("CenterID");
				System.out.println("Center ID is @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+CenterID);
				
				if (CenterID!=0)
				{
					return true;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
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