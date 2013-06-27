package com.clicker.util;

import java.sql.*;

import com.clicker.databaseconn.DatabaseConnection;

/**
 * 
 * @author rajavel
 * This is helper class for student response in quiz 
 */
public class ResponseHelper {

	/**
	 * This method is used for get Time out (Tablet is in off state) student list form data base
	 * @param quizRecordID Current quiz record id
	 * @param courseID Course id of quiz 
	 * @return List of time out student in HTML table format
	 */
	public String getTimeoutStudentList(int quizRecordID, String courseID){
		System.out.println("Inside ResponseHelper");
		String listOfStudent_Table ="<center><table align='center' border='1' width='200px'> <th> Student ID </th>";
		Connection con=null;
		try{
		DatabaseConnection dbcon = new DatabaseConnection();
		con = dbcon.createDatabaseConnection();
		Statement st = con.createStatement();
		String query = "SELECT dsc.StudentID FROM studentcourse dsc where dsc.CourseID = '"+courseID+"' and dsc.StudentID Not in( SELECT StudentID FROM quizrecordquestion dqrq where dqrq.QuizRecordID = "+quizRecordID +")";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()){
			listOfStudent_Table += "<tr><td align='center'>" + rs.getString("StudentID") + "</td></tr>";
		}
		listOfStudent_Table += "</table></center>";
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return listOfStudent_Table;
	}	
	
	/**
	 * This method is used for get no answer student list
	 * @param quizRecordID Current quiz record id
	 * @return List of student id in the form of HTML table format
	 */
	public String getNoAnswerStudentList(int quizRecordID, int questionID){
		System.out.println("Inside ResponseHelper");
		String listOfStudent_Table ="<center><table align='center' border='1' width='200px'> <th> Student ID </th>";
		Connection con = null;
		try{
		DatabaseConnection dbcon = new DatabaseConnection();
		con = dbcon.createDatabaseConnection();
		Statement st = con.createStatement();
		String query = "SELECT distinct StudentID FROM quizrecordquestion where QuizRecordID="+quizRecordID+" and QuestionID ="+questionID+" and Response = 'Z'";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()){
			listOfStudent_Table += "<tr><td align='center'>" + rs.getString("StudentID") + "</td></tr>";
		}
		listOfStudent_Table += "</table></center>";
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfStudent_Table;
	}	
	
	public String getRemoteCenterList(){
		String centerID="";
		String centerName="";
		Connection con=null;
		try{
		DatabaseConnection dbcon = new DatabaseConnection();
		con = dbcon.createDatabaseConnection();								
		Statement stmt  = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CenterID, CenterName FROM remotecenter");
		while(rs.next()){
			centerID += rs.getString("CenterID") + "@;";
			centerName += rs.getString("CenterName") + "@;";
		}
		}catch(Exception ex)
		{
			ex.getStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return centerID + "@@" + centerName;
	}
	
	public String getInsatantResponTable (int iquizid){
		StringBuffer respTable = new StringBuffer("<table id='restbl' border='1' style='width: 580px;'><tr><td>Student ID</td><td>Student Name</td><td>Response</td></tr>");
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = null;
		try{
		con = dbcon.createDatabaseConnection();								
		Statement stmt  = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT s.StudentID, StudentName, Response FROM instantquizresponse iqr, student s where IQuizID = " + iquizid + " and iqr.StudentID = s.StudentID");
		while(rs.next()){
			respTable.append("<tr><td>" + rs.getString("StudentID") + "</td><td>"+ rs.getString("StudentName") + "</td><td>" + rs.getString("Response") + "</td>" );
		}
		respTable.append("</table>");
		}catch(Exception ex)
		{
			ex.getStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return respTable.toString();
	}
}