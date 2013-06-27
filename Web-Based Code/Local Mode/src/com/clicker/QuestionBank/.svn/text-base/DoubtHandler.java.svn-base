package com.clicker.QuestionBank;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clicker.databaseconn.DatabaseConnection;

/**
 * Servlet implementation class DoubtHandler
 */
@WebServlet("/DoubtHandler")
public class DoubtHandler extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
     String request_students;  
     int count;
     Thread t;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoubtHandler() {
        super();
        // TODO Auto-generated constructor stub
        request_students="";
        count=12;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			DatabaseConnection dbcon = new DatabaseConnection();
			Connection conn=dbcon.createDatabaseConnection();
			Statement pst=conn.prepareStatement("Insert into raisehand values('"+1+"','"+1+"','"+1+"','"+1+"','"+new Timestamp(new java.util.Date().getTime())+"','"+request.getParameter("doubt").toString()+"','"+0+"','"+count+"')");
			int affected=pst.executeUpdate("Insert into raisehand values('"+1+"','"+1+"','"+1+"','"+1+"','"+new Timestamp(new java.util.Date().getTime())+"','"+request.getParameter("doubt").toString()+"','"+0+"','"+count+"')");
			count++;
			System.out.println("Affected Rows:"+affected);
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		pw.println("Welcome Instructor");

        Thread t=new Thread(this);
        t.start();
	}
	public void run()
	{
		Vector<String> v=new Vector<String>();
		while(true)
		{
			try{
				ResultSet rs=getRaicehand();
				while(rs.next())
				{
					System.out.println(rs.getString(2));
					v.add(rs.getString(1));
				}				
				updateRaisehand(v);
			}catch(Exception e)
			{
				System.out.println("Error:"+e);
				e.printStackTrace();
			}
		}		
		
	}
	
	public ResultSet getRaicehand(){
		Connection con=null;
		ResultSet rs = null;
		try {
			DatabaseConnection dbcon = new DatabaseConnection();
			con=dbcon.createDatabaseConnection();		
			PreparedStatement pstmt = null;			
			pstmt = con.prepareStatement("select raisehandID,Comments from raisehand where CourseID='"+1+"' and new='"+0+"'");
			rs = pstmt.executeQuery("select raisehandID,Comments from raisehand where CourseID='"+1+"' and new='"+0+"'");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateRaisehand(Vector<String> v){
		try{
			DatabaseConnection dbcon = new DatabaseConnection();
			Connection con=dbcon.createDatabaseConnection();
			PreparedStatement pstmt = null;
			pstmt=con.prepareStatement("update raisehand set new='"+1+"' where raisehandID = ?");
			for(int i=0;i<v.size();i++)
			{
				pstmt.setString(1,v.get(i).toString());
				pstmt.executeUpdate();
			}
			con.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	

