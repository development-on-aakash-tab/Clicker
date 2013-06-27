package com.clicker.raisehand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.clicker.databaseconn.DatabaseConnection;

public class RaiseHandDBModel {
	private Connection connection;
	private Statement statement;
	private RaiseHandRowData row=null;
	private ArrayList<RaiseHandRowData> resultList=null;
	public RaiseHandDBModel(){
		connection=null;
	}
	public void setData(String courseID,String studentID,String comment,String quizRecordID,String clickerID,String raiseHandTimeStamp){
		row=new RaiseHandRowData();
		row.setCourseID(courseID);
		row.setStudentID(studentID);
		row.setComment(comment);
		row.setQuizRecordID(quizRecordID);
		row.setClickerID(clickerID);
		row.setRaiseHandTimeStamp(raiseHandTimeStamp);
	}
	public void setData(String courseID,String studentID,String comment){
		row=new RaiseHandRowData();
		row.setCourseID(courseID);
		row.setStudentID(studentID);
		row.setComment(comment);
		row.setClickerID("AakashTablet7");
	}
	
	public void insertData(){
		try {
			DatabaseConnection dbcon = new DatabaseConnection();
			connection=dbcon.createDatabaseConnection();
			statement=connection.createStatement();
			statement.execute("INSERT INTO raisehand (CourseID,StudentID,ClickerID,Comments) VALUES ('"+row.getCourseID()+"','"+row.getStudentID()+"','"+row.getClickerID()+"','"+row.getComment()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<RaiseHandRowData> retrieveData(String courseID,String text,String date){
		ResultSet rs;
		if(text==null||text.equals("")){
			text="%";
		}
		if(date==null||date.equals("All Time")){
			date="%";
		}
		try{
			DatabaseConnection dbcon = new DatabaseConnection();
			connection=dbcon.createDatabaseConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery("SELECT DISTINCT stu.StudentRollNo,stu.StudentName,stu.EmailID,stuCou.Semester,rh.Comments,rh.RaiseTimeStamp FROM student as stu,studentcourse as stuCou,raisehand as rh WHERE rh.StudentID=stu.StudentID AND rh.StudentID=stuCou.StudentID AND rh.CourseID='"+courseID+"'"+"AND rh.Comments LIKE '%"+text+"%' AND rh.RaiseTimeStamp LIKE '%"+date+"%' ORDER BY rh.RaiseTimeStamp DESC" );
			RaiseHandRowData data;
			resultList=new ArrayList<RaiseHandRowData>();
			while(rs.next()){
				data=new RaiseHandRowData();
				data.setRollNo(rs.getString("stu.StudentRollNo"));
				data.setName(rs.getString("stu.StudentName"));
				data.setEmail(rs.getString("stu.EmailID"));
				data.setSemester(rs.getString("stuCou.Semester"));
				data.setComment(rs.getString("rh.Comments"));
				data.setRaiseHandTimeStamp(rs.getString("rh.RaiseTimeStamp"));
				resultList.add(data);
			}
			rs.close();
			connection.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
	public void deleteData(String raiseHandTimeStamp){
		try{
			DatabaseConnection dbcon = new DatabaseConnection();
			connection=dbcon.createDatabaseConnection();
			statement=connection.createStatement();
		    statement.execute("DELETE FROM raisehand WHERE RaiseTimeStamp='"+raiseHandTimeStamp+"'");
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
