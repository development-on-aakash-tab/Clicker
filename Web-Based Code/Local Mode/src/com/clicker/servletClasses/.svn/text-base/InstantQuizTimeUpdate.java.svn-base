package com.clicker.servletClasses;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.QuizSaveDatabaseRecords;
import com.clicker.wrappers.Question;

/**
 * Servlet implementation class InstantQuizTimeUpdate
 */
public class InstantQuizTimeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ServletContext application=null;
	//HttpSession session=null;
	//Thread checkIdle;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstantQuizTimeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response){
    	response.setHeader("pragma", "no-cache,no-store");  
    	response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
    	response.setContentType("text/event-stream;charset=UTF-8");
    	PrintWriter out = null;
    	ServletContext application=null;
    	HttpSession session=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	session = request.getSession();
    	String InstructorID = (String) session.getAttribute("InstructorID");
    	application = this.getServletContext();
    	if (InstructorID == null) {
    		request.setAttribute("Error", "Your session has expired.");
    		RequestDispatcher rd = request.getRequestDispatcher("../../jsp/instructorJspPages/InstructorError.jsp");
    		try {
				rd.forward(request, response);
	    		return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	String reqfor =  request.getParameter("optiontime").toString();
    	application.setAttribute(session.getAttribute("courseID").toString() + "optiontime", request.getParameter("optiontime"));
    	System.out.println(request.getParameter("optiontime"));	
    	if(reqfor.split(":")[1].equals("00") && reqfor.split(":")[2].equals("0")){    		
    		String CourseID = session.getAttribute("courseID").toString();		
    	    try {	
    	    	  int count = 0, newcount =0, idle=0;
    	         while (true) {	        	
    	            newcount = Integer.parseInt(application.getAttribute(CourseID + "InstantResponseCount").toString());
    	            if(count != newcount){
    	            	count = newcount;
    	            	idle=0;	            	      	
    	            }
    	            idle++;	            
    	            if(idle>=3){
    	            	QuizSaveDatabaseRecords saveQuizRecord = new QuizSaveDatabaseRecords();
    	            	ConcurrentHashMap <String, String> InstantResponse = (ConcurrentHashMap<String, String>) application.getAttribute(CourseID + "InstantResponse");	
    	            	int iquizID = Integer.parseInt(session.getAttribute("iquizid").toString());
    	            	saveQuizRecord.saveInstantQuizRecord(InstantResponse, iquizID);
    	            	System.out.println(" ------------- Instant Response is stored in database successfully ---------- ");
    	            	break;
                	}
    	            Thread.sleep(1000);	 
    	         }
    	      } catch (Exception e) {
    	         System.out.println("Stored Thread stopped");}    		
    	}    	
    	/*else if(requestfrom.equals("client") && application.getAttribute(InstructorID+"minutes")!=null){
    			String minutes = application.getAttribute(InstructorID+"minutes").toString();
    			String seconds =  application.getAttribute(InstructorID+"seconds").toString();
    			out.print("data: " + minutes + ":" +seconds + "\n\n");
    			out.flush();
    	}else {
    		out.print("data: Wait...");
    		out.flush();
    	}*/
    }
    
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,	 response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,	 response);
	}

}