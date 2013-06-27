package com.clicker.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class aakashLogin {

	public boolean checkInstructorLogin(Connection conn, String InstructorID,
			String Password) {
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from instructor where InstrID = '"
							+ InstructorID + "'");

			String DBInstructorName, DBPassword;

			if (rs.next()) {
				DBInstructorName = rs.getString("InstrID");
				DBPassword = rs.getString("Password");

				if (InstructorID.equals(DBInstructorName)
						&& Password.equals(DBPassword) && !Password.equals("")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean checkStudentLogin(Connection conn, String StudentID,
			String Password) {
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from student");
			String DBStudentName, DBPassword;

			while (rs.next()) {
				DBStudentName = rs.getString("StudentID");
				DBPassword = rs.getString("Password");

				if (StudentID.equals(DBStudentName)
						&& Password.equals(DBPassword) && !Password.equals("")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
