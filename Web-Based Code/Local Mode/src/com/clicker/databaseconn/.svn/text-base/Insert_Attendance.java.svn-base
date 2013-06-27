package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

import com.clicker.databaseconn.QuizRecordQueries;

public class Insert_Attendance {
	QuizRecordQueries qrq = new QuizRecordQueries();
	
	
	public void insert_student(Connection conn, String InstructorID,java.sql.Timestamp Attendance_TS)
	{
		
		//
		String CourseID=null;
		String StudentID=null;
		
		String Query="SELECT CourseID FROM instructorcourse WHERE InstrID='"+InstructorID+"'";
		ResultSet result = null;
		
		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeQuery(Query);
			
			while(result.next())
			{
				CourseID=result.getString("CourseID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("SELECT StudentID FROM studentcourse WHERE CourseID='"+CourseID+"'");
		
		String Query1="SELECT StudentID FROM studentcourse WHERE CourseID='"+CourseID+"'";
		
		ResultSet result1 = null;
		Statement st1;
		try {
			st1 = conn.createStatement();
			result1 = st1.executeQuery(Query1);
			
			while(result1.next())
			{
				StudentID=result1.getString("StudentID");
				
				this.insert_stud(conn,StudentID,CourseID,Attendance_TS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
 public void insert_stud(Connection conn, String StudentID,
			String CourseID, java.sql.Timestamp Attendance_TS)
 {
	 	     
		//System.out.println("=========>"+CourseID+"=====>"+ StudentID + "=======>"+Attendance_TS);
		//String Update_query="UPDATE attendance SET Attendance_Flag='1' where StudentID='"+ StudentID +"' and CourseID='"+CourseID+"' and timestamp(AttendanceTS) = timestamp('"+Attendance_TS+"')";
		
		String Response="0";
		try {
			String Query = "INSERT INTO attendance(StudentID,CourseID,AttendanceTS,Attendance_Flag) VALUES(?,?,?,?)";
			PreparedStatement ps;
			ps = conn.prepareStatement(Query);
			ps.setString(1, StudentID);
			ps.setString(2, CourseID);
			ps.setTimestamp(3, Attendance_TS);
			ps.setString(4, Response);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
 }
	public int insertAttendanceRecord(Connection conn, String StudentID,
			String CourseID, String Response, java.sql.Timestamp Attendance_TS) {
		System.out.println("Inside insert");
		String Query;
		
		System.out.println("Inside insert Att=========>"+CourseID+"=====>"+ StudentID + "=======>"+Response);
		
		if(Response == "0")
			 Query="UPDATE attendance SET Attendance_Flag='0' where StudentID='"+ StudentID +"' and CourseID='"+CourseID+"' and timestamp(AttendanceTS) = timestamp('"+Attendance_TS+"')";
		else		
		     Query="UPDATE attendance SET Attendance_Flag='1' where StudentID='"+ StudentID +"' and CourseID='"+CourseID+"' and timestamp(AttendanceTS) = timestamp('"+Attendance_TS+"')";
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(Query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;

	}
	
}