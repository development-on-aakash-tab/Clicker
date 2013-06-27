package com.clicker.QuestionBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.clicker.databaseconn.DatabaseConnection;

public class Insert_Question {
	/*
	 * It inserts the question and it's associated details into the database.
	 * Finally returns it the questionID so that the options can be inserted as
	 * well.
	 */
	public int insertQuestion(Connection conn, String Question,
			int LevelOfDifficulty, int Archived, float Credit,
			String ImageName, int QuestionType, String InstrID) {
		DatabaseConnection db = new DatabaseConnection();
		int QuestionID = -1;
		System.out.println("Conn:" + conn);
		try {
			PreparedStatement st = conn
					.prepareStatement(
							"Insert into question(Question,LevelOfDifficulty,Archived,Credit,ImageName,QuestionType,InstrID) values(?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			st.setString(1, Question);
			st.setInt(2, LevelOfDifficulty);
			st.setInt(3, Archived);
			st.setFloat(4, Credit);
			st.setString(5, ImageName);
			st.setInt(6, QuestionType);
			st.setString(7,InstrID);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				QuestionID = rs.getInt(1);
			} else {
				throw new RuntimeException(
						"PIB, can't find most recent insert we just entered");
			}
			conn.close();			
		} catch (Exception e) {
			System.out.println("Fatal Error!!! Exiting due to->" + e);
		} 
		return QuestionID;	
	}
}
