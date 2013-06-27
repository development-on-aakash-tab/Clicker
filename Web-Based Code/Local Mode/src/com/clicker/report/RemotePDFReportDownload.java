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
 * Servlet implementation class RemotePDFReportDownload
 */
//@WebServlet("/RemotePDFReportDownload")
public class RemotePDFReportDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JasperReport jasReport; // holds compiled jrxml file
	static JasperPrint jasPrint; // contains report after result filling process

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemotePDFReportDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reportname = request.getParameter("repname");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ reportname + ".pdf");
		DatabaseConnection dbcon = new DatabaseConnection();
		Connection con = dbcon.createDatabaseConnection();
		try {
			String centerid = request.getParameter("centerid");
			String reptype = request.getParameter("reptype");
			String StudCount = "";
			String path = getServletContext().getRealPath("/");
			path = path.substring(0, path.indexOf(".metadata"))	+ request.getContextPath().substring(1);
			jasReport = JasperCompileManager.compileReport(path
					+ "/WebContent/jasperreport/" + reportname + ".jrxml");
			System.out.println("Jasper Report compiled one : " + jasReport);
			System.out.println(con);
			HashMap hmapParam = new HashMap();
			if (reptype.equals("participant")) {
				StudCount = request.getParameter("studCount");
				hmapParam.put("Cid", centerid);
				hmapParam.put("StudCount", StudCount);
			}
			else if (reptype.equals("remote")) {
				hmapParam = null;
			}
			else if (reptype.equals("remotequizresult")) {
				StudCount = request.getParameter("StudCount");
				String QID = request.getParameter("qid");
				String QTS = request.getParameter("qts");
				hmapParam.put("centerid", centerid);
				hmapParam.put("QID", QID);
				hmapParam.put("QTS", QTS);
				hmapParam.put("studCount", StudCount);
				hmapParam.put("SUBREPORT_DIR", path + "/WebContent/jasperreport/");				
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);		
	}

}
