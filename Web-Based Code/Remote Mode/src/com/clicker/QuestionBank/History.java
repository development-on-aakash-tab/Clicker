package com.clicker.QuestionBank;

import com.clicker.databaseconn.*;
import java.sql.*;

public class History 
{
	
	public String instructor;
	public int qid;
	public String instructorid, question;
		 
	public History (int questionid, String quest, String instrid)
	{
		qid = questionid;
		question = quest;
		instructorid = instrid;
	}
	
	public void addentry( )
	{
		
		try
		{
			java.util.Date currentDatetime = new java.util.Date(System.currentTimeMillis());
			java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
			
			DatabaseConnection dbconn = new DatabaseConnection ( );
			Connection conn = dbconn.createDatabaseConnection();
			
			PreparedStatement st = null, st1 = null;
			
			st = conn.prepareStatement("select InstrName from instructor where InstrID = ?");
			st.setString(1, instructorid);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				
				instructor=rs.getString(1);
				
			}
			
			st1  = conn.prepareStatement("insert into questionshistory (QuestionID, Question, Instructor, Date) values (?, ?, ?, ?)");
			st1.setInt(1, qid);
			st1.setString(2, question);
			st1.setString(3, instructor);
			st1.setTimestamp(4, timestamp);
			st1.executeUpdate();
			
			
			System.out.println ("Entry added to History");
			st.close ( );
			st1.close();
		}
		catch (Exception ex)
		{
			System.out.println ("The error is: " + ex);
		}
	}

}
