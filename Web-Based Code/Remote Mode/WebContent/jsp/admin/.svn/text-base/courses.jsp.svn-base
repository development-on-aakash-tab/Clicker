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
<title>Courses</title>
<style> 

#cssmenu
 {
 font-family: arial, sans-serif;
 text-align:center; 
 width:auto;
 height:30px;
 font-weight:bold;
 font-size:32px;
 border: 1
 }
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
            width: 657px;
            overflow: auto;
          }
        </style>
        </head>
        <script type="text/javascript" src="../../javascript/jquery.js"></script>
        <script type="text/javascript" src="../../javascript/admin/courses.js"></script>
        <link href="../../jquery/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="../../jquery-ui-1.8.21.custom.css" rel="stylesheet"	type="text/css" />
         <link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />         
		<script src="../../jquery/jquery-1.5.min.js"></script>
		<script src="../../jquery/jquery-1.8.19-ui.min.js"></script>

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
				alert("COURSE ID ALREADY EXISTS!!!");
				<%
				session.setAttribute("error_code","0");
			}
		}
		%>
		}
       </script>
        <%@ include file="../newMenu/newMenuwithCSS.jsp" %>
       <body onload="check_err()">
      

      
    <%
     try{
	DatabaseConnection dbconn = new DatabaseConnection();
	Connection conn = dbconn.createDatabaseConnection();
		Statement st =conn.createStatement();		
		int i=0;
		String num=null;		
		String query1="select CourseID,CourseName,CourseDesc,DeptID from course" ;
		ResultSet rs=st.executeQuery(query1);
		%>				
		</br></br>
		<center>		
		<div style= "margin-top:5px;margin-left: 600px;">	
			<font color="white" size="3" > CREATE BACKUP</font>
		    <img src="images.jpg" id="backup" width="42" height="32" onclick="location.href='back_up.jsp'" title = "DataBase Backup"/>
		</div>	
		<form action="" method="post">
		<table cellpadding = "10">
		<tr><td><font color = "white"><b>SEARCH BY NAME :</b></font></td><td><input type="text" name="search_box" id="search_box"/></td>
		<td><img src="searchh.jpg" onclick = "search_result()" width="32" height="32" alt="button" border="0" title = "Search"/></td> </tr>
		</table>
		</form>
		<br>
		<form name= "my_form" action = "course_conn.jsp" method = "post">
		<div style= "width:650px;color:white;">				<table id="mytable" border="0" cellspacing="1" cellpadding="5" width= "100%">
		
		<tr><th width= "14%">COURSE ID</th><th width= "30%">COURSE NAME</th><th width= "30%">DESCRIPTION</th><th width= "14%">DEPT ID</th>
		<th width= "8%"></th><th width= "8%"></th><th width= "6%"></th></tr></table>
		</div>
		<br>
		<div id = "wrapper" style= "color:black;">
		<table id= "my_table" border="1" bordercolor="black" cellspacing="1" cellpadding="5" width= "100%" align="center">
		<tbody>
		<% 
		while(rs.next())
		{
			i++;
			num=Integer.toString(i);
			String course_id = rs.getString(1);
			String course_name = rs.getString(2);
			String desc = rs.getString(3);
			String dept_id = rs.getString(4);
			//out.println("dept_id"+num);
			%>
				
		<tr id="check_<%=i%>">	
			<td align="center" id = "td1_<%=i%>" width= "15%"><%=course_id %></td>
			<td  align="center" id = "td2_<%=i%>" width= "30%"><%=course_name%></td>
			<td align="center" id = "td3_<%=i%>" width= "30%"><%=desc%></td>
			<td align="center" id = "td4_<%=i%>" width= "15%"><%=dept_id %></td>		
			<td width= "5%"><img src="2.png" id = "e_<%=i%>"  onclick = "edit_values(<%=i%>)" width="32" height="32" alt="Edit" title="Edit" border="0" />
			</td>
			<td width= "5%">
			<img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" title="Delete" /></td>	
		</tr>
		<%}		
		
		 %>
		</tbody>
		</table>
		
		<br></br><br></br>
		<img src="new.png" id="addnew" onclick = "add_new()" width="32" height="32" alt="button" border="0" title="Add New"/>
		
		
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		
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
		
	<input type = "hidden" id= "hid" name = "hid">
	<input type = "hidden" id = "hid1" name = "hid1">
	
	
		<%			
		
}
catch(Exception e)
{
	out.println(e);
}

%>
  
 </div>	
 </form>	  
</center>		
    
      
      
       
       </body>
       <%@ include file="../../jsp/includes/footer.jsp"%>

 
</html>