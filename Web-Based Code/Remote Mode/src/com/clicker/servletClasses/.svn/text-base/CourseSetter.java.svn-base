package com.clicker.servletClasses;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.databaseconn.DatabaseQueries;

/**
 * Servlet implementation class CourseSetter
 */
public class CourseSetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					response.sendRedirect("./switchRaiseHand?enable=1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
