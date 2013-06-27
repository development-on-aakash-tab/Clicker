package com.clicker.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

/**
 * Servlet implementation class RemoteReport
 */
//@WebServlet("/RemoteReport")
public class RemoteReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JasperReport jasReport; // holds compiled jrxml file
	static JasperPrint jasPrint; // contains report after result filling process
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoteReport() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JRException {
		System.out.println("Enter in to report");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String ReportType = request.getParameter("report_op");
		System.out.println(ReportType);
		String userType = session.getAttribute("Usertype").toString();
		String username ="";
		if(userType.equals("Instructor")){
			username= session.getAttribute("InstructorID").toString();			
		}
		if (ReportType.equals("ParticipantList")) {
			String Cid = request.getParameter("remotecenter");
			String path = getServletContext().getRealPath("/");
			out.println(participantReport(Cid, ReportType, path));
		}
		else if (ReportType.equals("RemoteCenterList")) {
			String path = getServletContext().getRealPath("/");
			out.println(remoteCenterList(ReportType, path));
		}
		else if (ReportType.equals("RemoteQuizResponse")) {
			String centerid = request.getParameter("remotecenter");
			String QID = request.getParameter("Quizname");
			String QTS = request.getParameter("hide_qts");		
			String path = getServletContext().getRealPath("/");
			if (session.getAttribute("QuestionIDs")!=null){
				String [] questionIDs = (String [])session.getAttribute("QuestionIDs");
				String htmlFile = "<html><head><META HTTP-EQUIV='CACHE-CONTROL' CONTENT='NO-CACHE'></META><title>Quiz Response</title></head><body>";
				String cntnt ="";
				String img="";
				String tcntnt="";
				String timg="";
				StringBuffer pdfcontent = new StringBuffer(htmlFile+"<div>");
				htmlFile += "<div style='width: 60%; height: 100%; float:left; overflow-x: hidden; overflow-y:auto;'>";				
				tcntnt = quizReport(centerid, QID, QTS, "RemoteQuizResponseHeader", path);
				htmlFile +=tcntnt;
				pdfcontent.append(tcntnt);
				String correctAns[] = session.getAttribute("correctAns").toString().split("@");
				for(int i=0;i<questionIDs.length;i++){
					tcntnt = questionReport(centerid, questionIDs[i], QID, QTS, "RemoteQuestionResponse", path, correctAns[i]);
					cntnt +=tcntnt;
					timg = "<div style='margin:auto;'><img align='middle' src='"+username+"/Chart"+i+".jpeg?"+new Date().getTime()+"'></img></div><br/><br/><br/>";
					img += timg;
					pdfcontent.append(timg+tcntnt);					
				}
				htmlFile += cntnt+"</div><div style='width: 40%; float: right; height: 100%; overflow-x: hidden; overflow-y:auto;'>";
				pdfcontent.append("</div></body></html>");
				htmlFile += img+"</div></body></html>";
				session.setAttribute("ReportContent", pdfcontent);
				if(session.getAttribute("QuestionIDs")!=null){
					session.removeAttribute("QuestionIDs");
				}
				if(session.getAttribute("correctAns")!=null){
					session.removeAttribute("correctAns");
				}
				out.println(htmlFile);				
			}			
			
			
			//out.println(quizReport(Cid,  QID,  QTS,  ReportType,  path));
		}
	}
    
    private String questionReport(String centerid, String QstnID, String QID, String QTS, String reportname, String path, String ans) {
		// TODO Auto-generated method stub
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();		
		try {
			
			HashMap hmapParam = new HashMap();
			System.out.println(path + "jasperreport/" + reportname
					+ ".jrxml");
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/" + reportname + ".jrxml");

			hmapParam.put("QstnID", QstnID);
			hmapParam.put("QID", QID);
			hmapParam.put("QTS", QTS);
			hmapParam.put("RCid", Integer.parseInt(centerid));
			hmapParam.put("ans", ans);
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			
			// Create the instance for HTML Exporter
			JRExporter htmlExporter = new JRHtmlExporter();
			
			// Setup report no header, no footer, no images for layout
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			htmlExporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);

			// Set parameter of output string and jasper print
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					file);
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasPrint);
			
			// Export the report to StringBuffer file
			htmlExporter.exportReport();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return file.toString();
	}
    
    
    private StringBuffer participantReport(String centerid, String ReportType, String path) {
		// TODO Auto-generated method stub
    	DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();
		try {
			HashMap hmapParam = new HashMap();
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/Remote" + ReportType + ".jrxml");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) as ParticipantCount FROM participant where CenterID ='"+centerid+"'");
			hmapParam.put("Cid", centerid);
			String StudCount ="";
			if(rs.next()){
				StudCount= rs.getString("ParticipantCount");
			}
			hmapParam.put("StudCount", StudCount);
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			String data;
			BufferedReader fbr;
			// Appending PDF download link in report
			file.append("<html><head><title>" + ReportType + "</title></head><body><div align=\"center\">");
			file.append("<div align=\"right\"><a href='RemotePDFReportDownload?reptype=participant&centerid="
					+ centerid
					+ "&StudCount="
					+ StudCount
					+ "&repname=Remote"
					+ ReportType
					+ "' method='post' target='_blank'> Download PDF </a></div>");

			// Create the instance for HTML Exporter
			JRExporter htmlExporter = new JRHtmlExporter();

			// Setup report no header, no footer, no images for layout
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			htmlExporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);

			// Set parameter of output string and jasper print
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					file);
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasPrint);

			// Export the report to StringBuffer file
			htmlExporter.exportReport();
			con.close();	
			file.append("</div></body></html>");
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
    	return file;
    }
    
    private StringBuffer remoteCenterList(String ReportType, String path) {
		// TODO Auto-generated method stub
    	DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();
		try {
			HashMap hmapParam = new HashMap();
			hmapParam = null;
			jasReport = JasperCompileManager.compileReport(path	+ "jasperreport/"  + ReportType + ".jrxml");
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			String data;
			BufferedReader fbr;
			// Appending PDF download link in report
			file.append("<html><head><title>" + ReportType + "</title></head><body><div align=\"center\">");
			file.append("<div align=\"right\"><a href='RemotePDFReportDownload?reptype=remote&repname="
					+ ReportType
					+ "' method='post' target='_blank'> Download PDF </a></div>");

			// Create the instance for HTML Exporter
			JRExporter htmlExporter = new JRHtmlExporter();

			// Setup report no header, no footer, no images for layout
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			htmlExporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);

			// Set parameter of output string and jasper print
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					file);
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasPrint);

			// Export the report to StringBuffer file
			htmlExporter.exportReport();
			con.close();	
			file.append("</div></body></html>");
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
    	return file;
    }
		
    
    

	/**
	 * This method is used to generate the quiz report
	 * @param Cid Course id
	 * @param QID Quiz id
	 * @param QTS Quiz Time stamp when the quiz was conducted
	 * @param reportname Name of the jasper report
	 * @param path path of jasper report
	 * @return
	 */
	private String quizReport(String centerid, String QID, String QTS,
			String reportname, String path) {
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();
		String studentCount ="";
		try {
			
			HashMap hmapParam = new HashMap();
			System.out.println(path	+ "jasperreport/"  + reportname+ ".jrxml");
			jasReport = JasperCompileManager.compileReport(path	+ "jasperreport/" + reportname + ".jrxml");

				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT count(distinct q.ParticipantID) as studCount FROM remotequizrecord qr, remotequizrecordquestion q, options o, participant p where qr.QuizID = "+QID+" and qr.QuizTimeStamp = '"+QTS+"' and q.QuizRecordID = qr.QuizRecordID and o.OptionID = q.OptionID and p.ParticipantID = q.ParticipantID and q.CenterID="+centerid);				
				if(rs.next()){
					studentCount = rs.getString("studCount");
				}
				hmapParam.put("centerid", centerid);
				hmapParam.put("QID", QID);
				hmapParam.put("QTS", QTS);
				hmapParam.put("studCount", studentCount);	
				System.out.println(centerid + QID + QTS + studentCount);
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			// Appending PDF download link in report
			if(reportname.equals("RemoteQuizResponseHeader")){
				file.append("<div align=\"right\"><a href='HTMLtoPDF?reportType=Response'>Download PDF </a></div>");
			}else{
			//file.append("<html><head><title>" + reportname + "</title></head><body><div align=\"center\">");			
			file.append("<div align=\"right\"><a href='RemotePDFReportDownload?reptype=remotequizresult&centerid="
					+ centerid
					+ "&qid="
					+ QID
					+ "&qts="
					+ QTS
					+ "&studCount="
					+ studentCount
					+ "&repname="
					+ reportname +"_Chart"
					+ "' method='post' target='_blank'> Download PDF </a></div>");
			}
			// Create the instance for HTML Exporter
			JRExporter htmlExporter = new JRHtmlExporter();

			// Setup report no header, no footer, no images for layout
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			htmlExporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
			htmlExporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);

			// Set parameter of output string and jasper print
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
					file);
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasPrint);

			// Export the report to StringBuffer file
			htmlExporter.exportReport();
			
			con.close();
			//file.append("</div></body></html>");			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file.toString();
	}

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
