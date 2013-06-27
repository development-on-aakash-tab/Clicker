package com.clicker.report;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.clicker.report.GenerateChart.GreenRedBarRenderer;
import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;

/**
 * Servlet implementation class GenerateChartFromHashMap
 */
public class GenerateChartFromHashMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateChartFromHashMap() {
        super();
        // TODO Auto-generated constructor stub
    }

    private ServletContext ctx = null;

    @Override
    public void init(ServletConfig config) throws ServletException { 
         ctx = config.getServletContext(); 
    } 
    
    static <K,V extends Comparable<? super V>>  SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
    
    public static Map<String,Integer> sortByComparator(Map<String,Integer> unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        //sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        //put sorted list into map again
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    } 
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	String InstructorID = session.getAttribute("InstructorID").toString();
    	ReportHelper reportHelper = new ReportHelper();
    	String CourseID = session.getAttribute("courseID").toString();		
    	ConcurrentHashMap<String, String> AllStudentResponse = (ConcurrentHashMap<String, String>) ctx.getAttribute(CourseID + "AllStudentResponse");
    	Vector<Question> Questiondetails = (Vector<Question>) ctx.getAttribute(InstructorID + "Questiondetails");
    	
    	String [] QuestionResponseArray=null;
		Set<String> participantIDs = AllStudentResponse.keySet();
		
		// int questionIndex =0;
		System.out.println("Questiondetails.size()"	+ Questiondetails.size());
		String path = ctx.getRealPath("/");
		System.out.println(path);
		 folderCreateOrDelete(path, InstructorID);
	       
		for (int i = 0; i < Questiondetails.size(); i++) {
			Question quest = Questiondetails.get(i);
			int qtype = quest.getQuestionType();
			System.out.println(qtype);
			Option opt = new Option();
			String numresp="";
			Map <String, Integer> respcount;			
			respcount = new TreeMap<String, Integer>();
			
			Iterator iter = participantIDs.iterator();
			while (iter.hasNext()) {
				String current_pid = iter.next().toString();
				QuestionResponseArray = AllStudentResponse.get(current_pid).split("@@");		
				String resp = "Z";
				if(i < QuestionResponseArray.length){
					resp = QuestionResponseArray[i];
				}
				if(respcount.get(resp)!=null){
				respcount.put(resp, new Integer(respcount.get(resp)+1));
				}else{
					respcount.put(resp, new Integer(1));
				}
			}			
			int maxval=0;
			DefaultCategoryDataset barDataset1 = new DefaultCategoryDataset();
			Set<String> respkey;		
			if(qtype==2 || qtype ==3){
				Map<String, Integer> tt = sortByComparator(respcount);
				respkey = tt.keySet();
			}else{
			respkey = respcount.keySet();
			}
			iter = respkey.iterator();			
			while (iter.hasNext()) {
				String current_resp = iter.next().toString();
				int respvalue = respcount.get(current_resp);
				if(respvalue>maxval)
            		maxval = respvalue;
				barDataset1.setValue(respvalue, "Incorrect", current_resp); 
				System.out.println(respvalue + ":" + current_resp);
			}                   	
            String chartInfo[] = {"Question - " + (i+1), "Responses", "No of Responses"};
            responseChart(path, InstructorID, chartInfo, maxval, barDataset1,"Chart"+i, reportHelper.getCorrectAnswer(quest.getQuestionID()));
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
		    System.out.println("Folder Content is deleted");
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
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
			System.out.println("0-0-0-0-0-00-0-0-0-0-00-0-0-0-0-0 = "+ key + " - correct = " + correct );
			if (correct.equalsIgnoreCase(key)) {
				return java.awt.Color.GREEN;
			} else {
				return java.awt.Color.RED;
			}
		}
	}
	
	 class DifferentColorRenderer extends BarRenderer {
	        private static final long serialVersionUID = 1L;
			/** The colors. */
	        private Paint[] colors;

	        /**
	         * Creates a new renderer.
	         *
	         * @param colors  the colors.
	         */
	        public DifferentColorRenderer(final Paint[] colors) {
	            this.colors = colors;
	        }

	        /**
	         * Returns the paint for an item.  Overrides the default behaviour inherited from
	         * AbstractSeriesRenderer.
	         *
	         * @param row  the series.
	         * @param column  the category.
	         *
	         * @return The item color.
	         */
	        public Paint getItemPaint(final int row, final int column) {
	            return this.colors[column % this.colors.length];
	        }
	    }
}
