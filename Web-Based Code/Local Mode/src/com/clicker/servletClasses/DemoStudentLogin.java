package com.clicker.servletClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.global.AakashClickerGlobal;
import com.clicker.util.AESCrypto;
import com.clicker.util.MyEncryption;

/**
 * Servlet implementation class DemoStudentLogin
 */
public class DemoStudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String StudentId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoStudentLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String MacAddress = URLDecoder.decode(request.getParameter("MacAddress"), "ISO-8859-1");
		String macAddress = request.getParameter("MacAddress");
		System.out.println("macAddress = " + macAddress);
		final String MACADDRESS = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
    	Pattern pattern = Pattern.compile(MACADDRESS);
    	Matcher matcher = pattern.matcher(macAddress);
    	if(!matcher.matches()){
    		try {
    			MyEncryption crypto = new MyEncryption();
    			macAddress = crypto.decrypt(macAddress);
    			System.out.println("macAddress = " + macAddress);
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			return;
    		}
    	}
    	Connection conn = null;
		DatabaseConnection dbconn = new DatabaseConnection();
		conn = dbconn.createDatabaseConnection();
		Statement stmt=null, st=null;
		ResultSet rs =null, rs3=null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select StudentID from student where MacAddress='"+ macAddress + "'");
			if (rs.next()) {
				StudentId = rs.getString("StudentID");				
			}else{
				System.out.println("StudentId for this mac - " + macAddress + " is not available");
				response.sendRedirect("./jsp/errorpages/Error.jsp?Error=Invalide Mac address");
				return;
			}
			HttpSession session = request.getSession(true);
			System.out.println("StudentId ------ " + StudentId);
			session.setAttribute("StudentID", StudentId);
			session.setAttribute("Usertype", "Student");
			//Statement st1 = conn.createStatement();
			//int i = st1.executeUpdate("Insert into studentcourse (Year,Semester,CourseID,StudentID) values ('2012','Spring','CSE101','"+ StudentId + "')");
			//System.out.println("Insert statement : " + i);			
			st = conn.createStatement();
			rs3 = st.executeQuery("SELECT d.DeptID, d.DeptName from student s, department d where s.StudentID = '"+ StudentId + "' and s.DeptID = d.DeptID");
			if (rs3.next()) {
				session.setAttribute("DeptID", rs3.getString("DeptID"));
				session.setAttribute("DeptName", rs3.getString("DeptName"));
			}
			if (!AakashClickerGlobal.StudentIDs.contains(StudentId)) {
				AakashClickerGlobal.StudentIDs.add(StudentId);
			}			
			//st1.close();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();			
				if(rs3!=null)rs3.close();
				if(st!=null)st.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("./jsp/home/StudentSuccess.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
