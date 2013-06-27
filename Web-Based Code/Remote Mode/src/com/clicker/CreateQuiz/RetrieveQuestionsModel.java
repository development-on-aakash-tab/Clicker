package com.clicker.CreateQuiz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.clicker.databaseconn.DatabaseConnection;
import java.sql.Connection;

import java.sql.Statement;



public class RetrieveQuestionsModel {
	private Connection conn=null;
	private Statement statement=null;
	private String searchParameter=null;
	private ArrayList<Question> list=new ArrayList<Question>();
	int qtype = 0;
	String InstrID=null;
	public RetrieveQuestionsModel(int qtype, String InstrID){
		try{
			DatabaseConnection dbcon = new DatabaseConnection();
			conn = dbcon.createDatabaseConnection();
			this.qtype = qtype;
			this.InstrID=InstrID;
		}
		catch(Exception ex){
			System.out.println("Exception At 1");
			ex.printStackTrace();
		}
	}
	public void setSearchParameter(String s){
		searchParameter=s;
	}
	public ArrayList<Question> getQuestions(){
		try {
			statement=(Statement) conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception At 2");
			e.printStackTrace();
		}
		try {
			if(qtype == 0)
				statement.execute("SELECT QuestionID,Question,Archived, QuestionType FROM question WHERE InstrID='"+InstrID+"' and UPPER(Question) LIKE '%"+((searchParameter==null)?("%"):(searchParameter))+"%"+"'");
			else
				statement.execute("SELECT QuestionID,Question,Archived, QuestionType FROM question WHERE InstrID='"+InstrID+"' and UPPER(Question) LIKE '%"+((searchParameter==null)?("%"):(searchParameter))+"%" + "'" + " AND QuestionType=" + qtype );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception At 4");
			e.printStackTrace();
		}
		ResultSet rs=null;
		try {
			rs=(ResultSet) statement.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception At 5");
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				if(rs.getInt("Archived")==0)
					list.add(new Question(Integer.parseInt(rs.getString("QuestionID")),rs.getString("Question")));
			}
			statement.close();
			conn.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
}
