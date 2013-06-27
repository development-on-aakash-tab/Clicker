<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
try{
	
	
	String DeptName = (String) session.getAttribute("DeptName");
	String DeptID = (String) session.getAttribute("DeptID");
	Connection con = null;
	ResultSet rs1 = null;
	Statement st1 = null;
	String courseId = null;
	String InstructorID = (String) session.getAttribute("InstructorID");
	DatabaseConnection dbconn = new DatabaseConnection();
	con = dbconn.createDatabaseConnection();
	st1 = con.createStatement();
	rs1 = st1
			.executeQuery("SELECT CourseID FROM instructorcourse where InstrID = '"
					+ InstructorID + "'");
	while (rs1.next()) {
		courseId = rs1.getString("CourseID");
	}

	java.sql.Timestamp sqlDate;
	sqlDate = (java.sql.Timestamp) application
			.getAttribute(InstructorID + "Attendance_TS");
	String info = request.getParameter("info");
	int present = 0, absent = 0;
	if (info.equals("Present")) {
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("SELECT s.StudentID, s.StudentName, a.Attendance_Flag as Attendance FROM attendance a, student s where a.CourseID = '"
						+ courseId
						+ "' and a.StudentID = s.StudentID and a.AttendanceTS = '"
						+ sqlDate + "' and a.Attendance_Flag='1'");
		String attResponse = "";
		while (rs.next()) {
			attResponse += rs.getString("StudentID") + ";"
					+ rs.getString("StudentName") + ";" + "Present"
					+ "@";

		}
		rs.close();
		st.close();
		out.println(attResponse);
	} else if (info.equals("Absent")) {
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("SELECT s.StudentID, s.StudentName, a.Attendance_Flag as Attendance FROM attendance a, student s where a.CourseID = '"
						+ courseId
						+ "' and a.StudentID = s.StudentID and a.AttendanceTS = '"
						+ sqlDate + "' and a.Attendance_Flag='0'");
		String attResponse = "";
		while (rs.next()) {
			attResponse += rs.getString("StudentID") + ";"
					+ rs.getString("StudentName") + ";" + "Absent"
					+ "@";
		}
		rs.close();
		st.close();
		out.println(attResponse);		
	}
	rs1.close();
	st1.close();
	con.close();
}catch(Exception e){
	e.getStackTrace();
}
	
%>