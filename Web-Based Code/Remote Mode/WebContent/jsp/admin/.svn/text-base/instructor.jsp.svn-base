<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Instructor</title>
<style> 

         {
            margin: 0;
            padding: 0;
          }
          html, body {
            height: 100%;
            overflow: hidden;
          }
          #wrapper {
            height: 250px;
            width: 807px;
            overflow: auto;
          }
        </style>
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/admin/instructor.js"></script>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
 <script>
		function check_err()
		{
			<%
			if(session.getAttribute("error_code")!=null)
			{
			String err=session.getAttribute("error_code").toString();
			if(err.equals("1"))
			{
				
				%>
				alert("INSTRUCTOR ID ALREADY EXISTS!!!");
				<%
				session.setAttribute("error_code","0");
			}
			}
			%>
		}
        </script>
</head>

<body onload="check_err()">
<%@ include file="../newMenu/newMenuwithCSS.jsp" %>
<center>

<%
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	Connection conn = dbconn.createDatabaseConnection();
		Statement st =conn.createStatement();
		
		int i=0;
		String num=null;
		String query1="select * from instructor" ;
		ResultSet rs=st.executeQuery(query1);
		%>
		<div style= "margin-top:5px;margin-left: 650px;"><font color="white" size="3" > CREATE BACKUP</font><img src="images.jpg" id="backup" width="42" height="32" onclick="location.href='back_up.jsp'" title = "DataBase Backup"/></div>
		
		<form action="" method="post">
		<table cellpadding = "10">
		<tr><td><font color = "white"><b>SEARCH BY NAME :</b></font></td><td><input type="text" name="search_box" id="search_box"/></td>
		<td><img src="searchh.jpg" onclick = "search_result()" width="32" height="32" alt="button" border="0" title = "Search"/></td> </tr>
		</table>
		</form>
		<br><br>
<form name="my_form" action = "instr_conn.jsp" method = "post">


<div style= "width:800px;color:white;">
		<table id="mytable" border="0" cellspacing="1" cellpadding="5" width= "100%">
		
		<tr><th width= "4%">InstrID</th><th width= "13%">Instructor Name</th><th width= "9%">Date Of Joining</th><th width= "8%">Dept ID</th>
		<th width= "8%">Designation</th><th width= "10%">Email-Id</th><th width= "7%">Mobile No.</th><th width = "5%">Admin Privileges</th><th width = "5%">Course</th>
		<th width = "4%"></th>
		</tr></table>
		</div>
		<div id ="wrapper">
<table id= "my_table" border="1" bordercolor="black" cellspacing="1" cellpadding="5" width= "100%" align="center">
		<tbody>
		<% 
		while(rs.next())
		{
			i++;
			num=Integer.toString(i);
			String instr_id = rs.getString(1);
			String instr_name = rs.getString(2);
			String doj = rs.getString(3);
			String dept_id = rs.getString(4);
			String desg = rs.getString(6);
			String email_id = rs.getString(7);
			String mobile = rs.getString(8);
			String admin_pri = rs.getString(9);	
			
			Statement st2 =conn.createStatement();
			String query2="SELECT CourseID FROM instructorcourse where InstrID='"+ instr_id +"'";
			System.out.println(query2);
			ResultSet rs2=st2.executeQuery(query2);
			rs2.next();
			String course=rs2.getString(1);
			
			%>
				
		<tr id="check_<%=i%>">	
			<td align="center" id = "td1_<%=i%>" width= "6%"><%=instr_id %></td>
			<td align="center" id = "td2_<%=i%>" width= "15%"><%=instr_name%></td>
			<td align="center" id = "td3_<%=i%>" width= "10%"><%=doj%></td>
			<td align="center" id = "td4_<%=i%>" width= "8%"><%=dept_id %></td>
			<td align="center" id = "td5_<%=i%>" width= "10%"><%=desg %></td>	
			<td align="center" id = "td6_<%=i%>" width= "12%"><%=email_id %></td>
			<td align="center" id = "td7_<%=i%>" width= "8%"><%=mobile %></td>
			<td align="center" id = "td8_<%=i%>" width= "5%"><%=admin_pri %></td>
			<td align="center" id = "td9_<%=i%>" width= "5%"><%=course %></td>	
			<td width= "2%"><img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" title="Edit"/>
			</td>
			<td width= "2%">
			<img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" title="Delete"/></td>	
		</tr>
		<%
		}%>
		</tbody>
		</table>
		<br></br>
		<img src="new.png" id="addnew" onclick = "add_new()" width="32" height="32" alt="button" border="0" title="Add New"/>

</div>
		
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<input type = "hidden" name = "hid" id ="hid">
		<input type = "hidden" id = "hid1" name = "hid1">
		<select name="dept" id="dept" size="8" multiple = "multiple" style = "width:180px;display:none" >
		<%
		String d_query="select DeptID from department";
		ResultSet rs1=st.executeQuery(d_query);
		while(rs1.next())
		{
			%>
			  <option value="<%=rs1.getString(1)%>" ><%=rs1.getString(1) %></option>
			<%			
		}
		%>
		</select>
		
		
		<select name="course" id="course" size="8" multiple = "multiple" style = "width:180px;display:none" >
		<%
		
		String all_course="SELECT CourseID FROM course";
		ResultSet rs3=st.executeQuery(all_course);
		while(rs3.next())
		{
			%>
			  <option value="<%=rs3.getString(1)%>" ><%=rs3.getString(1) %></option>
			<%
			
		}
		%>
		</select>
		
				
</form>


<%
		
}
		catch(Exception e)
		{
			out.println(e);
		}

		%>
		<%@ include file="../../../jsp/includes/footer.jsp" %>
		</center>
</body>

</html>