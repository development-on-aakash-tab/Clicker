package com.clicker.raisehand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SwitchRaiseHand
 */
public class SwitchRaiseHand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchRaiseHand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enable=Integer.parseInt(request.getParameter("enable"));
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> list=(ArrayList<String>)getServletContext().getAttribute("raisedCourses");
		System.out.println("Enable:"+enable);
		if(enable==1){
			if(list==null){
				list=new ArrayList<String>();
			}
			if(!list.contains(session.getAttribute("courseID"))){
				list.add(session.getAttribute("courseID").toString());
				getServletContext().setAttribute("raisedCourses",list);
			}
		}
		else if(enable==0){
			System.out.println("Removing...");
			if(list!=null){
				list.remove(session.getAttribute("courseID"));
			}
		}		
		try {
			if(session.getAttribute("Mode").equals("Local")){
				response.sendRedirect("jsp/home/InstructorSuccess.jsp");
			}else{
				response.sendRedirect("jsp/errorpages/Error.jsp?Error=Unauthorised Access");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
