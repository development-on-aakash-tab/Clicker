package com.clicker.databaseconn;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jfree.data.category.DefaultCategoryDataset;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.clicker.util.OptionEncription;
import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;
import com.clicker.wrappers.Quiz;

public class DatabaseQueries {

	DatabaseResultSetQueries DB_rs_queries = new DatabaseResultSetQueries();

	/*	public String[] getInstructorIDCourseID(Connection conn, String InstrID) {
		String Query = "SELECT ic.CourseID from instructorcourse ic "
				+ "where ic.InstrID='" + InstrID + "'";

		ResultSet resultSet = DB_rs_queries.runResultSetQuery(conn, Query);
		String[] result = DB_rs_queries.runStringArrayQuery(conn, resultSet);
		return result;
	}

	public Vector<Quiz> getQuizIDName(Connection conn, String CourseID) {
		String Query = "SELECT QuizID, QuizName " + "FROM quiz "
				+ "WHERE CourseID='" + CourseID + "'";
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
	 */
	public String[] getQuestionIDsinQuiz(Connection conn, String QuizID) {
		String Query = "SELECT QuestionID FROM quizquestion  WHERE QuizID = '"
				+ QuizID + "'";
		ResultSet result = null;
		Statement st=null;
		String[] ResultArray = null;

		try {
			st = conn.createStatement();
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

		} catch (SQLException ex) {
			ResultArray = new String[1];
			System.out.println(ex);
		}
		finally
		{
			try {
				if(result!=null)result.close();

				if(st!=null)st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ResultArray;
	}

	public String displayQuestionText(Connection conn, String QuestionID) {

		String Query = "SELECT Question, QuestionType FROM question WHERE QuestionID= '"
				+ QuestionID + "'";
		ResultSet result = null;
		Statement st=null;
		String ResultArray = null;
		int questionType = 0;
		try {
			if (conn != null) {
				st = conn.createStatement();
				result = st.executeQuery(Query);
				if (result.next()) {
					Blob b = result.getBlob(1);
					byte[] bdata = b.getBytes(1, (int) b.length());
					ResultArray = new String(bdata);
					questionType = result.getInt("QuestionType");
				}

			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

		finally
		{
			try {
				if(result!=null)result.close();

				if(st!=null)st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

		} catch (SQLException ex) {
			optionName = null;
		}

		finally
		{
			try {
				if(result!=null)result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}		


		return optionName;
	}

	public Option getOptionDetails(Connection conn, String optionid,
			String questionid) throws SQLException {
		// String[] details = new String[6];
		Option op = new Option();
		ResultSet result=null;

		try
		{
			String Query = "select * from options where OptionID = '" + optionid
					+ "'";

			result = DB_rs_queries.runResultSetQuery(conn, Query);
			result.next();
			op.setOptionID(result.getString("OptionID"));
			op.setOptionValue(result.getString("OptionValue"));
			op.setExp(result.getString("OptionDesc"));
			op.setCorrect(result.getBoolean("OptionCorrectness"));
			op.setLOD(result.getInt("LevelofDifficulty"));
			op.setCredit(result.getFloat("Credit"));
			op.setQuestionId(questionid);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(result!=null)result.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
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

	public Vector<Question> getQuestionDetailsFromXML(String path) {
		Vector<Question> QuestionList = new Vector<Question>();
		try {
			File file = new File(path + "Quiz.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = (Document) db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList question_nlist = doc.getElementsByTagName("question");
			//String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
			for(int i=0; i<question_nlist.getLength();i++){
				Question quest = new Question();
				Element question_Element = (Element)question_nlist.item(i);
				NodeList id = question_Element.getElementsByTagName("id");
				String qid = id.item(0).getFirstChild().getNodeValue();
				quest.setQuestionID(qid);
				NodeList text = question_Element.getElementsByTagName("text");
				String qtext = text.item(0).getFirstChild().getNodeValue();
				quest.setQuestionText(qtext);
				NodeList type = question_Element.getElementsByTagName("type");
				String qtype = type.item(0).getFirstChild().getNodeValue();
				quest.setQuestionType(Integer.parseInt(qtype));
				NodeList correctans = question_Element.getElementsByTagName("correctans");
				String qcorrectans = correctans.item(0).getFirstChild().getNodeValue();
				OptionEncription crypt = new OptionEncription();
				if(qtype.equals("3")){
					qcorrectans = crypt.numberDC(qcorrectans).toString();
				}else{
					qcorrectans = crypt.charDC(qcorrectans).toString();
				}

				NodeList option_nlist = question_Element.getElementsByTagName("option");

				if(qtype.equals("3")){
					String optionvalue = option_nlist.item(0).getFirstChild().getNodeValue();
					Option op = new Option();
					op.setOptionValue(optionvalue);
					op.setCorrect(true);
					quest.addOption(op);
				}else{
					System.out.println("qcorrectans" + qcorrectans);
					for(int j=0;j<option_nlist.getLength();j++){
						Option op = new Option();
						String optionvalue = option_nlist.item(j).getFirstChild().getNodeValue();
						op.setOptionValue(optionvalue);
						System.out.println("Correct " + qcorrectans + " j " + j +" char " + Character.toString((char)(j+65)));
						if(qcorrectans.contains(Character.toString((char)(j+65)))){
							op.setCorrect(true);                		
						}else{
							op.setCorrect(false);
						}
						quest.addOption(op);            		
						//responseTable += "<tr><td>" + RemoteCenterID + "</td><td>" + qid + "</td><td>" + pid + "</td><td>" + resp + "</td></tr>";
					}
				}
				QuestionList.add(quest);            	
			}	            
		} catch (SAXException ex) {
		} catch (IOException ex) {
		} catch (ParserConfigurationException ex) {
		}
		return QuestionList;
	}

	public StringBuffer getQuizQuestions(Vector<Question> QuestionList){
		StringBuffer quizQuestions = new StringBuffer();
		for(int i=0;i<QuestionList.size();i++){
			Question quest = new Question();
			quest = QuestionList.get(i);
			quizQuestions.append(quest.getQuestionID() + "@;");
			quizQuestions.append(quest.getQuestionText() + "@;");
			quizQuestions.append(quest.getQuestionType() + "@;");
			Vector<Option> opt_list = new Vector<Option>();
			opt_list = quest.getOptions();
			for(int j=0;j<opt_list.size();j++){
				Option opt = new Option();
				opt = opt_list.get(j);
				quizQuestions.append(opt.getOptionValue() + "@,");
			}
			quizQuestions.append("@@");
		}
		return quizQuestions;
	}

	public int getQuestionTypeforQuiz(Connection conn, String QuestionID) {
		String Query = "select QuestionType from question where QuestionID='"
				+ QuestionID + "'";
		System.out.println(Query);
		int QuesTyp = DB_rs_queries.runIntQuery(conn, Query);
		return QuesTyp;
	}

	public int getQuestionTypeforQuestion(Connection conn, String Question) {
		String Query = "select QuestionType from question where Question='"
				+ Question + "'";
		System.out.println(Query);
		int QuesTyp = DB_rs_queries.runIntQuery(conn, Query);
		return QuesTyp;
	}

	/*	public String[] getStudentCourses(Connection conn, String StudentID) {
		String Query = "SELECT CourseID from studentcourse WHERE StudentID='"
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
	 */

	public String getCoordinatorID(Connection conn, String ParticipantID) {
		String Query = "SELECT c.UserName FROM coordinator c";
		String CoordinatorID = DB_rs_queries.runStringQuery(conn, Query);
		return CoordinatorID ;
	}
	public int getRemoteCenterID (Connection conn, String CoordinatorUserName)
	{
		int RemoteCenterID=0;

		String Query = "SELECT rc.CenterID from remotecenter rc, coordinator c" +
				" WHERE c.UserName = '"+CoordinatorUserName+"' " +
				"and rc.CenterID=c.CenterID" ;
		RemoteCenterID = DB_rs_queries.runIntQuery(conn, Query);		
		return RemoteCenterID;		
	}	

	public int getRemoteCenterIDForInsert(Connection conn,String RemoteCenterName, int rcid){
		int remotecenterid=0;

		String query="SELECT CenterID from remotecenter where CenterName='"+RemoteCenterName+"' and CenterID = " +rcid+"" ;
		remotecenterid=DB_rs_queries.runIntQuery(conn, query);

		return remotecenterid;
	}


	public ResultSet getRemoteCenterName(Connection conn){
		ResultSet rs=null;
		String query="SELECT CenterID, CenterName FROM remotecenter ";
		rs=DB_rs_queries.runResultSetQuery(conn, query);
		return rs;

	}

	public int getCountOfCoordinator(Connection conn){
		int count=0;

		String query="SELECT count(CoordID) FROM coordinator";
		count=DB_rs_queries.runIntQuery(conn, query);
		return count;
	}

	public boolean insertCoordinatorDetail(Connection conn,int CenterID,String Username,String Password,String email){
		Statement stmt=null;
		try {
			String query="insert into coordinator(CoordID,UserName,Password,CenterID,email) values(1,'"+Username+"','"+Password+"',"+CenterID+",'"+email+"')";
			stmt = conn.createStatement();
			stmt.execute(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally
		{
			try {
				if(stmt!=null)stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean UpdateCoordinatorDetail(Connection conn,String Username,String Password,String email){
		Statement stmt=null;

		try {
			String query="Update coordinator set UserName='"+Username+"',Password='"+Password+"',email='"+email+"' where CoordID=1";  
			stmt = conn.createStatement();
			stmt.execute(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally
		{
			try {
				if(stmt!=null)stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}


	public String getAndMatchRemoteCenterName(Connection conn) throws SQLException 
	{
		String RemoteCenterName=null;
		ResultSet rs=null;
		int RemoteCenterIDForMatch=0;
		try
		{
			String Query1="SELECT CenterID from coordinator where CoordID=1";
			RemoteCenterIDForMatch =DB_rs_queries.runIntQuery(conn, Query1);

			String Query = "SELECT CenterID, CenterName FROM remotecenter" +
					" WHERE CenterID = '"+RemoteCenterIDForMatch+"' ";
			rs = DB_rs_queries.runResultSetQuery(conn, Query);
			while(rs.next())
			{
				RemoteCenterName=rs.getString(2).toString() + " - " + rs.getString(1).toString();
			}


		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return RemoteCenterName;	

	}	

	public static void main(String args[]) {
		/*
		 * DatabaseConnection dbconn = new DatabaseConnection(); DatabaseQueries
		 * dbqueries = new DatabaseQueries(); Connection conn =
		 * dbconn.createDatabaseConnection(); dbqueries.updateCourseStatus(conn,
		 * 1, "CSE101");
		 */
	}

	public String[] getStudentCourses(Connection conn, String StudentID) {
		String Query = "SELECT CourseID from studentcourse WHERE StudentID='"
				+ StudentID + "'";
		ResultSet resultSet = DB_rs_queries.runResultSetQuery(conn, Query);
		String[] result = DB_rs_queries.runStringArrayQuery(conn, resultSet);
		return result;

	}


	public String[] getInstructorIDCourseID(Connection conn, String InstrID) {
		String Query = "SELECT ic.CourseID from instructorcourse ic "
				+ "where ic.InstrID='" + InstrID + "'";
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
}