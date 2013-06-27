package com.clicker.CreateQuiz;

import com.clicker.databaseconn.*;

import java.sql.*;

public class QuizNameSelect 
{
	public int quiztype = 0;
	String quizname [ ];
	public String [ ] quiz_Name(int qt, String courseID)
	{
		quiztype = qt;
		Connection conn = null;
		try
		{
			DatabaseConnection dbconn = new DatabaseConnection ( );
			conn = dbconn.createDatabaseConnection();
			//System.out.println("In quiz name select");
			PreparedStatement ps = null;
			ps = conn.prepareStatement("Select QuizName from quiz where QuizType = ? and CourseID = ? and Archived = 0");
			ps.setInt(1, quiztype);
			ps.setString(2, courseID);			
			ResultSet rs = ps.executeQuery( );
			rs.last();
			int n = rs.getRow( );
			rs.beforeFirst();
			quizname = new String[n];
			int i = 0;
			while (rs.next())
			{			
				quizname[i] = rs.getString("QuizName");
				i++;
			}		
			rs.close( );
			ps.close( );					
		}catch(SQLException e)
		{
			e.printStackTrace( );
		}finally{
			try {
				conn.close( );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return quizname;
		
	}
}
