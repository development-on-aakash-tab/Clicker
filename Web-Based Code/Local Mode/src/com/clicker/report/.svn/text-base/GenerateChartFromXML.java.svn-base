package com.clicker.report;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class GenerateChartFromXML
 */
public class GenerateChartFromXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateChartFromXML() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	public void readResponse(String pathxml, String FileName, String username, String path){
		int maxval=0;
		try {
			ReportHelper reportHelper = new ReportHelper();
			//String quizid = request.getParameter("qid");			
			try {
	            File file = new File(pathxml + FileName);
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = (Document) db.parse(file);
	            doc.getDocumentElement().normalize();
	            NodeList qid_nlist = doc.getElementsByTagName("qid");
	            //String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
	            System.out.println(qid_nlist.getLength());
	            for(int i=0; i<qid_nlist.getLength();i++){
	            	Element qid_Element = (Element)qid_nlist.item(i);
	            	String qid = qid_Element.getAttribute("qid").toString();         
	            	System.out.println("Qid : "+qid);
	            	NodeList Response_nlist = qid_Element.getElementsByTagName("Response");
	            	HashMap<String, String> resps = new HashMap<String, String>();
	            	for(int j=0;j<Response_nlist.getLength();j++){
	            		Element Response_Element = (Element)Response_nlist.item(j);
	            		NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
	            		NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
	            		String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
	            		String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
	            		System.out.println("pid : "+pid + " resp : " +resp);
	            		if(resps.get(resp)!=null){
	            			int rcount = (Integer.parseInt(resps.get(resp))+1);
	            			resps.put(resp,Integer.toString(rcount));
	            		}else{
	            			resps.put(resp,"1");
	            		}	            		
	            		//responseTable += "<tr><td>" + RemoteCenterID + "</td><td>" + qid + "</td><td>" + pid + "</td><td>" + resp + "</td></tr>";
	            	}
	            	            	
	            	String Answer = reportHelper.getQuestionCorrectness(qid);
	            	DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
	    			Set<String> respval = resps.keySet();
	    			Iterator<String> iter = respval.iterator();
	    		    while (iter.hasNext()) {
	    		    	String resp = iter.next();
	    		    	if (Integer.parseInt(resps.get(resp)) > maxval){
	    					maxval = Integer.parseInt(resps.get(resp));
	    		    	}
	    		    	System.out.println("Resp&Count : "+resp + Integer.parseInt(resps.get(resp)));
	    		    	barDataset.setValue(Integer.parseInt(resps.get(resp)), "Incorrect", resp.equals("Z")?"No Response":resp);	    				
	    		    }
	    		    String chartInfo[] = { "Question - " + (i + 1), "Responses", "No of Responses" };
    				responseChart(path, username, chartInfo, maxval, barDataset, "Question" + (i+1), Answer);
				}	            
	        } catch (SAXException ex) {
	        } catch (IOException ex) {
	        } catch (ParserConfigurationException ex) {
	        }		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void readAllResponse(String pathxml, String FileNames, String username, String path){
		try {
			System.out.println(FileNames);
			ReportHelper reportHelper = new ReportHelper();
			String [] fileNames = FileNames.split(";");
			HashMap<String, HashMap<String, String>> allresp = new HashMap<String, HashMap<String, String>>();
			HashMap<String, String> correctAns = new HashMap<String, String>();
			for(int nf = 0; nf<fileNames.length;nf++){
				String FileName = fileNames[nf];
			  try {
	            File file = new File(pathxml + FileName);
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = (Document) db.parse(file);
	            doc.getDocumentElement().normalize();
	            NodeList qid_nlist = doc.getElementsByTagName("qid");
	            //String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
	            for(int i=0; i<qid_nlist.getLength();i++){
	            	Element qid_Element = (Element)qid_nlist.item(i);
	            	String qid = qid_Element.getAttribute("qid").toString();         
	            	System.out.println("Qid : "+qid);
	            	NodeList Response_nlist = qid_Element.getElementsByTagName("Response");
	            	HashMap<String, String> resps = new HashMap<String, String>();
	            	if(allresp.get(Integer.toString(i))!=null){
	            		resps = allresp.get(Integer.toString(i));	            		
	            	}
	            	for(int j=0;j<Response_nlist.getLength();j++){
	            		Element Response_Element = (Element)Response_nlist.item(j);
	            		NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
	            		NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
	            		String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
	            		String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
	            		System.out.println("pid : "+pid + " resp : " +resp);	            		
	            		if(resps.get(resp)!=null){
	            			int rcount = (Integer.parseInt(resps.get(resp))+1);
	            			resps.put(resp,Integer.toString(rcount));
	            		}else{
	            			resps.put(resp,"1");
	            		}
	            	}	            	
	            	if(allresp.get(Integer.toString(i))!=null){
	            		allresp.put(Integer.toString(i), resps);
	            	}else{
	            		allresp.put(Integer.toString(i), resps);
	            		String Answer = reportHelper.getQuestionCorrectness(qid);
	            		correctAns.put(Integer.toString(i), Answer);
	            	}							
	            }
	        } catch (SAXException ex) {
	        } catch (IOException ex) {
	        } catch (ParserConfigurationException ex) {
	        }	
			}
			parseResponse(allresp, correctAns, username, path);
		  } catch (Exception e) {
			   System.out.println(e.toString());
		}	
		
	}
	
	public void parseResponse(HashMap<String, HashMap<String, String>> allresp,HashMap<String, String> correctAns, String username, String path){
		for(int i=0;i<allresp.size();i++){
			HashMap<String, String> resps = allresp.get(Integer.toString(i));
			String Answer = correctAns.get(Integer.toString(i));
			System.out.println("Ans : "+ Answer);
			int maxval =0;
			DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
			Set<String> respval = resps.keySet();
			Iterator<String> iter = respval.iterator();
		    while (iter.hasNext()) {
		    	String resp = iter.next();
		    	if (Integer.parseInt(resps.get(resp)) > maxval){
					maxval = Integer.parseInt(resps.get(resp));
		    	}
		    	System.out.println("Resp&Count : "+resp + Integer.parseInt(resps.get(resp)));
		    	barDataset.setValue(Integer.parseInt(resps.get(resp)), "Incorrect", resp.equals("Z")?"No Response":resp);				
		    }			
		    String chartInfo[] = { "Question - " + (i + 1), "Responses", "No of Responses" };
			responseChart(path, username, chartInfo, maxval, barDataset, "Question" + (i+1), Answer);
		}
	}
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");		
		HttpSession session = request.getSession(true);
		String username = session.getAttribute("InstructorID").toString();
		System.out.println("Inside Generate Chart from XML");
		String path = getServletContext().getRealPath("/");
		System.out.println(path);
		folderCreateOrDelete(path, username);
		String pathxml = System.getProperty("user.home") + "/ClickerRemoteFiles/Received/";
		String responseType = request.getParameter("responseType");
		String FileName = request.getParameter("filename");
		if(responseType.equals("response")){			
			readResponse(pathxml, FileName, username, path);
		}else if(responseType.equals("allresponse")){
			readAllResponse(pathxml, FileName, username, path);
		}
	}

	public void folderCreateOrDelete(String path, String username) {
		boolean iscreated = (new File(path + username)).mkdir();
		if (iscreated) {
			System.out.println("Directory: " + path + username + " created");
		} else {
			File folder = new File(path + username);
			File[] files = folder.listFiles();
			if (files != null) { // some JVMs return null for empty dirs
				for (File f : files) {
					if (f.isDirectory()) {
						continue;
					} else {
						f.delete();
					}
				}
			}
			System.out.println("Folder Content is deleted");
		}
	}

	// Overload for quiz chart with out delete folder
	public void responseChart(String path, String username, String[] chartInfo,
			int maxval, DefaultCategoryDataset barDataset, String chartname,
			String answers) {
		try {
			GreenRedBarRenderer colorGreenRed = new GreenRedBarRenderer();

			if (!answers.contains(";")) {
				colorGreenRed.setCorrect(answers);
			} else if (answers.contains(";")) {
				String answersArray[] = answers.split(";");
				for (int i = 0; i < answersArray.length; i++) {
					colorGreenRed.setCorrect(answersArray[i]);
				}
			}

			JFreeChart chart = null;
			chart = ChartFactory.createBarChart(chartInfo[0], chartInfo[1],
					chartInfo[2], barDataset, PlotOrientation.VERTICAL, true,
					true, false);

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
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void chartLayout(JFreeChart chart, CategoryPlot plot, String path,
			String username, String chartname, int maxval) {
		try {

			// Object for riderer
			BarRenderer renderer = (BarRenderer) plot.getRenderer();

			// set plot background color
			plot.setBackgroundPaint(Color.white);

			// set y axis label size
			plot.getDomainAxis().setLabelFont(
					plot.getDomainAxis().getLabelFont()
							.deriveFont(new Float(16)));

			// set tick label (bar value label) size
			plot.getDomainAxis().setTickLabelFont(
					plot.getDomainAxis().getLabelFont()
							.deriveFont(new Float(12)));

			// Range Axis font size
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setLabelFont(plot.getDomainAxis().getLabelFont()
					.deriveFont(new Float(16)));
			rangeAxis.setTickLabelFont(plot.getDomainAxis().getLabelFont()
					.deriveFont(new Float(14)));
			// set the upper margin for inside chart
			rangeAxis.setUpperMargin(0.1);

			// Show value on bar chart
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			renderer.setBaseItemLabelsVisible(true);

			// set font for inner barchart label
			renderer.setBaseItemLabelFont(plot.getDomainAxis().getLabelFont()
					.deriveFont(new Float(15)));
			rangeAxis.setTickUnit(new NumberTickUnit((int) maxval / 5 == 0 ? 1
					: (int) maxval / 5, new DecimalFormat("0")));
			File file = new File(path + username + "/" + chartname + ".jpeg");
			ChartUtilities.saveChartAsJPEG(file, chart, 450, 300);
			// ChartUtilities.writeChartAsPNG(out, chart, 800, 600);
			// out.close();z
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	class GreenRedBarRenderer extends BarRenderer {
		private static final long serialVersionUID = 1L;
		private String correct;

		public GreenRedBarRenderer() {
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
