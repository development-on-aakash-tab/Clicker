package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseResultSetQueries {
	public ResultSet runResultSetQuery(Connection conn, String Query) {
		ResultSet result = null;
		try {
			if (conn != null) {
				Statement st = conn.createStatement();
				result = st.executeQuery(Query);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}

	public String runStringQuery(Connection conn, String Query) {
		String result = null;
		ResultSet res;
		try {
			Statement st = conn.createStatement();
			res = st.executeQuery(Query);
			res.next();
			result = res.getString(1);

		} catch (Exception ex) {
		}
		return result;
	}

	public String[] runStringArrayQuery(Connection conn, ResultSet result) {
		String[] ResultArray;
		try {
			result.last();
			int n = result.getRow();
			result.beforeFirst();

			ResultArray = new String[n];
			if (n != 0) {
				int i = 0;
				while (result.next()) {
					ResultArray[i] = result.getString(1);
					i++;
				}

			}
		} catch (Exception ex) {
			ResultArray = null;
		}
		return ResultArray;
	}
	
	public int[] runIntArrayQuery(Connection conn, ResultSet result) {
		int[] ResultArray;
		try {
			result.last();
			int n = result.getRow();
			result.beforeFirst();

			ResultArray = new int[n];
			if (n != 0) {
				int i = 0;
				while (result.next()) {
					ResultArray[i] = result.getInt(1);
					i++;
				}

			}
		} catch (Exception ex) {
			ResultArray = null;
		}
		return ResultArray;
	}

	public int runIntQuery(Connection con, String query) {
		int result = 0;
		ResultSet rs;

		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}