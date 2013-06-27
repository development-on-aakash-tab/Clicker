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
 * Servlet implementation class QuizTimeUpdate
 */
public class QuizTimeUpdate extends HttpServlet //implements Runnable 
{
	private static final long serialVersionUID = 1L;
	String test;
	//ServletContext application=null;
	//HttpSession session=null;
	Thread checkIdle;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizTimeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    
    protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response){
    	response.setHeader("pragma", "no-cache,no-store");  
    	response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
    	//response.setContentType("text/event-stream;charset=UTF-8");
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
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	String requestfrom =  request.getParameter("requestfrom").toString();

    	if(requestfrom.equals("server")){
    	String minutes = request.getParameter("minutes").toString();
    	String seconds = request.getParameter("seconds").toString();
    	application.setAttribute(InstructorID+"minutes", minutes);
    	application.setAttribute(InstructorID+"seconds", seconds);
    	if(minutes.equals("0") && seconds.equals("0")){    		
    		String cid= request.getParameter("cid").toString();
    		application.removeAttribute("SSE"+cid);
    		System.out.println("Stoped !!!!");
    		//checkIdle = new Thread(this);
    		//checkIdle.start();
    		
    		String CourseID = session.getAttribute("courseID").toString();
    		System.out.println("Course ID = " + CourseID);
    	      try {	
    	    	  int count = 0, newcount =0, idle=0;
    	         while (true) {	        	
    	            newcount = Integer.parseInt(application.getAttribute(CourseID + "StudentResponseCount").toString());
    	            if(count != newcount){
    	            	count = newcount;
    	            	idle=0;	            	      	
    	            }
    	            idle++;	            
    	            if(idle>=3){
    	            	QuizSaveDatabaseRecords saveQuizRecord = new QuizSaveDatabaseRecords();
    	            	//String InstructorID = (String) session.getAttribute("InstructorID");
    	            	System.out.println("InstrID = " + InstructorID);
    	            	Vector <Question> Questiondetails = (Vector <Question>) application.getAttribute(InstructorID+"Questiondetails");
    	            	System.out.println("Question Details = " + Questiondetails);
    	            	ConcurrentHashMap <String, String> AllStudentResponse = (ConcurrentHashMap<String, String>) application.getAttribute(CourseID + "AllStudentResponse");	
    	            	System.out.println("Student response = " + AllStudentResponse);
    	            	int quizID = application.getAttribute(InstructorID+"QuizID")==null?0:Integer.parseInt(application.getAttribute(InstructorID+"QuizID").toString());
    	            	System.out.println("Quiz Id = " + quizID);
    	            	saveQuizRecord.saveQuizRecord(AllStudentResponse, Questiondetails, quizID);
    	            	System.out.println(" ------------- Response is stored in database successfully ---------- ");
    	            	break;
                	}
    	            Thread.sleep(1000);	 
    	         }
    	      } catch (Exception e) {
    	         System.out.println("Stored Thread stopped");}
    	   
    	}
    	}
    	else if(requestfrom.equals("client") && application.getAttribute(InstructorID+"minutes")!=null){
    			String minutes = application.getAttribute(InstructorID+"minutes").toString();
    			String seconds =  application.getAttribute(InstructorID+"seconds").toString();
    			out.print("data: " + minutes + ":" +seconds + "\n\n");
    			out.flush();
    	}else {
    		out.print("data: Wait...");
    		out.flush();
    	}
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
	

	/*public void run() {
		
		//ServletContext application=null;
		//HttpSession session=null;
		System.out.println("In run method");
		String CourseID = session.getAttribute("courseID").toString();
		System.out.println("Course ID = " + CourseID);
	      try {	
	    	  int count = 0, newcount =0, idle=0;
	         while (true) {	        	
	            newcount = Integer.parseInt(application.getAttribute(CourseID + "StudentResponseCount").toString());
	            if(count != newcount){
	            	count = newcount;
	            	idle=0;	            	      	
	            }
	            idle++;	            
	            if(idle>=3){
	            	QuizSaveDatabaseRecords saveQuizRecord = new QuizSaveDatabaseRecords();
	            	String InstructorID = (String) session.getAttribute("InstructorID");
	            	System.out.println("InstrID = " + InstructorID);
	            	Vector <Question> Questiondetails = (Vector <Question>) application.getAttribute(InstructorID+"Questiondetails");
	            	System.out.println("Question Details = " + Questiondetails);
	            	ConcurrentHashMap <String, String> AllStudentResponse = (ConcurrentHashMap<String, String>) application.getAttribute(CourseID + "AllStudentResponse");	
	            	System.out.println("Student response = " + AllStudentResponse);
	            	int quizID = application.getAttribute(InstructorID+"QuizID")==null?0:Integer.parseInt(application.getAttribute(InstructorID+"QuizID").toString());
	            	System.out.println("Quiz Id = " + quizID);
	            	saveQuizRecord.saveQuizRecord(AllStudentResponse, Questiondetails, quizID);
	            	System.out.println(" ------------- Response is stored in database successfully ---------- ");
	            	break;
            	}
	            Thread.sleep(1000);	 
	         }
	      } catch (Exception e) {
	         System.out.println("Stored Thread stopped");}
	   }*/
	
}
class CollectResponse extends Thread{
	/**
	 * 
	 */
	String test;
	//ServletContext sc;
	public CollectResponse(String st){
		test =st;
	}
	public void run() {
	      try {	
	    	  int count = 0;
	         while (true) {	        	
	            System.out.println(test + "Thread working...");
	            //System.out.println(sc.getAttribute("StudentResponseCount").toString());
	            Thread.sleep(2000);	 
	            count++;
	            if(count>=3){	            	
	            	break;
	            }
	         }
	      } catch (InterruptedException e) {
	         System.out.println("Thread stopped");}
	   }
}

class StoreResponse extends Thread{
	/**
	 * 
	 */
	String test;
	//ServletContext sc;
	ConcurrentHashMap <String, String> AllStudentResponse;
	Vector <Question> Questiondetails;
	int quizID;
	public StoreResponse(String st){
		test =st;
	}
	public StoreResponse(ConcurrentHashMap <String, String> Responses, Vector <Question> questiondetails, int quizID){
		AllStudentResponse = Responses;
		Questiondetails =questiondetails;
		this.quizID = quizID;
	}
	public void run() {
	      try {	
	    	  int count = 0;
	         while (true) {	        	
	            System.out.println(test + "Thread working...");
	            //System.out.println(sc.getAttribute("StudentResponseCount").toString());
	            Thread.sleep(2000);	 
	            count++;
	            if(count>=3){	            	
	            	break;
	            }
	         }
	      } catch (InterruptedException e) {
	         System.out.println("Thread stopped");}
	   }
}
