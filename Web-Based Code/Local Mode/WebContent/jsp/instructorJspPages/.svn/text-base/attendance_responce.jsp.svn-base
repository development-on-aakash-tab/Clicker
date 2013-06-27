<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="com.clicker.wrappers.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<%!String UserID;%>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
	String Usertype = (String) session.getAttribute("Usertype");
	System.out.println("Usertype is......... : " + Usertype);
	String DeptName = (String) session.getAttribute("DeptName");
	String DeptID = (String) session.getAttribute("DeptID");
	String usertype = Usertype;
	if (Usertype == null) {
		request.setAttribute("Error", "Your session has expired.");
		System.out.println("calling usertype...... session ended");
		RequestDispatcher rd = request
				.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	} else if (Usertype.equals("Instructor")) {
		UserID = (String) session.getAttribute("InstructorID");
	} else {
		UserID = (String) session.getAttribute("StudentID");
	}

	String InstructorID = (String) session.getAttribute("InstructorID");
%>
<%
String courseId = null;
java.sql.Timestamp sqlDate=null;
Connection con = null;
try{	
	ResultSet rs = null;
	Statement st = null;	
	DatabaseConnection dbconn = new DatabaseConnection();
	con = dbconn.createDatabaseConnection();
	st = con.createStatement();
	rs = st.executeQuery("SELECT CourseID FROM instructorcourse where InstrID = '"
			+ InstructorID + "'");
	while (rs.next()) {
		courseId = rs.getString("CourseID");
	}	
	sqlDate = (java.sql.Timestamp) application.getAttribute(InstructorID + "Attendance_TS");
	rs.close();
	st.close();
}catch(Exception e){
	e.getStackTrace();	
}
%>

<script language="JavaScript" src="../../javascript/attendance.js">

</script>

<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 
		<div>
			</br>
			<div style="min-height: 300px; font-weight: bold; align: center">
				<form>
					<div>
						<div>
							Department Name :
							<%=DeptName%><br /> <br /> Course ID:
							<%=courseId%>
							<%application.setAttribute(InstructorID + "Attendance_Status",
					"TAKEN");%>
							&nbsp;&nbsp;Time Stamp :<%=sqlDate%>
						</div>

						</br>
						<div>
							<div id="Attendance_detail" style="align: center">
								<table border=1>
									<tr>
										<td>Student ID</td>
										<td>Student Name</td>
										<td>Attandance</td>
									</tr>
									<%int present = 0;
			int absent = 0;
			try{
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1
					.executeQuery("SELECT s.StudentID, s.StudentName, if(a.Attendance_Flag = 1 , 'Present', 'Absent') as Attendance FROM attendance a, student s where a.CourseID = '"
							+ courseId
							+ "' and a.StudentID = s.StudentID and a.AttendanceTS = '"
							+ sqlDate + "'");
			String attResponse = "";
			while (rs1.next()) {%>
									<tr>
										<td><%=rs1.getString("StudentID")%></td>
										<td><%=rs1.getString("StudentName")%></td>
										<td><%=rs1.getString("Attendance")%></td>
									</tr>
									<%if (rs1.getString("Attendance").equalsIgnoreCase("Present"))
					present++;
				else
					absent++;
			}
			rs1.close();
			st1.close();
			con.close();
			}catch(Exception e){
				e.getStackTrace();
			}%>
								</table>
							</div>
						</div>
						<div align="center">
							<div align="center" id="present_link"
								style="font-size: medium; color: blue; cursor: pointer"
								onclick="showPresent()">
								Present:<%=present%>
							</div>
							<div align="center" id="absent_link"
								style="font-size: medium; color: blue; cursor: pointer"
								onclick="showAbsent()">
								Absent:<%=absent%>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</center>
	<%@ include file="../../jsp/includes/footer.jsp"%>
</body>
</html>