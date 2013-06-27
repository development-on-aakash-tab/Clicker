package com.clicker.report;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clicker.databaseconn.DatabaseConnection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Reference 
 * 1. Generate Jasper Report
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperReport.html
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperPrint.html
 * 2. Download Japser Report
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperExportManager.html
 */

/**
 * This Servlet is used to generate the report which one you selected and download as PDF format
 * @author rajavel
 * 
 */
//@WebServlet("/DownloadPDF")
public class DownloadPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static JasperReport jasReport; // holds compiled jrxml file
	static JasperPrint jasPrint; // contains report after result filling process
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadPDF() {
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
	
	/**
	 * This method is used to check the report category and generate based on that.
	 * @param request HTTPServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reportname = request.getParameter("repname");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + reportname + ".pdf");
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
        try {
			String Cid = request.getParameter("cid");
			String reptype = request.getParameter("reptype");
			String SID = "";
			String QID = "";
			String QTS = "";
			String TS = "";		
			String studCount ="";			
			if(reportname.indexOf("Response") != -1){
				reportname = "QuizResponse_Chart";
			}else if(reportname.indexOf("Attendance") != -1){
				reportname = "Attendance_Chart";
			}
			String path = getServletContext().getRealPath("/");
			//String path = getServletContext().getRealPath(reportname + ".jrxml");
			//path = path.substring(0, path.indexOf(".metadata"))
				//	+ request.getContextPath().substring(1);
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/" + reportname + ".jrxml");
			System.out.println("Jasper Report compiled one : " + jasReport);
			System.out.println(con);
			HashMap hmapParam = new HashMap();
			if (reptype.equals("stud")) {
				SID = request.getParameter("sid");
				hmapParam.put("Cid", Cid);
				hmapParam.put("SID", SID);
			} else if (reptype.equals("quiz")) {
				QID = request.getParameter("qid");
				QTS = request.getParameter("qts");
				if (reportname.indexOf("Response") != -1 || reportname.indexOf("Result") != -1) {					
					hmapParam.put("Cid", Cid);
					hmapParam.put("QID", QID);
					hmapParam.put("QTS", QTS);
					if(reportname.indexOf("Response") != -1){
						hmapParam.put("SUBREPORT_DIR", path + "jasperreport/");
					}else{
						double topScore = Double.parseDouble(request.getParameter("topScore"));
						hmapParam.put("topScore", topScore);
					}
					studCount = request.getParameter("studCount");
					hmapParam.put("studCount", studCount);			
				} else if (reportname.indexOf("Detail") != -1) {
					hmapParam.put("Cid", Cid);
					hmapParam.put("QID", QID);
				}
			} else if (reptype.equals("course")) {
				if (reportname.indexOf("Attendance") != -1) {
					TS = request.getParameter("ATS");
					String AttSummary = request.getParameter("AttSummary");
					System.out.print("Inside Part in Download, TS Value " + TS);
					hmapParam.put("Cid", Cid);
					hmapParam.put("TS", TS);
					hmapParam.put("AttSummary", AttSummary);
					hmapParam.put("SUBREPORT_DIR", path + "jasperreport/");					
				} else if (reportname.equals("StudentList")) {
					hmapParam.put("Cid", Cid);				
					hmapParam.put("StudCount", request.getParameter("StudCount"));
				}else if (reportname.equals("QuizSummary")) {
					hmapParam.put("Cid", Cid);				
					String NoofQuiz = request.getParameter("NoofQuiz");		
					hmapParam.put("NoofQuiz", NoofQuiz);
				}else if (reportname.equals("StudentQuery")) {
					hmapParam.put("Cid", Cid);				
					hmapParam.put("queryDate", request.getParameter("queryDate"));
				}else {
					hmapParam.put("Cid", Cid);
				}
				System.out.println("Inside DowbloadPDF :" + Cid + " " + TS
						+ " " + reportname);
			}
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			System.out.println("Jasper Print : " + jasPrint);
			ServletOutputStream sos = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasPrint, sos);
			sos.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
