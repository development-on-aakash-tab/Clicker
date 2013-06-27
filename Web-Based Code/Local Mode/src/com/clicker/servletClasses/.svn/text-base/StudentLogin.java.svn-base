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

/**
 * Servlet implementation class StudentLogin
 */
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentLogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = null;

		try {
			DatabaseConnection dbconn = new DatabaseConnection();
			conn = dbconn.createDatabaseConnection();

			String LoginName = request.getParameter("LoginName");

			if (AakashClickerGlobal.StudentIDs != null) {
				if (AakashClickerGlobal.StudentIDs.contains(LoginName)) {
					response.sendRedirect("MultipleStudentLoginError.jsp");
					return;
				}
			}
			com.clicker.util.aakashLogin login = new com.clicker.util.aakashLogin();

			String Password = request.getParameter("Password");

			boolean flag = false;

			flag = login.checkStudentLogin(conn, LoginName, Password);

			if (flag == true) {
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*60);				
				session.setAttribute("StudentID", LoginName);
				session.setAttribute("Usertype", "Student");
				Statement st = conn.createStatement();
				ResultSet rs = st
						.executeQuery("SELECT d.DeptID, d.DeptName from student s, department d where s.StudentID = '"
								+ LoginName + "' and s.DeptID = d.DeptID");
				if (rs.next()) {
					session.setAttribute("DeptID", rs.getString("DeptID"));
					session.setAttribute("DeptName", rs.getString("DeptName"));
				}

				if (!AakashClickerGlobal.StudentIDs.contains(LoginName)) {
					AakashClickerGlobal.StudentIDs.add(LoginName);
				}

				session.setMaxInactiveInterval(120);
				response.sendRedirect("./jsp/home/StudentSuccess.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}