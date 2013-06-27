package com.clicker.servletClasses;

import java.io.IOException;
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
import com.clicker.global.AakashClickerGlobal;
import com.clicker.util.MyEncryption;

/**
 * Servlet implementation class DemoParticipantLogin
 */
public class AndroidParticipantLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String ParticipantId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidParticipantLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String MacAddress = request.getParameter("MacAddress");
		System.out.println("mac " + MacAddress);
		final String MACADDRESS = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
    	Pattern pattern = Pattern.compile(MACADDRESS);
    	Matcher matcher = pattern.matcher(MacAddress);
    	if(!matcher.matches()){
    		try {
    			MyEncryption crypto = new MyEncryption();
    			MacAddress = crypto.decrypt(MacAddress);
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			return;
    		}
    	}	
		Connection conn = null;
		DatabaseConnection dbconn = new DatabaseConnection();
		conn = dbconn.createDatabaseConnection();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select ParticipantID from participant where MacAddress='"+ MacAddress + "'");
			if (rs.next()) {
				ParticipantId = rs.getString("ParticipantID");
			}else{
				System.out.println("StudentId for this mac - " + MacAddress + " is not available");
				response.sendRedirect("./jsp/errorpages/Error.jsp?Error=Invalide Mac address");
				return;
			}
			System.out.println("Participant........" + ParticipantId);
			System.out.println("MACADDRESS........" + MacAddress);

			HttpSession session = request.getSession(true);
			session.setAttribute("ParticipantID", ParticipantId);
			AakashClickerGlobal.ParticipantIDs.add(ParticipantId);
			response.sendRedirect("./jsp/home/ParticipantSuccess.jsp");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(stmt!=null){stmt.close();}	
				if(rs !=null)rs.close();
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
		// TODO Auto-generated method stub
	}

}
