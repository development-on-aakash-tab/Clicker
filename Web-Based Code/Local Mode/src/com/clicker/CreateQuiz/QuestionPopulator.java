package com.clicker.CreateQuiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QuestionPopulator extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		RetrieveQuestionsModel rom=new RetrieveQuestionsModel(Integer.parseInt(request.getParameter("qtype")),request.getParameter("InstrID"));
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(request.getParameter("question")==null||request.getParameter("question").equals(""))){
			rom.setSearchParameter(request.getParameter("question"));
		}
		ArrayList<Question> allQuestions=rom.getQuestions();
		for(Question q:allQuestions){
			out.print("<option value='"+q.QuestionID+"' id='"+q.QuestionID+"'>"+q.Question+"</option>");
		}
		out.close();
	}
}
