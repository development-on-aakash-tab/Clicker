package com.clicker.util;


import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.databaseconn.DatabaseQueries;

public class CourseSetter extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		session.setAttribute("courseID", request.getParameter("courseID"));
		try {
			if(session.getAttribute("Usertype").equals("Student")){
				response.sendRedirect("jsp/studentJspPages/StudentQuizListener.jsp");
				try{
					DatabaseConnection dbconn = new DatabaseConnection();
					DatabaseQueries dbqueries = new DatabaseQueries();		
					Connection conn = dbconn.createDatabaseConnection();
					String InstructorID = dbqueries.getInstructorID(conn, request.getParameter("courseID"));
					session.setAttribute("InstructorID", InstructorID);			
					conn.close();
				}catch(Exception e){
					e.getStackTrace();
				}
			}
			else if(session.getAttribute("Usertype").equals("Instructor")){
				if(session.getAttribute("Mode").equals("Remote")){
				response.sendRedirect("./switchRaiseHand?enable=1");
				System.out.println("This is inside Course Setter");
			}
				else{
					response.sendRedirect("./switchRaiseHand?enable=1");	
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
