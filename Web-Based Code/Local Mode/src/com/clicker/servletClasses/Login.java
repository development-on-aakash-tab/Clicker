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
import com.clicker.util.aakashLogin;

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

			if (AakashClickerGlobal.StudentIDs != null) {
				if (AakashClickerGlobal.StudentIDs.contains(LoginName)) {
					response.sendRedirect("./jsp/home/MultipleStudentLoginError.jsp");
					return;
				}
			}
			/*if (AakashClickerGlobal.InstructorIDs != null) {
				if (AakashClickerGlobal.InstructorIDs.contains(LoginName)) {
					response.sendRedirect("./jsp/home/MultipleInstructorLoginError.jsp");
					return;
				}
			}*/
			String Password = request.getParameter("Password");
			String Admin=request.getParameter("admin");
			String Mode = request.getParameter("mode");
			aakashLogin login = new aakashLogin();
			boolean flagForStud = false;
			boolean flag = false;
			flag = login.checkInstructorLogin(conn, LoginName, Password);
			flagForStud = login.checkStudentLogin(conn, LoginName, Password);
			if (flag == true) {
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(3*60*60);
				String admin_priviledge = null;				
				session.setAttribute("InstructorID", LoginName);
				session.setAttribute("Usertype", "Instructor");
				Statement st = conn.createStatement();
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
					System.out.println("admin=" + admin_priviledge);
				}
				
				if (Admin != null) {					
					//session.setMaxInactiveInterval(3600);
					session.setAttribute("Mode", "Local");
					if (admin_priviledge.equals("1")){
						response.sendRedirect("./jsp/home/AdminSuccess.jsp");
						System.out.println("This is Local Admin Login");						
					}else{
						session.setAttribute("AdminMode","admin");
						response.sendRedirect("./jsp/home/InstructorSuccess.jsp");
						System.out.println("This is Local Instructor Login");
					}					
				} 
				else if(Mode != null) {	
					session.setAttribute("Mode", "Remote");
					response.sendRedirect("./jsp/home/InstructorRemoteSuccess.jsp");
					System.out.println("This is Remote Instructor Login");
				} 
				else{						
					session.setAttribute("Mode", "Local");
					response.sendRedirect("./jsp/home/InstructorSuccess.jsp");	
					System.out.println("This is Local Instructor Login");
				}
			}
			else if (flagForStud == true) {
				HttpSession session = request.getSession(true);
				session.setAttribute("StudentID", LoginName);
				session.setAttribute("Usertype", "Student");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT d.DeptID, d.DeptName from student s, department d " +
						"where s.StudentID = '"	+ LoginName + "' and s.DeptID = d.DeptID");
				if (rs.next()) {
					session.setAttribute("DeptID", rs.getString("DeptID"));
					session.setAttribute("DeptName", rs.getString("DeptName"));
				}

				if (!AakashClickerGlobal.StudentIDs.contains(LoginName)) {
					AakashClickerGlobal.StudentIDs.add(LoginName);
				}
				//session.setMaxInactiveInterval(300);
				response.sendRedirect("./jsp/home/StudentSuccess.jsp");
			} else {
				response.sendRedirect("Login.jsp?error=error");
			}
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
