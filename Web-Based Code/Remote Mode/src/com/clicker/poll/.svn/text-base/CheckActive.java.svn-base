package com.clicker.poll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckActive
 */
public class CheckActive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckActive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * This will check whether the course is active or not when teacher/instructor presses any tab and comes back to poll tab.
		 */
		HttpSession session=request.getSession(false);
		if(session.getAttribute("active")!=null && session.getAttribute("active").equals("true"))
		{
			PrintWriter pw=response.getWriter();
			double curtime=new java.util.Date().getTime();
			if((30-(curtime-Double.parseDouble(((session.getAttribute("TimeStamp")).toString())))/1000)>0)
				pw.print(session.getAttribute("active").toString()+"~"+session.getAttribute("Question").toString()+"~"+Math.round((15-(30-(curtime-Double.parseDouble(((session.getAttribute("TimeStamp")).toString())))/1000)/2)));
			else
			{
				pw.print(session.getAttribute("active").toString()+"~"+session.getAttribute("Question").toString()+"~"+(15-(30-(curtime-Double.parseDouble(((session.getAttribute("TimeStamp")).toString())))/1000)/2));
			}
		}
		else
		{
			PrintWriter pw=response.getWriter();
			pw.print("false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		session.setAttribute("active", "false");
		session.removeAttribute("Question");
		session.removeAttribute("Results");
		session.removeAttribute("TimeStamp");
		System.out.println("Previous Attributes removed");
	}

}
