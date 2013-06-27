/**
 * 
 */
package com.clicker.QuestionBank;

import java.io.File;
import java.sql.*;
import com.clicker.databaseconn.DatabaseConnection;
import jxl.*;


public class XLSimport {
	int questionID = -1;

	public int getQuestionID() {
		return questionID;
	}

	public int insertQuery(String query, Connection con) {
		try {

			DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createRemoteDatabaseConnection();
			Statement st = con.createStatement();
			Statement stRemote = conn1.createStatement();
			int result = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			stRemote.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				questionID = rs.getInt(1);
			}
			st.close();
			conn1.close();
			return result;
		} catch (SQLException ex) {
			return -1;
		}
	}

	public void insertQuestion(String Questiontxt, float Credit,
			int QuestionType, String ansOrder, Connection con) {
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createRemoteDatabaseConnection();
			String sql = "Insert into question values(null,'" + Questiontxt
					+ "', 1, 0, " + Credit + ", 0, 'null', " + QuestionType
					+ ",'" + ansOrder + "')";
			System.out.println("Question" + insertQuery(sql, con));
			System.out.println("Question" + insertQuery(sql, conn1));

			conn1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertOptions(String OptionValue, int OptionCorrectness,
			int QuestionID, Connection con) {
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection conn1 = db.createRemoteDatabaseConnection();
			String sql = "Insert into options values(null,'" + OptionValue
					+ "',null," + OptionCorrectness + ",1,0,0," + QuestionID
					+ ")";
			System.out.println("options" + insertQuery(sql, con));
			System.out.println("options" + insertQuery(sql, conn1));

			conn1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readQuestionXLSFile(File xlsfile, Connection con) {
		try {
			Workbook workbook = Workbook.getWorkbook(xlsfile);
			// String sheetName[] = workbook.getSheetNames();
			Sheet sheet;
			Cell xlsCell;
			Cell[] cell;
			// Getting first sheet of xls
			sheet = workbook.getSheet(0);
			System.out.println(sheet.getName());
			// i start from 1 because it will avoid first row in xls sheet that
			// is (Row 1)
			for (int i = 1; i < sheet.getRows(); i++) {
				String Question = "";
				float Credit = 0.0f;
				cell = sheet.getRow(i);
				xlsCell = sheet.getCell(1, i);
				Question = xlsCell.getContents().toString();
				if (Question.equals("")) {
					break;
				}

				xlsCell = sheet.getCell(2, i);
				String cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				// This Token defines the type of questions (g - General, m -
				// Multiple, n - Numeric, t - True / False and y - Yes / No)
				String QuestinTypeToken = "gmnty";
				int QType = QuestinTypeToken.indexOf(Character.toString(
						cellvalue.charAt(0)).toLowerCase()) + 1;
				System.out.println("Question Type " + cellvalue);
				xlsCell = sheet.getCell(3, i);
				cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				Credit = Float.parseFloat(cellvalue);

				xlsCell = sheet.getCell(4, i);
				cellvalue = xlsCell.getContents().toString();
				if (cellvalue.equals("")) {
					break;
				}
				String Ans = cellvalue.toLowerCase();
				insertQuestion(Question, Credit, QType, Ans, con);
				int QuestionID = getQuestionID(/* Question,con */);
				System.out.println("question id is " + QuestionID);
				// This will Execute for General and Multiple Questions
				if (QType == 1 || QType == 2) {
					// This Token define the Options
					String OptionToken = "abcdef";
					int OptionCorrectness = 0;
					// j start from 5 because it will take only options (from
					// Column F)
					for (int j = 5; j < cell.length; j++) {
						xlsCell = sheet.getCell(j, i);
						System.out.println(xlsCell.getContents().toString());
						String OptionValue = xlsCell.getContents().toString();
						if (OptionValue.equals("")) {
							break;
						}
						OptionCorrectness = 0;
						// Seting option correctness for General Question
						if (Ans.length() == 1) {
							if (OptionToken.indexOf(Ans) + 5 == j) {
								OptionCorrectness = 1;
							}
						}
						// Seting option correctness for Multiple Choice
						// Question
						else {
							for (int k = 0; k < Ans.length(); k++) {
								OptionCorrectness = 0;
								if (OptionToken.indexOf(Character.toString(Ans
										.charAt(k))) + 5 == j) {
									OptionCorrectness = 1;
									break;
								}
							}
						}
						insertOptions(OptionValue, OptionCorrectness,
								QuestionID, con);
					}
				}
				// It will Execute for Numeric Questions
				else if (QType == 3) {
					String OptionValue = Ans;
					insertOptions(OptionValue, 1, QuestionID, con);
				} // It will Execute for True / False Questions
				else if (QType == 4) {
					String OptionValue = Ans;
					insertOptions(OptionValue, 1, QuestionID, con);
					if (Ans.equals("true")) {
						insertOptions("false", 0, QuestionID, con);
					} else {
						insertOptions("true", 0, QuestionID, con);
					}

				}// It will Execute for Yes / No Questions
				else if (QType == 5) {
					String OptionValue = Ans;
					insertOptions(OptionValue, 1, QuestionID, con);
					if (Ans.equals("yes")) {
						insertOptions("no", 0, QuestionID, con);
					} else {
						insertOptions("yes", 0, QuestionID, con);
					}

				}

			}
			return "Question uploaded  Successfully";

		} catch (NumberFormatException ex) {
			System.out.print("Wrong Credit value :" + ex);
			return "Wrong Credit value";
		} catch (Exception exec) {
			System.out.print("Exception " + exec);
			return "Wrong File Format";
		}
	}

}
