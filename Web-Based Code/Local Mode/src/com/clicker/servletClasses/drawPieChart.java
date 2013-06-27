package com.clicker.servletClasses;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * References
 * 1. Draw Pie chart
 *      http://stackoverflow.com/questions/2663915/generate-jfreechart-in-servlet
 *
 */
/**
 * This Class is used to draw the response as pie chart
 * 
 * @author rajavel, rishab
 */
public class drawPieChart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String allResponses = request.getParameter("PieResponseString");
		//String allResponses = "A=20;B=2;C=2;D=0;E=2;F=2;X=4";
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();
			String responses[] = allResponses.split(";");
			for (int i = 0; i < responses.length; i++) {
				dataset.setValue(
						responses[i].split("=")[0] + " = "
								+ Integer.parseInt(responses[i].split("=")[1]),
						Integer.parseInt(responses[i].split("=")[1]));
			}
			JFreeChart chart = ChartFactory.createPieChart(
					"Participant Quiz Response", dataset, true, true, false);
			chart.setBackgroundPaint(Color.white);
			
			//Font size of label in pie chart
            PiePlot plot = (PiePlot)chart.getPlot();
            
            //chart background color            
            plot.setBackgroundPaint(Color.white);
            
            //label background color label of inside chart
            plot.setLabelBackgroundPaint(Color.WHITE);            
            plot.setLabelFont(plot.getLabelFont().deriveFont(new Float(25)));
            
			String path = getServletContext().getRealPath("/");
			File file = new File(path + "pchart.jpeg");
			ChartUtilities.saveChartAsJPEG(file, chart, 800, 490);
			out.println("<img style=\"margin:auto; width:800px;display:block;\" src='pchart.jpeg'/>");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
		}
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
