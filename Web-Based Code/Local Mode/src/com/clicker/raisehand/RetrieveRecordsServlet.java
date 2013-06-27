package com.clicker.raisehand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetrieveRecordsServlet
 */
public class RetrieveRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveRecordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<RaiseHandRowData> list=new RaiseHandDBModel().retrieveData(request.getSession().getAttribute("courseID").toString(),request.getParameter("text"),request.getParameter("date"));
		request.setAttribute("records", list);
		System.out.println("RECORDS:"+list);
		RequestDispatcher rd=null;
		if((request.getParameter("text")==null)&&(request.getParameter("date")==null)){
			rd=request.getRequestDispatcher("./jsp/raisehand/SavedDoubts.jsp");
			//rd.forward( request, response );
			System.out.println("SaveDoubts...");
		}
		else{
			rd=request.getRequestDispatcher("./jsp/raisehand/FilteredDoubts.jsp");
			System.out.println("Retrieve");
		}
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
