package com.clicker.CreateQuiz;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clicker.databaseconn.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class QuizCreator extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn=null;
	private PreparedStatement statement1=null;
	private PreparedStatement statement2=null;
	private PreparedStatement statement3=null;
	private Statement statement4=null;
	private Connection conn1=null;
	private PreparedStatement statement11=null;
	private PreparedStatement statement21=null;
	private PreparedStatement statement31=null;
	private Statement statement41=null;
	int quiztype;
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
		float version=3.1f;
		//int duration_sec;
		int duration_min;		
		//int dation_hour;
		String quizName;
		String InstrID=request.getParameter("InstrID");
		String QuizType=request.getParameter("QuizTypeName");
		if(QuizType.equals("Normal")){
			quiztype=1;
			System.out.println("This is Normal Quiz.........."+quiztype);			
		}else{
			quiztype=2;
			System.out.println("This is Spot Quiz............"+quiztype);
		}
		System.out.println("This is type of Quiz : "+QuizType);
		String courseID=request.getSession().getAttribute("courseID").toString();
		int quizID = 0;
		int ctr=-1;
		int[] questionID;
		ResultSet rs=null;
		try{
			DatabaseConnection dbconn = new DatabaseConnection();
			conn= dbconn.createDatabaseConnection();
			statement1=(PreparedStatement) conn.prepareStatement("INSERT INTO quiz (Version,Duration,QuizName,CourseID,QuizType,InstrID) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement2=(PreparedStatement) conn.prepareStatement("INSERT INTO quizquestion (QuizID,QuestionID,questionCredit) VALUES (?,?,?)");
			statement3=(PreparedStatement) conn.prepareStatement("INSERT INTO quizquestionoption (QuizID,QuestionID,OptionID) VALUES (?,?,?)");
			statement4=conn.createStatement();
			quizName=request.getParameter("quizName");
			//duration_hour=Integer.parseInt(request.getParameter("durationH"));
			duration_min=Integer.parseInt(request.getParameter("durationM"));
			//duration_sec=Integer.parseInt(request.getParameter("durationS"));
			statement1.setFloat(1, version);
			//statement1.setLong(2,duration_hour*60*60+duration_min*60+duration_sec);
			statement1.setLong(2,duration_min*60);
			statement1.setString(3, quizName);
			statement1.setString(4, courseID);
			statement1.setInt(5, quiztype);
			statement1.setString(6,InstrID);
			Boolean b =statement1.execute();
			System.out.println(b);
			rs=(ResultSet) statement1.getGeneratedKeys();
			if(rs.next()){
				quizID=rs.getInt(1);
			}
			else{
				System.out.println("FAIL");
			}
			ctr=Integer.parseInt(request.getParameter("count"));
			System.out.println("Count Recieved as "+ctr );
			questionID=new int[ctr];
			for(int i=0;i<questionID.length;i++){
				System.out.println("Hidden Field "+(i+1)+" :"+request.getParameter(""+(i+1)));
				questionID[i]=Integer.parseInt(request.getParameter(""+(i+1)));
				System.out.println("questionid i="+questionID[i]);
				statement2.setInt(1, quizID);
				statement2.setInt(2,questionID[i]);
				statement2.setFloat(3, 5f);
				Boolean b2=statement2.execute();
				System.out.println(b2);
				rs=(ResultSet) statement4.executeQuery("SELECT OptionID FROM options WHERE QuestionID="+questionID[i]);
				while(rs.next()){
					statement3.setInt(1, quizID);
					statement3.setInt(2,questionID[i]);
					statement3.setInt(3, rs.getInt("OptionID"));
					Boolean b1=statement3.execute();
					System.out.println(b1);
					
				}
			}
			statement1.close();
			statement2.close();
			statement3.close();
			statement4.close();			
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("jsp/createquiz/CreateQuiz.jsp");
		} catch (IOException e) {
			 e.printStackTrace();
		}
 	}
}
