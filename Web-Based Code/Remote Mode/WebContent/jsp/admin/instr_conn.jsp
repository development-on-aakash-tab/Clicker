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
			String instr_id = request.getParameter("edit2_txt1");
			//out.println(dept_id);
			String instr_name = request.getParameter("edit2_txt2");
			String doj = request.getParameter("edit2_txt3");
			String dept_id = request.getParameter("edit2_txt4");
			String desg = request.getParameter("edit2_txt5");
			//out.println(dept_id);
			String email_id = request.getParameter("edit2_txt6");
			String mobile = request.getParameter("edit2_txt7");
			String admin_pri = request.getParameter("edit2_txt8");
			String course=request.getParameter("edit_txt9");
			System.out.println("====================================>"+course+"========================");
			
				String query1 = "Update instructor set InstrName='"+instr_name+"', DOJ= '"+doj+"',DeptID='"+dept_id+"', Designation = '"+desg+"', EmailID = '"+email_id+"', MobileNo = '"+mobile+"', AdminPriviledges= '"+admin_pri+"' where InstrID ='"+instr_id+"'";
				int rs1=st.executeUpdate(query1);
				if((rs1!=0))
				{
					out.println("Successful");
					
				}
				else
					out.println(" Not Successful");
				
				String query2="update instructorcourse set CourseID='"+course +"' where InstrID='"+instr_id +"';";
				int rs2=st.executeUpdate(query2);
				if((rs2!=0))
				{
					out.println("Successful");
					
				}
				else
					out.println(" Not Successful");
				
				
				response.sendRedirect("instructor.jsp");
	}
	else
		if(type.equals("2"))
		{
			String instr_id=request.getParameter("new_value_1");
			String instr_name=request.getParameter("new_value_2");
			String doj=request.getParameter("new_value_3");
			String dept_id=request.getParameter("new_value_4");
			String desg=request.getParameter("new_value_5");
			String email_id=request.getParameter("new_value_6");
			String mobile=request.getParameter("new_value_7");
			String admin_pri=request.getParameter("new_value_8");
			String course=request.getParameter("new_value_9");
			String query3=" select * from instructor where InstrId='"+instr_id+"'";
			ResultSet rs=st.executeQuery(query3);
			int i=0;
			while(rs.next())
			{
				i++;
			}
			
			if(i==1)
			{	//out.println("hello");
				session.setAttribute("error_code","1");
				response.sendRedirect("instructor.jsp");
			}
			else
			{
			String query2="insert into instructor(InstrID,InstrName,DOJ,DeptID,Password,Designation,EmailID,MobileNo, AdminPriviledges) values('"+instr_id+"','"+instr_name+"','"+doj+"','"+dept_id+"','"+instr_id+"','"+desg+"','"+email_id+"','"+mobile+"','"+admin_pri+"') " ;
			System.out.println(query2);
			int rs2=st.executeUpdate(query2);
			if(rs2!=0)
			{
				out.println("Successful!!");
			}
			else
			{
				out.println(" Not Successful!!");
			}
			
			
			String query4="insert into instructorcourse(Year,Semester,CourseID,InstrID) values('2013','Spring','"+ course +"','"+ instr_id +"') " ;
			int rs4=st.executeUpdate(query4);
			if(rs4!=0)
			{
				System.out.println("Successful!!");
			}
			else
			{
				System.out.println(" Not Successful!!");
			}
			
			
			
			response.sendRedirect("instructor.jsp");
			}
		}//end of type 2
		else
			if (type.equals("3"))
			{
					
					String inst_id=request.getParameter("hid1");
					
					String query4 = "DELETE from instructorcourse where InstrID = '"+inst_id+"'";
					System.out.println(query4);
					int rs4 = st.executeUpdate(query4);
					
					
					
					String query3 = "delete FROM instructor where InstrID='"+inst_id+"'";
					int rs3 = st.executeUpdate(query3);
					if(rs3!=0)
					{
						out.println("Successful");
						
					}
					else
					{
						out.println(" Not Successful");
					}
					response.sendRedirect("instructor.jsp");
			}
}
catch(Exception e)
{
	out.println(e);
}
%>

</body>
</html>