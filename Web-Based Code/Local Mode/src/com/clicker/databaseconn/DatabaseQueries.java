/*
 * This class is related to database queries specially for Quiz
 */

package com.clicker.databaseconn;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;
import com.clicker.wrappers.Quiz;

public class DatabaseQueries {

	DatabaseResultSetQueries DB_rs_queries = new DatabaseResultSetQueries();

	public String[] getInstructorIDCourseID(Connection conn, String InstrID) {
		String Query = "SELECT ic.CourseID from instructorcourse ic "
				+ "where ic.InstrID='" + InstrID + "'";

		ResultSet resultSet = DB_rs_queries.runResultSetQuery(conn, Query);
		String[] result = DB_rs_queries.runStringArrayQuery(conn, resultSet);
		return result;
	}

	public Vector<Quiz> getQuizIDName(Connection conn, String CourseID, String InstrID) {
		String Query = "SELECT QuizID, QuizName " + "FROM quiz "
				+ "WHERE CourseID='" + CourseID + "' and QuizType=1 and Archived = 0 and InstrID='"+InstrID+"'";
		ResultSet rs = DB_rs_queries.runResultSetQuery(conn, Query);
		Vector<Quiz> quizDetails = new Vector<Quiz>();
		try {
			while (rs.next()) {
				String quizID = rs.getString("QuizID");
				String quizName = rs.getString("QuizName");
				Quiz quiz = new Quiz();
				quiz.setquizID(quizID);
				quiz.setQuizName(quizName);
				quizDetails.addElement(quiz);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return quizDetails;
	}
	
	public Vector<Quiz> getRemoteQuizIDName(Connection conn, String CourseID) {
		String Query = "SELECT QuizID, QuizName " + "FROM quiz "
				+ "WHERE CourseID='" + CourseID + "' and QuizType=1";
		ResultSet rs = DB_rs_queries.runResultSetQuery(conn, Query);
		Vector<Quiz> quizDetails = new Vector<Quiz>();
		try {
			while (rs.next()) {
				String quizID = rs.getString("QuizID");
				String quizName = rs.getString("QuizName");
				Quiz quiz = new Quiz();
				quiz.setquizID(quizID);
				quiz.setQuizName(quizName);
				quizDetails.addElement(quiz);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return quizDetails;
	}
	
	public Vector<Quiz> getSpotQuizIDName(Connection conn, String CourseID,String InstrID) {
		String Query = "SELECT QuizID, QuizName " + "FROM quiz "
				+ "WHERE CourseID='" + CourseID + "' and QuizType=2 and Archived = 0 and InstrID='"+InstrID+"'";
		ResultSet rs = DB_rs_queries.runResultSetQuery(conn, Query);
		Vector<Quiz> quizDetails = new Vector<Quiz>();
		try {
			while (rs.next()) {
				String quizID = rs.getString("QuizID");
				String quizName = rs.getString("QuizName");
				Quiz quiz = new Quiz();
				quiz.setquizID(quizID);
				quiz.setQuizName(quizName);
				quizDetails.addElement(quiz);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return quizDetails;
	}

	public int getQuizID(Connection conn, String QuizName) {
		int QuizID = 0;
		String Query = "SELECT QuizID FROM quiz WHERE QuizName='" + QuizName
				+ "'";
		ResultSet result = null;

		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeQuery(Query);

			while (result.next()) {
				QuizID = result.getInt("QuizID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return QuizID;
	}
	
	
	public int getQuestionTypeforQuestion(Connection conn, String Question) {
		String Query = "select QuestionType from question where Question='"
				+ Question + "'";
		System.out.println(Query);
		int QuesTyp = DB_rs_queries.runIntQuery(conn, Query);
		return QuesTyp;
	}

	public String[] getQuestionIDsinQuiz(Connection conn, String QuizID) {
		String Query = "SELECT QuestionID FROM quizquestion  WHERE QuizID = '"
				+ QuizID + "'";
		ResultSet result = null;
		String[] ResultArray = null;

		try {
			Statement st = conn.createStatement();
			result = st.executeQuery(Query);
			int i = 0;
			int count = 0;
			while (result.next()) {
				count++;
			}
			ResultArray = new String[count];
			result = st.executeQuery(Query);
			while (result.next()) {

				ResultArray[i] = result.getString(1);
				i++;
			}
		} catch (Exception ex) {
			ResultArray = new String[1];
			System.out.println(ex);
		}
		return ResultArray;
	}

	public String displayQuestionText(Connection conn, String QuestionID) {

		String Query = "SELECT Question, QuestionType FROM question WHERE QuestionID= '"
				+ QuestionID + "'";
		ResultSet result = null;
		String ResultArray = null;
		int questionType = 0;
		try {
			if (conn != null) {
				Statement st = conn.createStatement();
				result = st.executeQuery(Query);
				if (result.next()) {
					Blob b = result.getBlob(1);
					byte[] bdata = b.getBytes(1, (int) b.length());
					ResultArray = new String(bdata);
					questionType = result.getInt("QuestionType");
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return ResultArray + "@@" + questionType;
	}

	public String[] getQuestionOptionIDs(Connection conn, String QuestionID,
			String QuizID) {
		// This needs to be updated in the future to reflect LOD
		String Query = "select OptionID " + "from options "
				+ "where OptionID in "
				+ "(select OptionID from quizquestionoption "
				+ "where QuestionID='" + QuestionID + "' " + "AND QuizID='"
				+ QuizID + "')";

		String[] optionName;
		ResultSet result;
		result = DB_rs_queries.runResultSetQuery(conn, Query);

		try {
			System.out.println("Debugging rows at start" + result.getRow());
			result.last();
			System.out.println("Debugging rows at last" + result.getRow());
			int n = result.getRow();
			result.beforeFirst();
			System.out.println("Debugging rows at beforeFirst"
					+ result.getRow());
			optionName = new String[n];

			int i = 0;
			while (result.next()) {
				optionName[i] = result.getString(1);
				i++;
			}
		} catch (Exception ex) {
			optionName = null;
		}
		return optionName;
	}

	public Option getOptionDetails(Connection conn, String optionid,
			String questionid) throws SQLException {
		// String[] details = new String[6];
		Option op = new Option();
		String Query = "select * from options where OptionID = '" + optionid
				+ "'";

		ResultSet result = DB_rs_queries.runResultSetQuery(conn, Query);
		result.next();
		op.setOptionID(result.getString("OptionID"));
		op.setOptionValue(result.getString("OptionValue"));
		op.setExp(result.getString("OptionDesc"));
		op.setCorrect(result.getBoolean("OptionCorrectness"));
		op.setLOD(result.getInt("LevelofDifficulty"));
		op.setCredit(result.getFloat("Credit"));
		op.setQuestionId(questionid);

		return op;
	}

	public Vector<Question> getallQuestionDetails(Connection conn, String QuizID) {
		Vector<Question> QuestionList = new Vector<Question>();

		String QuestionIDs[] = getQuestionIDsinQuiz(conn, QuizID);
		String QuestionText_Type="";
		String QuestionText="";
		for (int i = 0; i < QuestionIDs.length; i++) {
			QuestionText_Type = displayQuestionText(conn, QuestionIDs[i]);
			QuestionText = QuestionText_Type.split("@@")[0];
			//QuestionText = QuestionText.replace("\r\n", " ").replace("\n", " ").replace("\"", "\\\"").replace("\'", "\\\'");
			int questionType = Integer.parseInt(QuestionText_Type.split("@@")[1]);
			String QuestionOptionsIDs[] = getQuestionOptionIDs(conn,QuestionIDs[i], QuizID);
			Question quest = new Question();						
			quest.setQuestionID(QuestionIDs[i]);
			quest.setQuestionText(QuestionText);
			quest.setQuestionType(questionType);
			for (int j = 0; j < QuestionOptionsIDs.length; j++) {
				try {
					Option opt = getOptionDetails(conn, QuestionOptionsIDs[j], QuestionIDs[i]);
					quest.addOption(opt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			QuestionList.addElement(quest);
		}
		return QuestionList;
	}
	
	public int getQuestionTypeforQuiz(Connection conn, String QuestionID) {
		String Query = "select QuestionType from question where QuestionID='"
				+ QuestionID + "'";
		System.out.println(Query);
		int QuesTyp = DB_rs_queries.runIntQuery(conn, Query);
		return QuesTyp;
	}

	public String[] getStudentCourses(Connection conn, String StudentID) {
		String Query = "SELECT CourseID from studentcourse WHERE StudentID='"
				+ StudentID + "'";
		ResultSet resultSet = DB_rs_queries.runResultSetQuery(conn, Query);
		String[] result = DB_rs_queries.runStringArrayQuery(conn, resultSet);
		return result;

	}
	
	
	public String[] getDemoStudentCourses(Connection conn, String StudentID) {
		String Query = "SELECT CourseID from demostudentcourse WHERE StudentID='"
				+ StudentID + "'";
		ResultSet resultSet = DB_rs_queries.runResultSetQuery(conn, Query);
		String[] result = DB_rs_queries.runStringArrayQuery(conn, resultSet);
		return result;
	}

	public String getInstructorID(Connection conn, String CourseID) {
		String Query = "SELECT InstrID FROM instructorcourse WHERE CourseID='"
				+ CourseID + "'";
		String InstructorID = DB_rs_queries.runStringQuery(conn, Query);
		return InstructorID;
	}

	public boolean updateCourseStatus(Connection conn, int Status,
			String CourseID) {
		String Query = "UPDATE course SET Status=" + Status
				+ " WHERE CourseID='" + CourseID + "'";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(Query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int checkActiveStatusCourse(Connection conn, String CourseID) {
		int status = 0;
		String Query = "SELECT Status from course " + "WHERE CourseID = '"
				+ CourseID + "'";
		status = DB_rs_queries.runIntQuery(conn, Query);
		return status;
	}
	
	public int getParticipantID (Connection conn, String ParticipantUserName)
	{
		System.out.println("Participant User Name is ##################### "+ParticipantUserName);
		int ParticipantID=0;		
		String Query = "SELECT ParticipantID FROM participant WHERE UserName='"+ParticipantUserName+"'";
		ParticipantID = DB_rs_queries.runIntQuery(conn, Query);		
		return ParticipantID;		
	}	
}
