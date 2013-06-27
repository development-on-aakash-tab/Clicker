package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseResultSetQueries {
	public ResultSet runResultSetQuery(Connection conn, String Query) {
		ResultSet result = null;
		Statement st = null;
		try {
			if (conn != null) {
				st = conn.createStatement();
				result = st.executeQuery(Query);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return result;
	}

	public String runStringQuery(Connection conn, String Query) {
		String result = null;
		ResultSet res=null;
		Statement st=null;
		try {
			 st= conn.createStatement();
			res = st.executeQuery(Query);
			res.next();
			result = res.getString(1);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally
		{
			try {
				if(res!=null)res.close();
				if(st!=null)st.close();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	public String[] runStringArrayQuery(Connection conn, ResultSet result) {
		String[] ResultArray = null;
		StringBuffer st = new StringBuffer();
		try{
			while(result.next()){
				st.append(result.getString(1) + ";");
			}
			ResultArray = st.toString().split(";");
		}catch(SQLException ex){
			ex.getStackTrace();
		}
		return ResultArray;
	}

	public int runIntQuery(Connection con, String query) {
		int result = 0;
		ResultSet rs=null;;
		Statement st=null;;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}
}