package com.clicker.servletClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.clicker.databaseconn.*;
import com.clicker.global.AakashClickerGlobal;
import com.clicker.util.AakashLogin;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		try {
			DatabaseConnection dbconn = new DatabaseConnection();
			conn = dbconn.createDatabaseConnection();
			DatabaseQueries dbqueries = new DatabaseQueries();
			String LoginName = request.getParameter("LoginName");
			String Password = request.getParameter("Password");
			String mode = request.getParameter("mode");
			AakashLogin login = new AakashLogin();
			boolean flag = false;
			if(mode!=null){
				flag = login.checkCoordinatorLogin(conn, LoginName, Password);
				if (flag == true) {				
					HttpSession session = request.getSession(true);
					session.setAttribute("CoordinatorID", LoginName);
					session.setMaxInactiveInterval(3600);
					session.setAttribute("Mode", "Remote");
					response.sendRedirect("./jsp/home/CoordinatorSuccess.jsp");
					System.out.println("This is Remote Coordinator Login");
				}		
				else {
					response.sendRedirect("Login.jsp?error=error");
				}
			}else{
				flag = login.checkInstructorLogin(conn, LoginName, Password);
				if(flag==true){
					HttpSession session = request.getSession(true);
					session.setMaxInactiveInterval(60*60);					
					String admin_priviledge = null;				
					session.setAttribute("InstructorID", LoginName);
					session.setAttribute("Usertype", "Instructor");
					Statement st = conn.createStatement();
					System.out.println("0000 000 " + LoginName);
					String[] Courses = dbqueries.getInstructorIDCourseID(conn,	LoginName);
					session.setAttribute("courseList", Courses);
					ResultSet rs = st.executeQuery("SELECT d.DeptID, d.DeptName,i.AdminPriviledges " +
							"from instructor i, department d where i.InstrID = '"	+ LoginName + "' and i.DeptID = d.DeptID ");
					if (rs.next()) {
						session.setAttribute("DeptID", rs.getString("DeptID"));
						session.setAttribute("DeptName", rs.getString("DeptName"));
						if (!AakashClickerGlobal.InstructorIDs.contains(LoginName)) {
							AakashClickerGlobal.InstructorIDs.add(LoginName);
						}
						admin_priviledge = rs.getString("i.AdminPriviledges");
						System.out.println("admin_priviledge=" + admin_priviledge);
						session.setAttribute("Mode", "Local");
						response.sendRedirect("./jsp/home/InstructorSuccess.jsp");	
						System.out.println("This is Local Instructor Login");
					}
				}else{
					response.sendRedirect("Login.jsp?error=error");
				}
			}
						
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			try {
				if(conn!=null)conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
