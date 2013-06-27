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
<title>Department</title>
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
            width: 607px;
            overflow: auto;
          }
        </style>
        <script type="text/javascript" src="../../javascript/jquery.js"></script>
         <script type="text/javascript" src="../../javascript/admin/dept.js"></script>
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
				alert("DEPT. ID ALREADY EXISTS!!!");
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
 
<%
try{
	DatabaseConnection dbconn = new DatabaseConnection();
	Connection conn = dbconn.createDatabaseConnection();
		Statement st =conn.createStatement();
	
		
		int i=0;
		String num=null;
		String dept_id=null;
		String query1="select DeptID,DeptName,HOD, InstiID from department" ;
		ResultSet rs=st.executeQuery(query1);
		%>
		<div style= "margin-top:5px;margin-left: 832px;"><font color="white" size="3" > CREATE BACKUP</font><img src="images.jpg" id="backup" width="42" height="32" onclick="location.href='back_up.jsp'" title = "DataBase Backup"/></div>
		<center>
		<br><br>
		<form action="" method="post">
		<table cellpadding = "10">
		<tr><td><font color = "white"><b>SEARCH BY NAME :</b></font></td><td><input type="text" name="search_box" id="search_box"/></td>
		<td>
			<img src="searchh.jpg" onclick = "search_result()" width="32" height="32" alt="button" border="0" title = "Search"/>
		</td> </tr>
		</table>
		</form>
		<br>
		<form name= "my_form" action = "dept_conn.jsp" method = "post">
		<div style= "width:600px;color:white;">
		<table id="mytable" border="0" cellspacing="1" cellpadding="5" width= "100%">
		
		<tr><th width= "22%">DEPARTMENT ID</th><th width= "23%">  DEPARTMENT NAME</th><th width= "22%">HOD</th><th width= "19%">INSTITUTE ID</th>
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
			<td width= "5%">
			<img src="2.png" id = "e_<%=i%>" onclick = "edit_values(<%=i%>)" width="32" height="32" alt="button" border="0" title="Edit"/>
			</td>
			<td width= "5%">
			<img src="del.png" onclick = "delete_values(<%=i%>)" width="32" height="32" alt="button" border="0" title="Delete"/>
			</td>
			</tr>
					<%}
		 %>
		</tbody>
		</table>
		<br></br>
		<img src="new.png" id="addnew" onclick = "add_new()" width="32" height="32" alt="button" border="0" title="Add New"/>
		
		<br></br><br></br>
		
		
		</div>
		
	
		<input type="hidden" name="count" id="count" value="<%=i+1%>" />
		<select name="inst" id="inst" size="8" style = "width:180px;display:none" >
		<%
		String d_query="select InstiID from institution";
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
	</form>
	
		<%	
		
		
}
catch(Exception e)
{
	out.println(e);
}

%>
</center>

</body>
<%@ include file="../../../jsp/includes/footer.jsp" %>
</html>