<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id= "my_table" border="1" bordercolor="black" cellspacing="1" cellpadding="5" width= "100%" align="center">
		<tbody>
<%
String search=request.getParameter("search");
String type=request.getParameter("type");
//out.println(type);
Connection conn = null;
DatabaseConnection dbconn = new DatabaseConnection();
conn = dbconn.createDatabaseConnection();
Statement st = conn.createStatement();
if(type.equals("dept"))
{	
		try {
				int i=0;
				String dept_id=null;
			 	String query="select DeptID,DeptName,HOD, InstiID from department where DeptName LIKE '%"+search+"%'"; 
				ResultSet rs = st.executeQuery(query);
				while(rs.next())
				{
					i++;
					
					dept_id = rs.getString(1);
					String dept_name = rs.getString(2);
					String hod = rs.getString(3);
					String insti_id = rs.getString(4);
					//out.println("dept_id"+num);
					%>
						
				<tr id="check_<%=i%>">	
					<td align="center" id = "td1_<%=i%>" width= "21%"><%=dept_id %></td>
					<td  align="center" id = "td2_<%=i%>" width= "21%"><%=dept_name%></td>
					<td align="center" id = "td3_<%=i%>" width= "20%"><%=hod%></td>
					<td align="center" id = "td4_<%=i%>" width= "20%"><%=insti_id %></td>		
					<td width= "5%"><img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>
					<td width= "5%"><img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>	
				</tr>
				<%}//end of while...%>
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<%
		
			}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
}//end of dept
else
if(type.equals("course"))
{
	try {
		int i=0;
		String query="select CourseID,CourseName,CourseDesc,DeptID from course where CourseName LIKE '%"+search+"%'"; 
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			i++;
			String course_id = rs.getString(1);
			String course_name = rs.getString(2);
			String desc = rs.getString(3);
			String dept_id = rs.getString(4);
			%>
			<tr id="check_<%=i%>">	
			<td align="center" id = "td1_<%=i%>" width= "21%"><%=course_id %></td>
			<td  align="center" id = "td2_<%=i%>" width= "21%"><%=course_name%></td>
			<td align="center" id = "td3_<%=i%>" width= "20%"><%=desc%></td>
			<td align="center" id = "td4_<%=i%>" width= "20%"><%=dept_id %></td>		
			<td width= "5%"><img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>
			<td width= "5%"><img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>	
			</tr>
		<%}//end of while...%>
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<%}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	
}//end of course
else
if(type.equals("inst"))
{
	//out.println("instructor");
	try {
		int i=0;
		String query="select * from instructor where InstrName LIKE '%"+search+"%'"; 
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			i++;
			String instr_id = rs.getString(1);
			String instr_name = rs.getString(2);
			String doj = rs.getString(3);
			String dept_id = rs.getString(4);
			String desg = rs.getString(6);
			String email_id = rs.getString(7);
			String mobile = rs.getString(8);
			String admin_pri = rs.getString(9);
			
			%>
				
			<tr id="check_<%=i%>">	
			<td align="center" id = "td1_<%=i%>" width= "6%"><%=instr_id %></td>
			<td align="center" id = "td2_<%=i%>" width= "15%"><%=instr_name%></td>
			<td align="center" id = "td3_<%=i%>" width= "10%"><%=doj%></td>
			<td align="center" id = "td4_<%=i%>" width= "11%"><%=dept_id %></td>
			<td align="center" id = "td5_<%=i%>" width= "10%"><%=desg %></td>	
			<td align="center" id = "td6_<%=i%>" width= "12%"><%=email_id %></td>
			<td align="center" id = "td7_<%=i%>" width= "10%"><%=mobile %></td>
			<td align="center" id = "td8_<%=i%>" width= "5%"><%=admin_pri %></td>	
			<td width= "5%"><img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>
			<td width= "5%"><img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>
			</tr>
		<%}//end of while...%>
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<%}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
}//end of inst
else
if(type.equals("student"))
{
	try {
		int i=0;
		String query="select StudentID,StudentRollNo,StudentName,YearofJoining,Privileges,DeptID from student where StudentName LIKE '%"+search+"%'"; 
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			i++;
			String stud_id=rs.getString(1);
			String roll_no = rs.getString(2);
			String stud_name = rs.getString(3);
			String year= rs.getString(4);
			String privileges = rs.getString(5);
			String dept_id=rs.getString(6);
			//out.println("dept_id"+num);
			%>
				
		<tr id="check_<%=i%>">	
			<td align="center" id = "td1_<%=i%>" width= "12%"><%=stud_id %></td>
			<td  align="center" id = "td2_<%=i%>" width= "12%"><%=roll_no%></td>
			<td align="center" id = "td3_<%=i%>" width= "15%"><%=stud_name%></td>
			<td align="center" id = "td4_<%=i%>" width= "12%"><%=year%></td>	
			<td align="center" id = "td5_<%=i%>" width= "12%"><%=privileges%></td>	
			<td align="center" id = "td6_<%=i%>" width= "12%"><%=dept_id%></td>		
			<td width= "5%"><img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>
			<td width= "5%"><img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" /></td>	
		</tr>
		<%}//end of while...%>
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<%}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
	
}
else
{
	out.println("some error!!");
}


%>
</tbody>
</table>

</body>
</html>