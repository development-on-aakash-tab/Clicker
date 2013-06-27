package com.clicker.report;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.clicker.report.GenerateChart.GreenRedBarRenderer;

/**
 * Servlet implementation class GenerateChartRemote
 */
public class RemoteInstantChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoteInstantChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside GenerateChartRemote");
		response.setContentType("image/png");            
        String chartType = request.getParameter("rptname");     
        HttpSession session = request.getSession(true);
        String username= session.getAttribute("InstructorID").toString();
        String path = getServletContext().getRealPath("/");
        folderCreateOrDelete(path, username);
        try {
        	DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
            if (chartType.equals("AllRemoteCenterResponse")){            	
            	ReportHelper reportHelper = new ReportHelper();
            	String qid = request.getParameter("qid");
            	int maxval=0;
            	String cor_ans = session.getAttribute("cor_ans").toString();
            	String allResponses = reportHelper.getRCInstantResponse(qid);
	           	System.out.println(allResponses);	            		
	            if(!allResponses.equals("")){
	            String responses[] = allResponses.split(";");	    
	            DefaultCategoryDataset barDataset1 = new DefaultCategoryDataset();
	            for(int i=0; i<responses.length;i++){
	              	if(Integer.parseInt(responses[i].split("=")[1])>maxval)
	              		maxval = Integer.parseInt(responses[i].split("=")[1]);	                    	
	                    barDataset1.setValue(Integer.parseInt(responses[i].split("=")[1]), "Incorrect",responses[i].split("=")[0] );                    
	                }	            	
	               String chartInfo[] = {"Instant Quiz", "Responses", "No of Responses"};
	               responseChart(path, username, chartInfo, maxval, barDataset1,"InstantQuiz", cor_ans);
            	}
            }
            else if (chartType.equals("response")){            	
            	ReportHelper reportHelper = new ReportHelper();
            	String qid = request.getParameter("qid");
            	int centerid = Integer.parseInt(request.getParameter("centerid"));
            	int maxval=0;
            	String cor_ans = session.getAttribute("cor_ans").toString();
            	String allResponses = reportHelper.getRCInstantResponse(centerid,qid);
	           	System.out.println(allResponses);	            		
	            if(!allResponses.equals("")){
	            String responses[] = allResponses.split(";");	    
	            DefaultCategoryDataset barDataset1 = new DefaultCategoryDataset();
	            for(int i=0; i<responses.length;i++){
	              	if(Integer.parseInt(responses[i].split("=")[1])>maxval)
	              		maxval = Integer.parseInt(responses[i].split("=")[1]);	                    	
	                    barDataset1.setValue(Integer.parseInt(responses[i].split("=")[1]), "Incorrect",responses[i].split("=")[0] );                    
	                }	            	
	               String chartInfo[] = {"Instant Quiz", "Responses", "No of Responses"};
	               responseChart(path, username, chartInfo, maxval, barDataset1,"InstantQuiz", cor_ans);
            	}
	        }
        }
        catch (Exception e)
        {
           System.out.println(e.toString());
        }        
	}	
	
	public void folderCreateOrDelete(String path, String username){
		boolean iscreated = (new File(path + username)).mkdir();
		if (iscreated) {
			System.out.println("Directory: " + path + username + " created");
		}else {	
			File folder = new File (path+username);
			File[] files = folder.listFiles();
		    if(files!=null) { //some JVMs return null for empty dirs
		        for(File f: files) {
		            if(f.isDirectory()) {
		               continue;
		            } else {
		                f.delete();
		            }
		        }
		    }
		    System.out.println("Folder Content is deleted" + username);
		}
	}
	
	//Overload for quiz chart with out delete folder
		public void responseChart(String path, String username, String[] chartInfo, int maxval, DefaultCategoryDataset barDataset, String chartname, String answers){
			try{						
				GreenRedBarRenderer colorGreenRed = new GreenRedBarRenderer();

				if (!answers.contains(";")) {
					colorGreenRed.setCorrect(answers);
				} else if (answers.contains(";")){
					String answersArray [] = answers.split(";");
					for (int i = 0; i < answersArray.length; i++) {
						colorGreenRed.setCorrect(answersArray[i]);
					}
				}
				
				JFreeChart chart=null;
				chart= ChartFactory.createBarChart(chartInfo[0], chartInfo[1], chartInfo[2], barDataset, 
						PlotOrientation.VERTICAL, true, true, false);		
				
				chart.setBackgroundPaint(Color.white);
	            CategoryPlot plot = (CategoryPlot) chart.getPlot();
	                        
	           	// Set the Legend for chart
	            java.awt.Shape shape = new java.awt.geom.Rectangle2D.Double(-4.0,
	            		-4.0, 8.0, 8.0);            
	            org.jfree.chart.LegendItemCollection legend = plot.getLegendItems();
	            
	            legend.add(new org.jfree.chart.LegendItem(
							new java.text.AttributedString("Correct"), "", "", "",
							shape, java.awt.Color.GREEN));
	            plot.setFixedLegendItems(legend);
	            // set green color for correct answers
	            plot.setRenderer(colorGreenRed); 
	                
				chartLayout(chart, plot, path, username, chartname, maxval);
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		
		public void chartLayout(JFreeChart chart, CategoryPlot plot, String path, String username, String chartname, int maxval){
			try{			
	        
			//Object for riderer
	        BarRenderer renderer = (BarRenderer) plot.getRenderer();
			
			// set plot background color
	        plot.setBackgroundPaint(Color.white);
	        
	        // set y axis label size
	        plot.getDomainAxis().setLabelFont( plot.getDomainAxis().getLabelFont().deriveFont(new Float(16)) );
	        
	        // set tick label (bar value label) size
	        plot.getDomainAxis().setTickLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(12)));           
	                   
	        // Range Axis font size
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(16)));
	        rangeAxis.setTickLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(14)));
	        // set the upper margin for inside chart
	        rangeAxis.setUpperMargin(0.1);

	        // Show value on bar chart
	        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	        renderer.setBaseItemLabelsVisible(true);
	        
	        // set font for inner barchart label
	        renderer.setBaseItemLabelFont(plot.getDomainAxis().getLabelFont().deriveFont(new Float(15)));
	        rangeAxis.setTickUnit(new NumberTickUnit( (int)maxval/5 == 0 ? 1 : (int)maxval/5 , new DecimalFormat("0")));
	        File file = new File(path + username + "/"+chartname+".jpeg");
			ChartUtilities.saveChartAsJPEG(file, chart, 450, 300);
	        //ChartUtilities.writeChartAsPNG(out, chart, 800, 600);
			//out.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		
		class GreenRedBarRenderer extends BarRenderer {
			private static final long serialVersionUID = 1L;
			private String correct;

			public GreenRedBarRenderer() {
				super();
				correct="";
			}

			public void setCorrect(String c) {
				correct = correct + c;
			}

			public Paint getItemPaint(int x, int y) {
				org.jfree.data.category.CategoryDataset dataset = this.getPlot()
						.getDataset();
				String key = (String) dataset.getColumnKey(y);
				if (correct.equalsIgnoreCase(key)) {
					return java.awt.Color.GREEN;
				} else {
					return java.awt.Color.RED;
				}
			}
		}

}
