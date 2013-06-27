<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
    session.setAttribute("insid",InstructorID);
	System.out.println("Instructor ID is : " + InstructorID);

	if (InstructorID == null) {
		request.setAttribute("Error","Your session has expired.");
		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/errorpages/Error.jsp");
		rd.forward(request, response);
	}
	//System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	//System.out.println((String)session.getAttribute("Usertype"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try
{
	DatabaseConnection dbconn = new DatabaseConnection();
	Connection conn = dbconn.createDatabaseConnection();
		Statement st =conn.createStatement();
	String type = request.getParameter("hid");
	if(type.equals("1"))
	{
			String course_id = request.getParameter("edit_txt1");
			//out.println(dept_id);
			String course_name = request.getParameter("edit_txt2");
			String course_desc = request.getParameter("edit_txt3");
			String dept_id = request.getParameter("edit_txt4");
			
				String query1 = "Update course set CourseName='"+course_name+"', CourseDesc= '"+course_desc+"', DeptID = '"+dept_id+"' where CourseID ='"+course_id+"'";
				int rs1=st.executeUpdate(query1);
				if((rs1!=0))
				{
					out.println("Successful");
					
				}
				else
					out.println(" Not Successful");
				response.sendRedirect("courses.jsp");
	}
	else
	if(type.equals("2"))
	{
		String course_id=request.getParameter("new_value_1");
		String course_name=request.getParameter("new_value_2");
		String course_desc=request.getParameter("new_value_3");
		String dept_id=request.getParameter("new_value_4");
		String query3=" select * from course where CourseID='"+course_id+"'";
		ResultSet rs=st.executeQuery(query3);
		int i=0;
		while(rs.next())
		{
			i++;
		}
		
		if(i==1)
		{	//out.println("hello");
			session.setAttribute("error_code","1");
			response.sendRedirect("courses.jsp");
		}
		else
		{
		
		String query2="insert into course(CourseID,CourseName,CourseDesc,DeptID) values('"+course_id+"','"+course_name+"','"+course_desc+"','"+dept_id+"') " ;
		int rs2=st.executeUpdate(query2);
		if(rs2!=0)
		{
			System.out.println("Successful!!");
		}
		else
		{
			System.out.println(" Not Successful!!");
		}
		
		response.sendRedirect("courses.jsp");
		}
		
	}//end of type 2
	else
		if (type.equals("3"))
		{
				String course_id=request.getParameter("hid1");
				String query3 = "DELETE from course where CourseID = '"+course_id+"'";
				int rs3 = st.executeUpdate(query3);
				if(rs3!=0)
				{
					out.println("Successful");
					
				}
				else
				{
					out.println(" Not Successful");
				}
				response.sendRedirect("courses.jsp");
		}
		
}
catch(Exception e)
{
	out.println(e);
}
%>
</body>
</html>