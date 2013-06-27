package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	public String getRemoteCenterName(String coordid){
		String RCName = "";
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		Statement stmt=null;
		ResultSet rs =null;
		try {
			stmt = con.createStatement();
			rs= stmt.executeQuery("select rc.CenterName from coordinator c, remotecenter rc where c.UserName = '" + coordid + "' and rc.CenterID = c.CenterID" );
			if(rs.next()){
				RCName = rs.getString("CenterName");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return RCName;
	}
	
	public String getParticipantList(){
		String participantList = "";
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		Statement stmt=null;
		ResultSet rs =null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT ParticipantID FROM participant");
			while(rs.next()){
				participantList += rs.getString("ParticipantID") + ";";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return participantList;
	}
	
	public int deleteParticipantList(String action){
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		int updateCount =0;
		Statement stmt=null;
		try {
			stmt = con.createStatement();
			String query ="";
			if(action.equals("all")){
				query="DELETE FROM participant";
			}
			else{
				action = action.substring(0, action.lastIndexOf(','));
				query="DELETE FROM participant where ParticipantID in (" + action + ")";
			}
			updateCount = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return updateCount;
	}
}
