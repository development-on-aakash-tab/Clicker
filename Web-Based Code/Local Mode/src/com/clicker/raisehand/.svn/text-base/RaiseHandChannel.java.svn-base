package com.clicker.raisehand;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RaiseHandChannel
 */
public class RaiseHandChannel extends HttpServlet {
	
	private boolean sendResponse=false;
	private String msgbody;
	private String courseIdStudent=null;
	private String studentId;
	private HashMap<Integer, String> sessionDoubts=null;
	static int counter=0;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RaiseHandChannel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String doubt;
		try {
			PrintWriter out=response.getWriter();
			//System.out.println("StudentCID:"+courseIdStudent+",InstructorCID"+request.getSession().getAttribute("courseID")+",bools:"+((courseIdStudent!=null)?courseIdStudent.equals(request.getSession().getAttribute("courseID")):"")+","+sendResponse);
		
			if(sendResponse&&courseIdStudent.equals(request.getSession().getAttribute("courseID"))){
				sendResponse=false;//argument to removeDoubt will be based on the id of the div ..decide a criteria to give the doubt a unique id
				HashMap<Integer, String> attribute = (HashMap<Integer, String>) request.getSession().getAttribute("sessionDoubts");
				sessionDoubts=attribute;
			    if(sessionDoubts==null){
			    	sessionDoubts=new HashMap<Integer, String>();
			    	request.getSession().setAttribute("sessionDoubts",sessionDoubts);
			    }			    
				synchronized (getServletContext()) {
				  System.out.println(msgbody);
			      out.print("<div id='"+(++counter)+"' class='doubt'><b>"+"StudentID : "+studentId+"<b><br><p style='padding-top:10px;'>"+msgbody+"</p><br/>");//courseid,studentid for the raise hand table																																																																																																																																																																																																																																																																																																																																																											
			      out.print("<input type='hidden' id='studentID"+counter+"' value='"+studentId+"'/>");
				  out.print("<input type='hidden' id='courseID"+counter+"' value='"+courseIdStudent+"'/>");
				  out.print("<input type='hidden' id='comments"+counter+"' value='"+msgbody+"'/>");
				  out.print("<input type='button' value='Save Doubt' onclick=\"saveDoubt('"+counter+"')\"/>&nbsp&nbsp<input type='button' value='Delete' onclick=\"removeDoubt('"+counter+"')\"/><br/></div>");//onclick(button1)=goes to The servlet that saves the data through ajax onclick(button2) removes the div and these buttons
				  doubt = "<div id='"+(counter)+"' class='doubt'><b>"+"StudentID : "+studentId+"<b><br><p style='padding-top:10px;'>"+msgbody+"</p><br/>"+"<input type='hidden' id='studentID"+counter+"' value='"+studentId+"'/>"+"<input type='hidden' id='courseID"+counter+"' value='"+courseIdStudent+"'/>"+"<input type='hidden' id='comments"+counter+"' value='"+msgbody+"'/>"+"<input type='button' value='Save Doubt' onclick=\"saveDoubt('"+counter+"')\"/>&nbsp&nbsp<input type='button' value='Delete' onclick=\"removeDoubt('"+counter+"')\"/><br/></div>";
				  sessionDoubts.put(counter, doubt);
				  System.out.println("Session Doubts : "+sessionDoubts);
				  System.out.println("Entered into sidebar");
			  }
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (getServletContext()) {
			msgbody=request.getParameter("msgbody");
			studentId=request.getSession().getAttribute("StudentID").toString();
			courseIdStudent=request.getSession().getAttribute("courseID").toString();
			sendResponse=true;
		}
		try {
			response.sendRedirect("./jsp/raisehand/StudentRaiseHand.jsp?status=ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
