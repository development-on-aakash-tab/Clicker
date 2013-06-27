package com.clicker.CreateQuiz;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OptionPopulator extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter out=response.getWriter();
			RetrieveOptionsModel retrieved=new RetrieveOptionsModel(Integer.parseInt(request.getParameter("questionID")));
			ArrayList<String> options=retrieved.getOptions();

			int ctr=0;
			for(String opt:options){
				if(retrieved.getQuestionType()==1||retrieved.getQuestionType()==2)
					out.print("<p class='options'>"+Character.toString((char)(65+(ctr++)))+". "+opt+"</p>");
				else if(retrieved.getQuestionType()==4)
					out.print("<p class='options'>" +Character.toString((char)(65+(ctr++)))+". "+opt+" </p>" );
			}
			
			 if(retrieved.getQuestionType()==3){
				out.print("<p class='options'>Numeric Answer : "+options.get(0)+"</p>");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
