package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.clicker.wrappers.StudentResponse;

public class StudentResponses {
	DatabaseResultSetQueries dbrsqueries = new DatabaseResultSetQueries();

	public String getStudentName(Connection conn, String StudentID) {
		String StudentName = "";
		ResultSet result = null;

		String Query = "SELECT StudentName FROM student WHERE StudentID='"
				+ StudentID + "'";

		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeQuery(Query);

			while (result.next()) {
				StudentName = result.getString("StudentName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StudentName;
	}

	public Vector<StudentResponse> getStudentResponses(Connection conn, int QuizRecordID, String QuestionID) {
		String Query = "SELECT StudentID, GROUP_CONCAT(Response) as Response from quizrecordquestion where QuizRecordID = '"
				+ QuizRecordID + "' AND QuestionID='"+QuestionID+"' GROUP BY StudentID";

		ResultSet result = dbrsqueries.runResultSetQuery(conn, Query);
		Vector <StudentResponse> StudentResponses  = new Vector <StudentResponse>();
		try {
			while (result.next()) {
				StudentResponse responses = new StudentResponse();				
				System.out.println("Inside "+result.getString("StudentID"));				
				responses.setStudentID(result.getString("StudentID"));
				responses.setStudentName(getStudentName(conn,
						result.getString("StudentID")));
				responses.setResponse(result.getString("Response").replace(",", ""));
				StudentResponses.addElement(responses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StudentResponses;
	}
	

	public Vector<StudentResponse> getDemoStudentResponses(Connection conn, int QuizRecordID, String QuestionID) {
		String Query = "SELECT StudentID, Response  from demoquizrecordquestion where QuizRecordID = '"
				+ QuizRecordID + "' AND QuestionID='"+QuestionID+"' ORDER BY StudentID";

		ResultSet result = dbrsqueries.runResultSetQuery(conn, Query);
		Vector <StudentResponse> StudentResponses  = new Vector <StudentResponse>();
		try {
			while (result.next()) {
				StudentResponse responses = new StudentResponse();				
				System.out.println("Inside "+result.getString("StudentID"));				
				responses.setStudentID(result.getString("StudentID"));
				responses.setResponse(result.getString("Response"));
				StudentResponses.addElement(responses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StudentResponses;
	}
	

}
