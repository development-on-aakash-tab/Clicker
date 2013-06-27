package com.clicker.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AakashLogin {

	public boolean checkCoordinatorLogin(Connection conn, String CoordinatorID,
			String Password) {
		Statement stmt=null;
		ResultSet rs=null;
		try {
			
			stmt = conn.createStatement();
			 rs = stmt.executeQuery("Select * from coordinator where UserName = '" + CoordinatorID + "'");
			String DBCoordinatorName, DBPassword;
			if (rs.next()) {
				DBCoordinatorName = rs.getString("UserName");
				DBPassword = rs.getString("Password");
				if (CoordinatorID.equals(DBCoordinatorName)
						&& Password.equals(DBPassword) && !Password.equals("")) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkParticipantLogin(Connection conn, String ParticipantID,
			String Password) {
		Statement stmt=null;
		ResultSet rs=null;
		try {
			
			stmt = conn.createStatement();
			 rs = stmt.executeQuery("Select * from participant");
			String DBParticipantName;
			while (rs.next()) {
				DBParticipantName = rs.getString("ParticipantID");
				if (ParticipantID.equals(DBParticipantName)) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return false;
	}
	
	public boolean checkInstructorLogin(Connection conn, String InstructorID, String Password) {
		try {
			Statement stmt;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from instructor where InstrID = '"+ InstructorID + "'");
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
	
}
