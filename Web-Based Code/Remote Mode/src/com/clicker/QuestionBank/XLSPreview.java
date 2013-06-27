package com.clicker.QuestionBank;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@SuppressWarnings("serial")
public class XLSPreview extends HttpServlet {
	 
	 public String readQuestionXLSFile(PrintWriter out, File xlsfile) {
	        try {
	        	//PrintWriter out = new PrintWriter(xlsfile);
	        	System.out.println("XLS Preview Filename: " + xlsfile.getPath());
	            Workbook workbook = Workbook.getWorkbook(xlsfile);
	            //String sheetName[] = workbook.getSheetNames();
	            Sheet sheet;
	            Cell xlsCell;
	            Cell[] cell;
	            // Getting first sheet of xls
	            sheet = workbook.getSheet(0);
	            // i starts from 1 because it will avoid first row in xls sheet that is (Row 1)
	            for (int i = 1; i < sheet.getRows(); i++) {
	                String Question = "";
	                String instrid = "";
	                float Credit = 0.0f;
	                cell = sheet.getRow(i);
	                xlsCell = sheet.getCell(0, i);
	                Question = xlsCell.getContents().toString();
	                if (Question.equals("")) {
	                    break;
	                }

	                xlsCell = sheet.getCell(1, i);
	                String cellvalue = xlsCell.getContents().toString();
	                if (cellvalue.equals("")) {
	                    break;
	                }
	                //This Token defines the type of questions (g - General, m - Multiple, n - Numeric, t - True / False and y - Yes / No)
	                String QuestinTypeToken = "gmnty";
	                int QType = QuestinTypeToken.indexOf(Character.toString(cellvalue.charAt(0)).toLowerCase()) + 1;
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
					
	                //if(i == 0)
	                	//out.print("<br><br><hr width='50%' align='center'><br>");
	                out.print("<br><br><b>"+(i) +".</b> "+Question+"<br>");
	                
	                
	                // This will Execute for General and Multiple Questions
	                if (QType == 1 || QType == 2) {
	                    // This Token define the Options
	                    String OptionToken = "abcdef";
	                    int OptionCorrectness = 0;
	                    char ch='a';
	                    // j start from 5 because it will take only options (from Column F)
	                    for (int j = 4; j < cell.length; j++) {
	                        xlsCell = sheet.getCell(j, i);
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
	                        // Seting option correctness for Multiple Choice Question
	                        else {
	                            for (int k = 0; k < Ans.length(); k++) {
	                                OptionCorrectness = 0;
	                                if (OptionToken.indexOf(Character.toString(Ans.charAt(k))) + 4 == j) {
	                                    OptionCorrectness = 1;
	                                    break;
	                                }
	                            }
	                        }
	                        out.print("<br>"+"("+(ch++)+")&nbsp&nbsp");
	                        if(OptionCorrectness==1){
	                        	out.print("<label style='color: green'>"+OptionValue+"</label>");
	                        }
	                        else{
	                        	out.print("<label style='color: red'>"+OptionValue+"</label>");
	                        }
	    	                
	                    }
	                } 
	                // It will Execute for Numeric Questions
	                else if (QType == 3) {                    
	                        String OptionValue = Ans;
	                        out.print("<br><label style='color: green'>Correct Answer</label> : "+OptionValue);
	                } // It will Execute for True / False Questions
	                else if (QType == 4) {
	                    if (Ans.equals("true")) {
	                        out.println("<br><label style='color: green'>Correct Answer</label> : True");
	                    } else {
	                        out.println("<br><label style='color: green'>Correct Answer</label> : False");
	                    }

	                }// It will Execute for Yes / No Questions
	                /*else if (QType == 5) {
		                    if (Ans.equals("yes")) {
		                       out.println("<br><label style='color: green'>Correct Answer</label> : Yes");
		                    } else {
		                       out.println("<br><label style='color: green'>Correct Answer</label> : No");
		                    }

	                }*/
	                out.print("<br><br><b>Question Type&nbsp&nbsp</b> : ");
	                switch(QType){
	                case 1:out.println("Single Correct Answer");
	                	break;
	                case 2:out.println("Multiple correct answer");
	                	break;
	                case 3:out.println("Numeric Answer");
	                	break;
	                case 4:out.println("True/False");
	                	break;
	                /*case 5:out.println("Yes or No");
	                	break;*/
	                }
	                out.printf("<br><b>Question Credit</b> : %.2f\n",Credit); 
	            }
	            return "Question uploaded  Successfully";

	        } catch (NumberFormatException ex) {
	            System.out.print("Wrong Credit value :" + ex);
	            return "Wrong Credit value";
	        } catch (Exception exec) {
	            System.out.print("Exception: " + exec);
	            return "Wrong File Format";
	        }
	 }
	 protected void doGet(HttpServletRequest request,HttpServletResponse response){
		 response.setContentType("text/html");
		 String url = request.getParameter("xls");
		 ServletContext context = request.getServletContext();
		String pathurl = context.getRealPath("/uploads");
		
		File file = new File(pathurl + "/" + url);
		System.out.println("Filename: " + file.getPath());
		 PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("---------------------------- " + getServletContext().getInitParameter("file"));
		 readQuestionXLSFile(out, file);
		 out.close();
	 }
	    
}
