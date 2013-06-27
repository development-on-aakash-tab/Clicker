package com.clicker.report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;


/**
 * References :
 * 	Convert HTML to PDF :
 * 		http://today.java.net/pub/a/today/2007/06/26/generating-pdfs-with-flying-saucer-and-itext.html
 * 
 * 	Download core-renderer-R8-final.jar (ITextRenderer) 
 * 		http://wo-repository.doit.com.br/content/repositories/thirdparty/org/xhtmlrenderer/core-renderer/R8-final/
 * 
 */


/**
 * Servlet implementation class HTMLtoPDF
 */
public class HTMLtoPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HTMLtoPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request,	HttpServletResponse response) {
		response.setContentType("application/pdf;");		
		String path = getServletContext().getRealPath("/");
		HttpSession session = request.getSession(true);		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();	
			String reportType= request.getParameter("reportType");
			String InstructorID = session.getAttribute("InstructorID").toString();
			String reportContent = "";
			if(reportType.equals("Response")){
				response.setHeader("Content-Disposition", "attachment; filename=QuizResponse.pdf");
				if (session.getAttribute("ReportContent") != null) {
					reportContent = session.getAttribute("ReportContent").toString().replaceAll("&nbsp", "");
					reportContent = responseReplace(reportContent, sos, InstructorID, path);
					//session.removeAttribute("ReportContent");
				}
			}
			else if(reportType.equals("Result")){
				response.setHeader("Content-Disposition", "attachment; filename=QuizResult.pdf");
				if (session.getAttribute("QuizResultContent") != null) {
					reportContent = session.getAttribute("QuizResultContent").toString().replaceAll("&nbsp", "");
					reportContent = resultReplace(reportContent, sos, InstructorID, path);
					//session.removeAttribute("QuizResultContent");					
				}
			}

			// ITextRenderer is only works fine with compailed version of core-renderer.jar
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(reportContent);
			renderer.layout();
			try {
				renderer.createPDF(sos);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			sos.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String responseReplace(String reportContent, ServletOutputStream sos, String InstructorID, String path) {
		reportContent = reportContent.replaceAll("<br>", "<br/>");
		reportContent = reportContent.replaceAll("width: 60%; height: 100%; float:left; overflow-x: hidden; overflow-y:auto;","");
		reportContent = reportContent.replaceAll("width: 40%; float: right; height: 100%; overflow-x: hidden; overflow-y:auto;","");
		reportContent = reportContent.replaceAll(InstructorID + "/", path + InstructorID + "/");
		return reportContent;
	}
	
	public String resultReplace(String reportContent, ServletOutputStream sos, String InstructorID, String path) {
		reportContent = reportContent.replaceAll("<br>", "<br/>");
		reportContent = reportContent.replaceAll("width: 60%; height: 900px; float:left; overflow:auto;","");
		reportContent = reportContent.replaceAll("width: 40%; float: right;","");
		reportContent = reportContent.replaceAll(InstructorID + "/", path + InstructorID + "/");
		return reportContent;
	}

}
