package com.clicker.raisehand;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RestoreDoubtsManager
 */
public class RestoreDoubtsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestoreDoubtsManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("getting doubts");
		@SuppressWarnings("unchecked")
		HashMap<Integer, String> sessionDoubts=(HashMap<Integer, String>) request.getSession().getAttribute("sessionDoubts");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(sessionDoubts!=null){
				for(String doubt:sessionDoubts.values()){
					out.print(doubt);
					System.out.println("Sent 1 doubt :"+doubt);
				}
			}
			else{
				System.out.println("SessionDoubts NULL");
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
		int id=Integer.parseInt(request.getParameter("doubt"));
		HashMap<Integer, String> sessionDoubts=(HashMap<Integer, String>) request.getSession().getAttribute("sessionDoubts");
		if(sessionDoubts!=null)
			sessionDoubts.remove(id);
	}

}
