/*
 * This class is used to manage session.
 * This is useful for login and logout action of user.
 */

package com.clicker.servletClasses;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.databaseconn.DatabaseQueries;
import com.clicker.global.AakashClickerGlobal;

public class AakashSessionManager implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Session has been created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();

		String StudentID = (String) session.getAttribute("StudentID");
		String InstructorID = (String) session.getAttribute("InstructorID");

		if (StudentID != null) {
			AakashClickerGlobal.StudentIDs.remove(StudentID);
			System.out.println("Session has been destroyed for " + StudentID);
			System.out.println("Student have logged out now");
		} else if (StudentID == null) {
			System.out.println("StudentID is null");
			System.out.println("Student have logged out already");
		}

		if (InstructorID != null) {
			String CourseID = (String) session.getAttribute("InstructorCourse");
			DatabaseConnection dbconn = new DatabaseConnection();
			DatabaseQueries dbqueries = new DatabaseQueries();
			Connection conn = dbconn.createDatabaseConnection();
			dbqueries.updateCourseStatus(conn, 0, CourseID);
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (InstructorID == null) {
			System.out.println("InstructorID is null");
			System.out.println("Instructor have logged out already");
		}
	}
}