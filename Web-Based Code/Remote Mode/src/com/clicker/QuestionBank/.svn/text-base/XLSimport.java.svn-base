/**
 * 
 */
package com.clicker.QuestionBank;

import java.io.File;
import java.sql.*;
import com.clicker.databaseconn.*;
import jxl.*;
import com.clicker.QuestionBank.*;


public class XLSimport {
	int questionID = -1;

	public int getQuestionID() {
		return questionID;
	}

	public int insertQuery(String query, String columns, int selector) {
		try {

			DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createDatabaseConnection();
			PreparedStatement stRemote = null;
			//Statement st = con.createStatement();
			int i = 0, qid = 0, opcorrectness = 0;
			float credits = 0;
			String delimiter = ",";
			String cols[ ] = columns.split(delimiter);
			
			if(selector == 1){
			String quest = cols[0];
			credits = Float.parseFloat(cols[i + 1]);
			int qtype = Integer.parseInt(cols[i + 2]);
			System.out.println("In question insertQuery");
			stRemote = conn1.prepareStatement(query);
			stRemote.setString(1, cols[i]);
			stRemote.setFloat(2, credits);
			stRemote.setInt(3, qtype);
			stRemote.setString(4, cols[i + 3]);
			stRemote.setString(5, cols[i + 4]);
			
			
			}
			else
			{
				opcorrectness = Integer.parseInt(cols[i + 1]);
				credits = Float.parseFloat(cols[i + 2]);
				qid = Integer.parseInt(cols[i + 3]);
				System.out.println("In options insertQuery");
				stRemote = conn1.prepareStatement(query);
				stRemote.setString(1, cols[i]);
				stRemote.setInt(2, opcorrectness);
				stRemote.setFloat(3, credits);
				stRemote.setInt(4, qid);
				
			}
			//int result = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			int result = stRemote.executeUpdate( );
			System.out.println("result = " + result);
			ResultSet rs = stRemote.getGeneratedKeys();
			if (rs.next()) {
				questionID = rs.getInt(1);
				System.out.println("insertQuery questionID: " + questionID);
			}
			if(selector == 1){
			// Adding entry in the Questions history table
			History history = new History (questionID, cols[0], cols[4]);
			history.addentry ();}
			//st.close();*/
			//stRemote.close( );
			conn1.close();
			
			return questionID;
		} catch (SQLException ex) {
			System.out.println("insert query exception: " + ex);
			return -1;
		}
	}

	public void insertQuestion(String Questiontxt, float Credit,
			int QuestionType, String ansOrder, String InstrID) {
		try {
			/*DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createDatabaseConnection();*/
			
			String qcolumns = Questiontxt + "," + Credit + "," + QuestionType + "," + ansOrder + "," + InstrID; 
			System.out.println("qcolumns: " + qcolumns);
			String sql = "Insert into question(Question, LevelofDifficulty, Archived, Credit, " +
						 "QuestionType, AnswerOrder, InstrID) values(?, 1, 0, ?, ?, ?, ?)";	
			//System.out.println("Question" + insertQuery(sql, con));
			System.out.println("Question" + insertQuery(sql, qcolumns, 1));

			//conn1.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertOptions(String OptionValue, int OptionCorrectness, float credit,
			int QuestionID) {
		try {
			/*DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createDatabaseConnection();*/
			String ocolumns = OptionValue + "," + OptionCorrectness + "," + credit + "," + QuestionID;
			String sql = "Insert into options(OptionValue, OptionCorrectness, LevelofDifficulty, Archived," +
					"Credit, QuestionID) values(?, ?, 1, 0, ?, ?)";
			//System.out.println("options" + insertQuery(sql, con));
			System.out.println("options" + insertQuery(sql, ocolumns, 2));

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readQuestionXLSFile(String instrid, File xlsfile) {
		try {
			Workbook workbook = Workbook.getWorkbook(xlsfile);
			
			// String sheetName[] = workbook.getSheetNames();
			Sheet sheet;
			Cell xlsCell;
			Cell[] cell;
			// Getting first sheet of xls
			sheet = workbook.getSheet(0);
			System.out.println("Sheet name = " + sheet.getName());
			// i start from 1 because it will avoid first row in xls sheet that
			// is (Row 1)
			for (int i = 1; i < sheet.getRows(); i++) {
				String Question = "";
				float Credit = 0.0f;
				//String instrid = "";
				cell = sheet.getRow(i);
				xlsCell = sheet.getCell(0, i);
				Question = xlsCell.getContents().toString();
				System.out.println("xls Question = " + Question);
				if (Question.equals("")) {
					break;
				}

				xlsCell = sheet.getCell(1, i);
				String cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				// This Token defines the type of questions (g - General, m -
				// Multiple, n - Numeric, t - True / False and y - Yes / No)
				String QuestinTypeToken = "gmnty";
				int QType = QuestinTypeToken.indexOf(Character.toString(
						cellvalue.charAt(0)).toLowerCase()) + 1;
				System.out.println("Question Type " + QType);
				xlsCell = sheet.getCell(2, i);
				cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				Credit = Float.parseFloat(cellvalue);

				xlsCell = sheet.getCell(3, i);
				cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				String Ans = cellvalue.toLowerCase();
				
				/*xlsCell = sheet.getCell(4, i);
				instrid = xlsCell.getContents().toString();
				System.out.println("xls instrid = " + instrid);
				 
				if (instrid.equals("")) {
					break;
				}*/
				insertQuestion(Question, Credit, QType, Ans, instrid);
				
				int QuestionID = getQuestionID(/* Question,con */);
				System.out.println("question id is " + QuestionID);
				// This will Execute for General and Multiple Questions
				if (QType == 1 || QType == 2) {
					// This Token define the Options
					String OptionToken = "abcdef";
					int OptionCorrectness = 0;
					// j start from 5 because it will take only options (from
					// Column F)
					for (int j = 4; j < cell.length; j++) {
						xlsCell = sheet.getCell(j, i);
						System.out.println(xlsCell.getContents().toString());
						String OptionValue = xlsCell.getContents().toString();
						if (OptionValue.equals("")) {
							break;
						}
						OptionCorrectness = 0;
						// Seting option correctness for General Question
						if (Ans.length() == 1) {
							if (OptionToken.indexOf(Ans) + 4 == j) {
								OptionCorrectness = 1;
							}
						}
						// Seting option correctness for Multiple Choice
						// Question
						else {
							for (int k = 0; k < Ans.length(); k++) {
								OptionCorrectness = 0;
								if (OptionToken.indexOf(Character.toString(Ans
										.charAt(k))) + 4 == j) {
									OptionCorrectness = 1;
									break;
								}
							}
						}
						insertOptions(OptionValue, OptionCorrectness, Credit, QuestionID);
					}
				}
				// It will Execute for Numeric Questions
				else if (QType == 3) {
					String OptionValue = Ans;
					insertOptions(OptionValue, 1, Credit, QuestionID);
				} // It will Execute for True / False Questions
				else if (QType == 4) {
					//char s = Ans.charAt(0);
					String OptionValue = Ans;
					insertOptions(OptionValue, 1, Credit, QuestionID);
					if (Ans.equals("true")) {
						insertOptions("false", 0, Credit, QuestionID);
					} else {
						insertOptions("true", 0, Credit, QuestionID);
					}

				}// It will Execute for Yes / No Questions
				/*else if (QType == 5) {
					String OptionValue = Ans;
					//insertOptions(OptionValue, 1, QuestionID, con);
					if (Ans.equals("yes")) {
						//insertOptions("no", 0, QuestionID, con);
					} else {
						//insertOptions("yes", 0, QuestionID, con);
					}

				}*/

			}
			return "Question uploaded  Successfully";

		} catch (NumberFormatException ex) {
			System.out.print("Wrong Credit value = " + ex);
			return "Wrong Credit value";
		} catch (Exception exec) {
			System.out.print("Exception = " + exec);
			return "Wrong File Format";
		}
	}

}
