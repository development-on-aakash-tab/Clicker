package com.clicker.servletClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.util.MyEncryption;

/**
 * Servlet implementation class ParticipantLoginVerification
 */
public class ParticipantLoginVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String responseVal;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantLoginVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){    	
    	String enrollmentID = request.getParameter("EnrollmentID");
    	HttpSession session = request.getSession();
    	session.setAttribute("enrollmentID", enrollmentID);
    	String macAddress = request.getParameter("MacAddress");
    	String ipAddress = request.getParameter("IPAddress");
    	int flag = Integer.parseInt(request.getParameter("Flag"));
    	final String MACADDRESS = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
    	Pattern pattern = Pattern.compile(MACADDRESS);
    	Matcher matcher = pattern.matcher(macAddress);
    	if(!matcher.matches()){
    		try {
    			MyEncryption crypto = new MyEncryption();
    			enrollmentID = crypto.decrypt(enrollmentID);
    			macAddress = crypto.decrypt(macAddress);  
    			session.setAttribute("macAddress", macAddress);
    			//ipAddress = AESCrypto.decrypt(ipAddress);
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			return;
    		}
    	}
    	try{
    	PrintWriter out = response.getWriter();
		if (flag == 1) // check decision value if it is 1.
		{
			responseVal = insertRecord(enrollmentID, macAddress, ipAddress);// call insertRecord() method.			
			out.print(responseVal);			
		} else if (flag == 3) {
			responseVal = checkRecord(macAddress);
			out.print(responseVal);
		}
    	}catch (IOException e) {
			e.getStackTrace();
		}
    }
    
	private String checkRecord(String macAddress) {
		// TODO Auto-generated method stub
		DatabaseConnection dbConn = new DatabaseConnection();
		Connection conn = dbConn.createDatabaseConnection();
		String result = "";
		Statement st=null;
		ResultSet rs=null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM participant WHERE MacAddress ='" +macAddress+ "'");
			if(rs.next()){
				result = "Connect";
			}else{
				result = "Registration";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private String insertRecord(String enrollmentID, String macAddress, String ipAddress) {
		DatabaseConnection dbConn = new DatabaseConnection();
		Connection conn = dbConn.createDatabaseConnection();
		String result = "No";
		
		Statement st=null;
		ResultSet rs=null;
		
		Statement st1=null;
		ResultSet rs1=null;
		
		Statement st2=null;
		ResultSet rs2=null;
		
		Statement st3=null;
		
		try {
			 st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM participant WHERE  ParticipantID = '"+enrollmentID+"' && MacAddress = '" + macAddress + "'");
			if(rs.next()){
				result =  "QuizPage";
			}else{
				 st1 = conn.createStatement();
				 rs1 = st1.executeQuery("SELECT * FROM participant WHERE  MacAddress = '"+macAddress+"'");
				if(rs1.next()){
					if(!rs1.getString("ParticipantID").equals(enrollmentID)){
						result = "No";
					}					
				}else{
					st2 = conn.createStatement();
					rs2 = st2.executeQuery("SELECT * FROM participant WHERE  ParticipantID = '" +enrollmentID+ "'");
					if(rs2.next()){
						result = "Duplicate";
					}else{
						st3 = conn.createStatement();
						st3.executeUpdate("INSERT INTO participant (ParticipantID, MacAddress, IPAddress) VALUES ('"+enrollmentID+"','"+macAddress+"', '"+ipAddress+"')");
						result = "QuizPage";
						
					}
					/*
					if(rs2.next()){
						if(rs2.getString("MacAddress").length()>1){
							result = "Duplicate";
						}	
						else{
							Statement st3 = conn.createStatement();
							st3.executeUpdate("Update participant set MacAddress = '"+macAddress+"' where ParticipantID = '"+enrollmentID+"'");
							result = "QuizPage";
							st3.close();
						}
					}else{
						result = "No";
					}
					*/
									
				}
				
			}
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(st!=null)st.close();
				if(rs!=null)rs.close();
				
				if(st1!=null)st1.close();
				if(rs1!=null)rs1.close();
				
				if(st2!=null)st2.close();
				if(rs2!=null)rs2.close();
				
				if(st3!=null)st3.close();
				
				conn.close();	
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
		
		
		return result;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
