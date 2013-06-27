<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%

DatabaseConnection dbconn = new DatabaseConnection();
DatabaseQueries dbqueries = new DatabaseQueries();
Connection conn = dbconn.createDatabaseConnection();
ResultSet rs=null;
try
{
 rs=dbqueries.getRemoteCenterName(conn);

while(rs.next())
{
	System.out.println(rs.getString(1).toString());
	System.out.println();
	out.println(rs.getString(2).toString() + " - " + rs.getString(1).toString());

} 

}
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally
{
	try {
		if(rs!=null)rs.close();
		
		if(conn!=null)conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
%>