package com.clicker.report;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.clicker.databaseconn.DatabaseConnection;



/**
 * This class is act as helper class for report generation, it get the detail from database 
 * @author rajavel
 *
 */
public class ReportHelper {
	
	/**
	 * This method is used to get a student details 
	 * @param sid Student id
	 * @return Student details as String
	 */
	public String getStudentInfo(String sid) {
		String studinfo = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;        
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String querystudinfo = "SELECT s.StudentName, sc.Year, sc.Semester, s.YearofJoining FROM student s, studentcourse sc where s.StudentID = '" + sid + "' and sc.StudentID = s.StudentID";
            rs = st.executeQuery(querystudinfo);
            if (rs.next()) {
                studinfo = rs.getString("StudentName") + "~" + rs.getString("Year") + "~" + rs.getString("Semester") + "~" + rs.getString("YearofJoining");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return studinfo;
	}
	
	public String getStudentList(String cid) {
		String studList = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;        
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String querystudinfo = "SELECT StudentID FROM studentcourse where CourseID = '" + cid + "'";
            rs = st.executeQuery(querystudinfo);
            if (rs.next()) {
            	studList = rs.getString("StudentID") + ";";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return studList;
	}
	
	/**
	 * This method is used to get the information of quiz id, studet id and time of attendance for a course 
	 * @param courseid Course id
	 * @return return the detail of quiz id, studet id and time of attendance
	 */
	public String getQuizStudAttenTSInfo(String courseid) {
        String optiontxt1 = "";
        String optiontxt2 = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "SELECT QuizID, QuizName from quiz where CourseID = '" + courseid + "'";
            String querystudid = "SELECT StudentID FROM studentcourse where CourseID = '" + courseid + "'";
            String queryatttimestamp = "SELECT distinct AttendanceTS FROM attendance where CourseID = '" + courseid + "'";
            rs = st.executeQuery(queryquizname);
            optiontxt1 += "<option value= 'Quiz Name'>Quiz Name</option>";
            while (rs.next()) {
                optiontxt1 += "<option value= " + rs.getString("QuizID") + ">" + rs.getString("QuizName") + "</option>";
                optiontxt2 += ";" + rs.getString("QuizID") + ";" + rs.getString("QuizName");
            }
            ResultSet rs1 = st.executeQuery(querystudid);
            optiontxt2 += "~";
            optiontxt1 += "~";
            optiontxt1 += "<option value= 'Student ID'>Student ID</option>";
            optiontxt1 += "Student ID";
            while (rs1.next()) {
                optiontxt1 += "<option value= " + rs1.getString("StudentID") + ">" + rs1.getString("StudentID") + "</option>";
                optiontxt2 += ";" + rs1.getString("StudentID");
            }
            ResultSet rs2 = st.executeQuery(queryatttimestamp);
            optiontxt2 += "~";
            optiontxt1 += "~";
            optiontxt1 += "<option value= 'Time Stamp'>Time Stamp</option>";
            while (rs2.next()) {
                optiontxt1 += "<option value= " + rs2.getString("AttendanceTS") + ">" + rs2.getString("AttendanceTS") + "</option>";
                optiontxt2 += ";" + rs2.getString("AttendanceTS");
            }
            rs1.close();
            rs2.close();
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optiontxt1 + "@#" + optiontxt2;
    }
	
	public String getDateQuizInfo(String courseid, String date, String instructorID) {
        String optiontxt1 = "";
        String optiontxt2 = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "SELECT distinct q.QuizID, q.QuizName from quiz q, quizrecord qr where CourseID = '"+courseid+"' and qr.QuizID = q.QuizID and q.InstrID = '"+instructorID+"' and date(qr.TimeStamp) = '"+date+"'";
            rs = st.executeQuery(queryquizname);
            optiontxt1 += "<option value= 'Quiz Name'>Quiz Name</option>";
            while (rs.next()) {
                optiontxt1 += "<option value= " + rs.getString("QuizID") + ">" + rs.getString("QuizName") + "</option>";
                optiontxt2 += ";" + rs.getString("QuizID") + ";" + rs.getString("QuizName");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optiontxt1 + "@#" + optiontxt2;
    }
	
	/**
	 * This method is used get the quiz conducted times for particular quiz
	 * @param quizid Quiz id
	 * @return Quiz Time stamp as string
	 */
	public String getQuizTime(String quizid) {
        String qts1 = "";
        String qts2 = "";
        Connection con= null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "select TimeStamp from quizrecord where QuizID = '" + quizid + "' order by TimeStamp DESC";
            rs = st.executeQuery(queryquizname);
            qts2 = "<option value= 'Time Stamp'>Time Stamp</option>";
            while (rs.next()) {
                qts2 += "<option value= " + rs.getString("TimeStamp") + ">" + rs.getString("TimeStamp") + "</option>";
                qts1 += ";" + rs.getString("TimeStamp");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }           
        return qts1 + "@#" + qts2;        
    }
	
	public String getQuizTime(String quizid, String date) {
        String qts1 = "";
        String qts2 = "";
        Connection con= null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "select distinct qr.TimeStamp from quizrecord qr, quizrecordquestion qrq where qr.QuizID = '"+quizid+"' and qrq.QuizRecordID = qr.QuizRecordID and date(TimeStamp) = '"+date+"' order by TimeStamp DESC";
            rs = st.executeQuery(queryquizname);
            qts2 = "<option value= 'Time Stamp'>Time Stamp</option>";
            while (rs.next()) {
                qts2 += "<option value= " + rs.getString("TimeStamp") + ">" + rs.getString("TimeStamp") + "</option>";
                qts1 += ";" + rs.getString("TimeStamp");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }           
        return qts1 + "@#" + qts2;        
    }
	
	/**
	 * This is method is used to get the Quiz response for particular quiz
	 * @param cid Course id
	 * @param qid Quiz id
	 * @param qts Quiz Time stamp
	 * @return Student response of quiz
	 */
	public String getQuizResponse(String cid, String qid, String qts){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select qrq.Response, count(*) as RCount from quiz q, quizrecord qr, quizrecordquestion qrq, options o where q.CourseID = '" + cid + "' and qr.QuizID = q.QuizID and qr.QuizID = '" +qid+ "' and qr.TimeStamp = '" +qts+ "' and qrq.QuizRecordID = qr.QuizRecordID and o.OptionID = qrq.OptionID group by qrq.Response";

            rs = st.executeQuery(query);
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return response;
	}
	
	public String getQuestionIDs(String qid){
		String questionIDs = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "SELECT QuestionID FROM quizquestion where QuizID = " +qid;
            rs = st.executeQuery(query);
            while (rs.next()) {
            	questionIDs += rs.getString("QuestionID") + "@";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
		return questionIDs;
	}
	
	/**
	 * This is method is used to get the Quiz response for particular quiz
	 * @param qstnid Question id
	 * @param qid Quiz id
	 * @param qts Quiz Time stamp
	 * @return Student response of quiz
	 */
	public String getQuestionResponse(String qstnid, String qid, String qts){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            //String query = "select qrq.Response, count(*) as RCount, q.QuestionType from quizrecord qr, quizrecordquestion qrq, options o, question q where qr.QuizID = '"+qid+"' and qr.TimeStamp = '"+qts+"' and qrq.QuizRecordID = qr.QuizRecordID and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.Response";
            String query = "Select Sresponse.Response, count(*) as RCount from (select GROUP_CONCAT(qrq.Response SEPARATOR '') as Response, qrq.StudentID, q.QuestionType from quizrecord qr, quizrecordquestion qrq, options o, question q where qr.QuizID = '"+qid+"' and qr.TimeStamp = '"+qts+"' and qrq.QuizRecordID = qr.QuizRecordID and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.StudentID order by qrq.Response) as Sresponse group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);
            st1 = con.createStatement();
            query = "SELECT o.OptionID, o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            System.out.println(query);
            rs1 = st1.executeQuery(query);
			if (rs1.first()) {
				if (Integer.parseInt(rs1.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs1.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs1.next()) {
						ASCII++;
						if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}
            System.out.println("Check 1");
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";
            	
            }
            System.out.println("Check 2");
            rs.close();
            st.close();
            rs1.close();
            st1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response + "@" + correctAnswer);
        return response + "@" + correctAnswer;
	}
	
	public String getCorrectAnswer(String qstnid){
		Connection con = null;
        Statement st = null;
        ResultSet rs1 = null;
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "SELECT o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            rs1 = st.executeQuery(query);
            System.out.println(query);
            if (rs1.first()) {
				if (Integer.parseInt(rs1.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs1.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs1.next()) {
						ASCII++;
						if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}
            st.close();
            rs1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return correctAnswer;
		
	}
	
	public String getQuestionRemoteResponse(String centerid, String qstnid, String qid, String qts){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            //String query = "select qrq.Response, count(*) as RCount, q.QuestionType from remotequizrecord qr, remotequizrecordquestion qrq, options o, question q where qr.QuizID = '"+qid+"' and qr.QuizTimeStamp = '"+qts+"' and qrq.QuizRecordID = qr.QuizRecordID and qrq.QuestionID = '"+qstnid+"' and qrq.CenterID = "+centerid+" and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.Response";
            String query = "Select Presponse.Response, count(*) as RCount from (select GROUP_CONCAT(qrq.Response SEPARATOR '') as Response, qrq.ParticipantID, q.QuestionType from remotequizrecord qr, remotequizrecordquestion qrq, options o, question q where qr.QuizID = '"+qid+"' and qr.QuizTimeStamp = '"+qts+"' and qrq.QuizRecordID = qr.QuizRecordID and qrq.QuestionID = '"+qstnid+"' and qrq.CenterID = "+centerid+" and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.ParticipantID order by qrq.Response) as Presponse group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);
            st1 = con.createStatement();
            query = "SELECT o.OptionID, o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            System.out.println(query);
            rs1 = st1.executeQuery(query);
			if (rs1.first()) {
				if (Integer.parseInt(rs1.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs1.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs1.next()) {
						ASCII++;
						if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}
            System.out.println("Check 1");
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";
            	
            }
            System.out.println("Check 2");
            rs.close();
            st.close();
            rs1.close();
            st1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response + "@" + correctAnswer);
        return response + "@" + correctAnswer;
	}
	
	public String getAllRCQuestionResponse(String qstnid, String qid, String quizRecordID){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "Select Presponse.Response, count(*) as RCount from (select GROUP_CONCAT(qrq.Response) as Response, qrq.ParticipantID, q.QuestionType from remotequizrecordquestion qrq, options o, question q where qrq.QuizRecordID = '"+quizRecordID+"' and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.ParticipantID) as Presponse group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);
            st1 = con.createStatement();
            query = "SELECT o.OptionID, o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            System.out.println(query);
            rs1 = st1.executeQuery(query);
			if (rs1.first()) {
				if (Integer.parseInt(rs1.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs1.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs1.next()) {
						ASCII++;
						if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}
            while (rs.next()) {
            	response += rs.getString("Response").replace(",", "") + "=" + rs.getString("RCount") + ";";
            	
            }
            rs.close();
            st.close();
            rs1.close();
            st1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response + "@" + correctAnswer);
        return response + "@" + correctAnswer;
	}
	
	public String getRCQuestionResponse(int centerid, String qstnid, String qid, String quizRecordID){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "Select Presponse.Response, count(*) as RCount from (select GROUP_CONCAT(qrq.Response) as Response, qrq.ParticipantID, q.QuestionType from remotequizrecordquestion qrq, options o, question q where qrq.QuizRecordID = '"+quizRecordID+"' and qrq.CenterID = "+centerid+" and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.ParticipantID) as Presponse group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);
            st1 = con.createStatement();
            query = "SELECT o.OptionID, o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            System.out.println(query);
            rs1 = st1.executeQuery(query);
			if (rs1.first()) {
				if (Integer.parseInt(rs1.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs1.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs1.next()) {
						ASCII++;
						if (Integer.parseInt(rs1.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}
            while (rs.next()) {
            	response += rs.getString("Response").replace(",", "") + "=" + rs.getString("RCount") + ";";
            	
            }
            rs.close();
            st.close();
            rs1.close();
            st1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response + "@" + correctAnswer);
        return response + "@" + correctAnswer;
	}
	
	public String getRCInstantResponse(int centerid, String qid){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "Select Response, count(*) as RCount from remoteinstantquizresponse where CenterID = "+centerid+" and IQuizID= "+qid+" group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);            
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";            	
            }
            rs.close();
            st.close();            
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response);
        return response;
	}
	public String getRCInstantResponse(String qid){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "Select Response, count(*) as RCount from remoteinstantquizresponse where IQuizID= "+qid+" group by Response";
            System.out.println(query);
            rs = st.executeQuery(query);            
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";            	
            }
            rs.close();
            st.close();            
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(response);
        return response;
	}
	
	public String getQuestionCorrectness(String qstnid){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;        
        String correctAnswer = "";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();            
            String query = "SELECT o.OptionID, o.OptionCorrectness, o.OptionValue, q.QuestionType FROM options o, question q where o.QuestionID = "+qstnid+" and q.QuestionID = o.QuestionID order by o.OptionID";
            System.out.println(query);
            rs = st.executeQuery(query);
			if (rs.first()) {
				if (Integer.parseInt(rs.getString("QuestionType")) == 3) {
					if (Integer.parseInt(rs.getString("OptionCorrectness")) == 1) {
						correctAnswer = rs.getString("OptionValue");
					}
				}
				else {
					int ASCII = 65;
					if (Integer.parseInt(rs.getString("OptionCorrectness")) == 1) {
						correctAnswer += Character.toString((char) ASCII)+ ";";
					}
					while (rs.next()) {
						ASCII++;
						if (Integer.parseInt(rs.getString("OptionCorrectness")) == 1) {
							correctAnswer += Character.toString((char) ASCII) + ";";
						}
					}
				}				
			}                 
            rs.close();
            st.close();           
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(correctAnswer);
        return correctAnswer;
	}
	
	/**
	 * This method is used to get the result information for a quiz
	 * @param cid Course id
	 * @param qid Quiz id
	 * @param qts Quiz Time stamp
	 * @return Student result as percentage
	 */
	public String getQuizResult(String cid, String qid, String qts){
		String result = "";
		String grade = "";
		String grades = "";
		String topPrecentage = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            double topScore = 1.0;
            Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select max(p.Percentage) as topScore from (select sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100 as Percentage from " +
					"(select qrq.StudentID, qst.Credit, if((count(*) = sum(o.OptionCorrectness) and count(*) in (SELECT count(*) FROM " +
					"options oo where oo.QuestionID = qst.QuestionID and oo.OptionCorrectness = 1) ),1,0) as correct " +
					"from quiz q, quizrecord qr, quizrecordquestion qrq, student s, options o, question qst, course c " +
					"where q.CourseID= '"+cid+"' and c.CourseID = q.CourseID and qr.QuizID = q.QuizID and qr.QuizID = "+qid+" and qr.TimeStamp = '"+qts+"' " +
					"and qrq.QuizRecordID = qr.QuizRecordID and s.StudentID=qrq.StudentID  and o.OptionID = qrq.OptionID and qst.QuestionID = qrq.QuestionID " +
					"group by s.StudentID, qst.QuestionID order by s.StudentID, qst.QuestionID) sq group by sq.StudentID) p");
			if(rs1.next()){
				topPrecentage=rs1.getString("topScore");
				topScore = Double.parseDouble(rs1.getString("topScore"));
			}            
			
            st = con.createStatement();            
            String query = "select sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100 as Percentage, " +
            		"if((sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100) / "+topScore+" * 100 >=91,'A', " +
            		"if((sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100) / "+topScore+" * 100 >=71, 'B'," +
            		"if((sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100) / "+topScore+" * 100 >=51,'C', " +
            		"if((sum(sq.Credit * sq.correct) / sum(sq.Credit) * 100) / "+topScore+" * 100 >=41,'D', 'F')))) as Grade " +
            		"from (select s.StudentID, qst.Credit, if((count(*) = sum(o.OptionCorrectness) and count(*) in " +
            		"(SELECT count(*) FROM options oo where oo.QuestionID = qst.QuestionID and oo.OptionCorrectness = 1) ),1,0) as correct " +
            		"from quiz q, quizrecord qr, quizrecordquestion qrq, student s, options o, question qst, course c, department d " +
            		"where q.CourseID= '"+cid+"' and c.CourseID = q.CourseID and qr.QuizID = q.QuizID and qr.QuizID = "+qid+" and qr.TimeStamp = '"+qts+"'" +
            		"and qrq.QuizRecordID = qr.QuizRecordID and s.StudentID=qrq.StudentID  and o.OptionID = qrq.OptionID and qst.QuestionID = qrq.QuestionID " +
            		"and d.DeptID = c.DeptID group by s.StudentID, qst.QuestionID order by s.StudentID, qst.QuestionID) sq group by sq.StudentID";
            //String query = "select round(sum(qst.Credit *o.OptionCorrectness) / sum(qst.Credit) * 100, 2) as Percentage from quiz q, quizrecord qr, quizrecordquestion qrq, student s, options o, question qst, course c where q.CourseID= '" +cid+ "' and c.CourseID = q.CourseID and qr.QuizID = q.QuizID and qr.QuizID = '" +qid+ "' and qr.TimeStamp = '" +qts+ "' and qrq.QuizRecordID = qr.QuizRecordID and s.StudentID=qrq.StudentID  and o.OptionID = qrq.OptionID and qst.QuestionID = qrq.QuestionID group by s.StudentID";
            System.out.println(query);
            rs = st.executeQuery(query);
            int p_below50 = 0;
            int p_50to70 = 0;
            int p_70to90 = 0;
            int p_above90 = 0;
            int grade_a = 0;
            int grade_b = 0;
            int grade_c = 0;
            int grade_d = 0;
            int grade_f = 0;
            float persentage;
            while (rs.next()) {
            	persentage = Float.parseFloat(rs.getString("Percentage"));
            	grade += rs.getString("Grade") +";";
            	System.out.println("Percentage :" +persentage);
            	if (persentage < 50){
            		p_below50++;
            	}
            	else if(persentage < 70){
            		p_50to70++;
            	}
            	else if(persentage < 90){
            		p_70to90++;
            	}
            	else if(persentage >= 90){
            		p_above90++;
            	}
            	
            	grade = rs.getString("Grade");
            	if (grade.equalsIgnoreCase("A")){
            		grade_a++;
            	}
            	else if(grade.equalsIgnoreCase("B")){
            		grade_b++;
            	}
            	else if(grade.equalsIgnoreCase("C")){
            		grade_c++;
            	}
            	else if(grade.equalsIgnoreCase("D")){
            		grade_d++;
            	}else if(grade.equalsIgnoreCase("F")){
            		grade_f++;
            		
            	}
            }
            result = "0 to 50=" +p_below50 + ";50 to 70=" + p_50to70 + ";70 to 90=" + p_70to90 + ";90 to 100=" + p_above90;
            grades = "A="+grade_a + ";B="+grade_b +";C="+grade_c +";D="+grade_d +";F="+grade_f ;
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return result + "@" +grades +"@"+ topPrecentage;
	}
	
	/**
	 * This is method is used to get the student attendance detail for a course 
	 * @param cid Course id
	 * @param ats Attendance Time stamp
	 * @return Student Attendance details
	 */
	public String getAttendance(String cid, String ats){
		String attendance = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "Select distinct S.StudentID, if(A.Attendance_Flag=0,'Absent','Present') as Attendance_Flag from student S, attendance A where A.StudentID = S.StudentID and A.CourseID= '" +cid+ "' and A.AttendanceTS = '" +ats+ "'";
            System.out.println(query);
            rs = st.executeQuery(query);
            int present = 0;
            int absent = 0;
            String flag;
            while (rs.next()) {
            	flag = rs.getString("Attendance_Flag");
            	System.out.println("Percentage :" +flag);
            	if (flag.equals("Present")){
            		present++;
            	}
            	else {
            		absent++;
            	}            	
            }
            attendance = "Absent=" + absent + ";Present=" +present;
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return attendance;
	}
	
	public String getAttendanceInfo(String cid, String date){
		String attendance = "";
		String attendanceOptions = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;        
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "SELECT distinct AttendanceTS FROM attendance where CourseID = '"+cid+"' and Date(AttendanceTS) = '" +date+ "' order by AttendanceTS";
            System.out.println(query);
            rs = st.executeQuery(query);
            while (rs.next()) {            	
            	 attendance = "," + getStudentInfo("AttendanceTS");
            	 attendanceOptions += "<option value= " + rs.getString("AttendanceTS") + ">" + rs.getString("AttendanceTS") + "</option>";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return attendance +"@#"+ attendanceOptions;
	}
	public String getQuizList(String date) {
        String optiontxt1 = "";
        String optiontxt2 = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "SELECT distinct rqr.QuizID, q.QuizName FROM remotequizrecord rqr, quiz q where date(rqr.QuizTimestamp) = '"+date+"' and q.QuizID= rqr.QuizID";
            rs = st.executeQuery(queryquizname);
            optiontxt1 += "<option value= 'Quiz Name'>Quiz Name</option>";
            while (rs.next()) {
                optiontxt1 += "<option value= " + rs.getString("QuizID") + ">" + rs.getString("QuizName") + "</option>";
                optiontxt2 += ";" + rs.getString("QuizID") + ";" + rs.getString("QuizName");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optiontxt2 + "@#" + optiontxt1;
    }
	
	public String getQuizTime1(String quizid, String date) {
        String qts1 = "";
        String qts2 = "";
        Connection con= null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "select distinct rqr.QuizTimeStamp from remotequizrecord rqr, remotequizrecordquestion rqrq where rqr.QuizID = '" + quizid + "' and rqrq.QuizRecordID = rqr.QuizRecordID and date(rqr.QuizTimeStamp) = '"+date+"' order by rqr.QuizTimeStamp DESC";
            rs = st.executeQuery(queryquizname);
            qts2 = "<option value= 'Time Stamp'>Time Stamp</option>";
            while (rs.next()) {
                qts2 += "<option value= " + rs.getString("QuizTimeStamp") + ">" + rs.getString("QuizTimeStamp") + "</option>";
                qts1 += ";" + rs.getString("QuizTimeStamp");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }           
        return qts1 + "@#" + qts2;        
    }
	
	public String getRemoteCenterList(String quizid, String ts){
		String qts1 = "";
        String qts2 = "";
        Connection con= null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String queryquizname = "SELECT distinct rc.CenterID, rc.CenterName FROM remotequizrecord rqr, remotequizrecordquestion rqrq, remotecenter rc where rqr.QuizID = "+quizid+" and rqr.QuizTimestamp = '"+ts+"' and rqrq.QuizRecordID = rqr.QuizRecordID and rc.CenterID = rqrq.CenterID";
            rs = st.executeQuery(queryquizname);
            qts2 = "<option value= 'Select Center'>Select Center</option>";
            while (rs.next()) {
                qts2 += "<option value= " + rs.getString("CenterID") + ">" + rs.getString("CenterName") + "</option>";
                qts1 += rs.getString("CenterID")+ "$" + rs.getString("CenterName")+ ";";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }           
        return qts1 + "@#" + qts2;
	}
	
	public String getRemoteQuizResponse(String centerid, String qid, String qts){
		String response = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select qrq.Response, count(*) as RCount from remotequizrecord qr, remotequizrecordquestion qrq, options o where qr.QuizID = "+qid+" and qr.QuizTimeStamp = '"+qts+"' and qrq.QuizRecordID = qr.QuizRecordID and o.OptionID = qrq.OptionID and qrq.CenterID = "+centerid+" group by qrq.Response";

            rs = st.executeQuery(query);
            while (rs.next()) {
            	response += rs.getString("Response") + "=" + rs.getString("RCount") + ";";
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return response;
	}
	
	public String getCourseName(String courseID){
		String courseName = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select CourseName from course where CourseID = '" + courseID + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
            	courseName = rs.getString("CourseName");
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return courseName;
	}
	
	public String getCalendarDate(String courseID, String Datefrom, String instructorID){
		String calendarDate = "";
		if(Datefrom.equals("quizCondectedDate")){
			calendarDate = getDates("SELECT distinct Date(qr.TimeStamp) as TimeStamp FROM quiz q, quizrecord qr, quizrecordquestion qrq where q.CourseID = '"+courseID+"' and q.InstrID = '"+instructorID+"' and qr.QuizID = q.QuizID and qrq.QuizRecordID = qr.QuizRecordID group by qr.QuizRecordID order by qr.TimeStamp", "TimeStamp");
		}
		else if(Datefrom.equals("attendanceTakenDate")){
			calendarDate = getDates("SELECT distinct Date(AttendanceTS) as AttendanceTS FROM attendance where CourseID = '" +courseID+ "' order by AttendanceTS", "AttendanceTS");
		}
		else if(Datefrom.equals("raiseHandDate")){
			calendarDate = getDates("SELECT distinct Date(RaiseTimeStamp) as RaiseHandDate FROM raisehand where CourseID = '"+courseID+"' order by RaiseTimeStamp", "RaiseHandDate");
		}
		return calendarDate;
	}
	
	public String getDates(String query, String fieldName){
		String dates = "", startDate="", endDate = "";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			DatabaseConnection dbcon = new DatabaseConnection();
			con = dbcon.createDatabaseConnection();
			st = con.createStatement();
			rs = null;
			rs = st.executeQuery(query);
			boolean dateflag = false;
			while (rs.next()) {
				dateflag=true;
				String date = rs.getString(fieldName);			
				dates += date +",";				
			}
			if(!dateflag){
				java.util.Date dt = new java.util.Date();
				String sdf = new SimpleDateFormat("yyyy-MM-dd").format(dt);				
				startDate = sdf;
				endDate = sdf;				
			}else{
				if (rs.first()) {
					startDate = rs.getString(fieldName);						
				}
				if (rs.last()) {
					endDate = rs.getString(fieldName);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return dates +"@"+ startDate +"@"+endDate;
	}
	public String getStudentIDs(String courseID){
		String studentIDs= "";
		try {
			DatabaseConnection dbcon = new DatabaseConnection();
			Connection con = dbcon.createDatabaseConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT StudentID FROM studentcourse where CourseID = '" + courseID + "'");
			while (rs.next()) {					
				studentIDs += rs.getString("StudentID") + ",";		
			}			
			rs.close();
			st.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentIDs;
	}
	public String getCourseIDs(String userType, String userID, String deptID){
		String copurseIDs= "";
		try {
			DatabaseConnection dbcon = new DatabaseConnection();
			Connection con = dbcon.createDatabaseConnection();
			Statement st = con.createStatement();
			ResultSet rs = null;			
			if (userType.equals("Instructor")) {
				rs = st.executeQuery("SELECT CourseID FROM course where DeptID = '"
						+ deptID + "'");
			} else {
				rs = st.executeQuery("SELECT CourseID FROM studentcourse where StudentID = '"
						+ userID + "'");
			}
			while (rs.next()) {					
				copurseIDs += rs.getString("CourseID") + ",";		
			}			
			rs.close();
			st.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return copurseIDs;
	}
	
}
