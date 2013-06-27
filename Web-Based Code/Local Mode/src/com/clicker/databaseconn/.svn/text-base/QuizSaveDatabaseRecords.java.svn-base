package com.clicker.databaseconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;
import com.clicker.databaseconn.QuizRecordQueries;

public class QuizSaveDatabaseRecords {
	QuizRecordQueries qrq = new QuizRecordQueries();
	public int insertDemoQuizRecordQuestionData(Connection conn, int QuizID,
			String StudentID, String Response, Vector<Question> quest_vec, int i) {
		int latestQuizRecordID = 0;
		int QuizRecordIndex = 0;
		String QuestionID;
		String OptionID = "0";
		try {
			latestQuizRecordID = qrq.getLatestQuizRecordID(conn, QuizID);
			QuizRecordIndex = qrq.getMaximumQuizRecordsIndex(conn);
			String Query = "INSERT INTO demoquizrecordquestion (QuizRecordID,QuestionID,OptionID,StudentID,Response,QRIndex,"
					+ "InvalidQuiz,ResponseIndex,ResponseIndexCorrect) VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps;
			Question quest = (Question) quest_vec.elementAt(i);
			Vector<Option> Options = quest.getOptions();
			int ResponseLength = Response.length();
			int OptionIndex = 0;
			if (ResponseLength == 1) {
				String GenOpt = "ABCDEF";
				if (GenOpt.contains(Response) && Options.size() >= 3) {
					char ResponseFirstChar = Response.charAt(0);
					OptionIndex = GenOpt.indexOf(ResponseFirstChar);
					if (OptionIndex < Options.size()) {
						OptionID = ((Option) Options.elementAt(OptionIndex)).getOptionID();
					} else {
						OptionID = "0";
					}
				} else if (Response.equalsIgnoreCase("X")
						|| Response.equalsIgnoreCase("Z")) {
					OptionID = "0";
				}
			}
			QuestionID = quest.getQuestionID();
			ps = conn.prepareStatement(Query);
			ps.setInt(1, latestQuizRecordID);
			ps.setString(2, QuestionID);
			ps.setString(3, OptionID);
			ps.setInt(4, Integer.parseInt(StudentID));
			ps.setString(5, Response);
			ps.setInt(6, QuizRecordIndex + 1);
			ps.setBoolean(7, false);
			ps.setInt(8, OptionIndex);
			ps.setInt(9, 1);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/*public void saveQuizRecord(ConcurrentHashMap<String,Map<String,String>> AllStudentResponse, Vector <Question> Questiondetails, int quizID){
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		Set<String> studentIDs = AllStudentResponse.keySet();
		Iterator<String> iter = studentIDs.iterator();
		while (iter.hasNext()) {
			String current_sid = iter.next().toString();
			insertIndividulaStudentRecord(con,current_sid, AllStudentResponse.get(current_sid), Questiondetails, quizID);			
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void saveQuizRecord(ConcurrentHashMap<String, String> AllStudentResponse, Vector <Question> Questiondetails, int quizID){
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con=null;
		try {
			con = dbcon.createDatabaseConnection();
			Set<String> studentIDs = AllStudentResponse.keySet();
			Iterator<String> iter = studentIDs.iterator();
			while (iter.hasNext()) {
				String current_sid = iter.next().toString();
				insertIndividulaStudentRecord(con,current_sid, AllStudentResponse.get(current_sid).split("@@"), Questiondetails, quizID);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}
	}
	
	public void insertIndividulaStudentRecord(Connection con, String studentID, String[] studentResposne, Vector <Question> questiondetails, int quizID) throws SQLException{
		for(int i=0;i<questiondetails.size();i++){			
			if(i<studentResposne.length)
				insertQuizRecordQuestionData(con, quizID, studentID, studentResposne[i], questiondetails.get(i));
			else
				insertQuizRecordQuestionData(con, quizID, studentID, "Z", questiondetails.get(i));
		}		
	}
	
	public int insertQuizRecordQuestionData(Connection conn, int QuizID,
			String studentID, String Response, Question quest) throws SQLException {
		int latestQuizRecordID = 0;
		String QuestionID;
		int TimeTaken = 0;
		String OptionID = "0";
			latestQuizRecordID = qrq.getLatestQuizRecordID(conn, QuizID);
			QuestionID = quest.getQuestionID();
			String GenOpt = "ABCDEF";
			int ResponseLength = Response.length();
			Vector<Option> Options = quest.getOptions();
			int OptionIndex = 0;
			if (Response.equalsIgnoreCase("X")
					|| Response.equalsIgnoreCase("Z")) {
				OptionID = "0";
				insertRecord(conn, latestQuizRecordID, QuestionID, OptionID, studentID, Response, TimeTaken, OptionIndex);
			} else if (ResponseLength == 1 && quest.getQuestionType() == 1) {
				if (GenOpt.contains(Response) && Options.size() >= 3) {
					char ResponseFirstChar = Response.charAt(0);
					OptionIndex = GenOpt.indexOf(ResponseFirstChar);

					if (OptionIndex < Options.size()) {
						OptionID = ((Option) Options.elementAt(OptionIndex))
								.getOptionID();
					} else {
						OptionID = "0";
					}
				}
				insertRecord(conn, latestQuizRecordID, QuestionID, OptionID, studentID, Response, TimeTaken, OptionIndex);
			} else if (ResponseLength >= 1 && quest.getQuestionType() == 2) {
				for(int j=0;j<ResponseLength;j++){
					String ResponseChar= Character.toString(Response.charAt(j));					
					if (GenOpt.contains(ResponseChar) && Options.size() >= 3) {						
						OptionIndex = GenOpt.indexOf(ResponseChar);
						if (OptionIndex < Options.size()) {
							OptionID = ((Option) Options.elementAt(OptionIndex))
									.getOptionID();
						} else {
							OptionID = "0";
						}
					}
					insertRecord(conn, latestQuizRecordID, QuestionID, OptionID, studentID, ResponseChar, TimeTaken, OptionIndex);
				}
			}
			else if (quest.getQuestionType() == 3) {
				System.out.println("size of num options :" + Options.size());
				System.out.println("Response of num :" + ((Option) Options.elementAt(0)).getOptionValue());
						if (Response.equals(((Option) Options.elementAt(0)).getOptionValue())) {
							OptionID = ((Option) Options.elementAt(0))
									.getOptionID();
						} else {
							OptionID = "0";
						}
					
					insertRecord(conn, latestQuizRecordID, QuestionID, OptionID, studentID, Response, TimeTaken, OptionIndex);
				}
			else if (quest.getQuestionType() == 4) {				
				if (GenOpt.contains(Response)) {
					char ResponseFirstChar = Response.charAt(0);
					OptionIndex = GenOpt.indexOf(ResponseFirstChar);
					if (OptionIndex < Options.size()) {
						OptionID = ((Option) Options.elementAt(OptionIndex))
								.getOptionID();
					} else {
						OptionID = "0";
					}
				}
				insertRecord(conn, latestQuizRecordID, QuestionID, OptionID, studentID, Response, TimeTaken, OptionIndex);
			}		 
		return 0;
	}

	public void insertRecord(Connection conn, int latestQuizRecordID,
			String QuestionID, String OptionID, String studentID,
			String Response, int TimeTaken, int OptionIndex) throws SQLException {
		PreparedStatement ps=null;
		try {
			int QuizRecordIndex = qrq.getMaximumQuizRecordsIndex(conn);			
			String Query = "INSERT INTO quizrecordquestion (QuizRecordID,QuestionID,OptionID,StudentID,Response,QRIndex,"
					+ "InvalidQuiz,TimeTaken,ResponseIndex,ResponseIndexCorrect) VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(Query);
			ps.setInt(1, latestQuizRecordID);
			ps.setString(2, QuestionID);
			ps.setString(3, OptionID);
			ps.setString(4, studentID);
			ps.setString(5, Response);
			ps.setInt(6, QuizRecordIndex + 1);
			ps.setBoolean(7, false);
			ps.setInt(8, TimeTaken);
			ps.setInt(9, OptionIndex);
			ps.setInt(10, 1);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(ps!=null)ps.close();
		}
	}
	
	
	public void saveInstantQuizRecord(ConcurrentHashMap<String, String> InstantResponse, int iquizID){
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con=null;		
		try {
			con = dbcon.createDatabaseConnection();
			PreparedStatement pstmt = con.prepareStatement("Insert into instantquizresponse(StudentID, IQuizID, Response) values(?, ?, ?)");
			Set<String> studentIDs = InstantResponse.keySet();
			Iterator<String> iter = studentIDs.iterator();
			while (iter.hasNext()) {
				String current_sid = iter.next().toString();
				pstmt.setString(1,current_sid);
				pstmt.setInt(2, iquizID);
				pstmt.setString(3, InstantResponse.get(current_sid));
				pstmt.executeUpdate();		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}
	}
}
