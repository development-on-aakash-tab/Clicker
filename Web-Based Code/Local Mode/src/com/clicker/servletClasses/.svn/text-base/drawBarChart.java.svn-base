package com.clicker.servletClasses;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Servlet implementation class drawBarChart
 */
public class drawBarChart extends HttpServlet {
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
		System.out.println("Inside BarChat");
		PrintWriter out = response.getWriter();
		System.out.println("Before ");
		//String allResponses = "A=20;B=2;C=2;D=0;E=2;F=2;X=4";        
		String allResponses = request.getParameter("BarResponseString");
		String CorrectAnswer = request.getParameter("CorrectAnswer");
		//String CorrectAnswer = "A";

		String Legend = "";
		int maxval=0;
        
		if (CorrectAnswer.equals("No")) {
			Legend = "Response";
		} else {
			Legend = "Incorrect";
		}

		System.out.println("Responses are " + allResponses);

		try {
			DefaultCategoryDataset dataSetBarChart = new DefaultCategoryDataset();
			String responses[] = allResponses.split(";");

			for (int i = 0; i < responses.length; i++) {
				if(Integer.parseInt(responses[i].split("=")[1])>maxval)
                    maxval = Integer.parseInt(responses[i].split("=")[1]);                
				dataSetBarChart.addValue(
						Integer.parseInt(responses[i].split("=")[1]), Legend,
						responses[i].split("=")[0]);
			}

			DifferenceBarRenderer diffcolor = new DifferenceBarRenderer();

			if (!CorrectAnswer.equals("No")) {

				int CorrectAnswerLength = CorrectAnswer.length();

				if (CorrectAnswerLength == 1) {
					diffcolor.setCorrect(CorrectAnswer);
				} else {
					for (int i = 0; i < CorrectAnswerLength; i++) {
						String cmp = Character
								.toString(CorrectAnswer.charAt(i));
						diffcolor.setCorrect(cmp);
					}
				}
			} else {
				diffcolor.setCorrect(CorrectAnswer);
			}

			JFreeChart barchart = ChartFactory.createBarChart(
					"Participant Responses", "Responses", "Number of Participants",
					dataSetBarChart, PlotOrientation.VERTICAL, true, true,
					false);

			CategoryPlot plot = (CategoryPlot) barchart.getPlot();
			java.awt.Shape shape = new java.awt.geom.Rectangle2D.Double(-4.0,
					-4.0, 8.0, 8.0);
			org.jfree.chart.LegendItemCollection legend = plot.getLegendItems();
			if (!CorrectAnswer.equals("No")) {
				legend.add(new org.jfree.chart.LegendItem(
						new java.text.AttributedString("Correct"), "", "", "",
						shape, java.awt.Color.GREEN));
			}
			plot.setFixedLegendItems(legend);

			org.jfree.chart.LegendItemCollection lc = plot.getLegendItems();

			plot.setRenderer(diffcolor);
			NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
			rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			//Object for riderer
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
			
			// set plot background color
            plot.setBackgroundPaint(Color.white);
            
            // set y axis label size
            plot.getDomainAxis().setLabelFont( plot.getDomainAxis().getLabelFont().deriveFont(new Float(20)) );
            
            // set tick label (bar value label) size
            plot.getDomainAxis().setTickLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(18)));            
            
            // Range Axis font size
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(20)));
            rangeAxis.setTickLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(18)));
            // set the upper margin for inside chart
            rangeAxis.setUpperMargin(0.1);
    
            // Show value on bar chart
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            
            // set font for inner barchart label
            renderer.setBaseItemLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(18)));
            rangeAxis.setTickUnit(new NumberTickUnit( (int)maxval/5 == 0 ? 1 : (int)maxval/5 , new DecimalFormat("0")));
			
			
			String path = getServletContext().getRealPath("/");
			File file = new File(path + "bchart.jpeg");

			ChartUtilities.saveChartAsJPEG(file, barchart, 1024, 490);
			//int chartwidth = Integer.parseInt(request.getAttribute("chartwidth").toString());
			//chartwidth -= chartwidth * 20 /100; 
			//out.println("<img style=\"margin:auto; width:" + chartwidth +"1000px;display:block;\" src='bchart.jpeg'/>");
			out.println("<img style=\"margin:auto; width:1000px;display:block;\" src='bchart.jpeg'/>");

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
	}// </editor-fold>

	class DifferenceBarRenderer extends BarRenderer {
		private static final long serialVersionUID = 1L;
		private String correct;

		public DifferenceBarRenderer() {
			super();
		}

		public void setCorrect(String c) {
			correct = correct + c;
		}

		public Paint getItemPaint(int x, int y) {
			org.jfree.data.category.CategoryDataset dataset = this.getPlot()
					.getDataset();
			String key = (String) dataset.getColumnKey(y);
			if (correct.contains(key)) {
				return java.awt.Color.GREEN;
			} else {
				return java.awt.Color.RED;
			}
		}
	}
}