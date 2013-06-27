package com.clicker.QuestionBank;

import java.sql.*;
import com.clicker.databaseconn.*;

public class GetAllQuestions {
	Connection con;
	String quest;
	private int archive = 0;

	public void setAllQuestions(int archive) {
		this.archive = archive;
	}

	public GetAllQuestions() {
		// TODO Auto-generated constructor stub
		con = null;
		quest = "";
	}

	public String getAllQuestions(int qtype,String InstrID) {
		DatabaseConnection db = new DatabaseConnection();
		con = db.createDatabaseConnection();
		String query = null;
		PreparedStatement pstmt = null;
		try {
				if (qtype != 0)
				{
					query = "select Question, QuestionID, Archived, QuestionType from question where QuestionType=? and InstrID=?";
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, qtype);
					pstmt.setString(2,InstrID);
				}
				else 
				{
					query="select Question,QuestionID,Archived, QuestionType from question where InstrID=?";
					pstmt=con.prepareStatement(query);
					pstmt.setString(1,InstrID);
				}
				
				ResultSet rs=null;
				rs = pstmt.executeQuery( );
			if(rs!=null){
			while(rs.next()) {
				
				if(rs.getInt("Archived") == 0 && archive == 0)
				{
					if((rs.getInt("QuestionType") == qtype))
					{
						quest+=rs.getString("Question");
						quest+="@~";
						quest+=rs.getString("QuestionID");
						quest+="@@";
					}
					else
					{
						quest+=rs.getString("Question");
						quest+="@~";
						quest+=rs.getString("QuestionID");
						quest+="@@";
					}
				
					
				}
				
				else if(rs.getInt("Archived") == 1 && archive == 1)
				{
					if(rs.getInt("QuestionType") == qtype)
					{
						quest+=rs.getString("Question");
						quest+="@~";
						quest+=rs.getString("QuestionID");
						quest+="@@";
					}
					else
					{
						quest+=rs.getString("Question");
						quest+="@~";
						quest+=rs.getString("QuestionID");
						quest+="@@";
					}
				}
				
			}
			}
			else{
				System.out.println("Result set is empty");
			}
			rs.close();
			pstmt.close();
			con.close();
			System.out.println("--->" + quest.substring(0, quest.length() - 1));
		} catch (Exception e) {
			System.out.println("Fatal Error on the server side:" + e);
		}
		return quest.substring(0, quest.length());
	}
}
