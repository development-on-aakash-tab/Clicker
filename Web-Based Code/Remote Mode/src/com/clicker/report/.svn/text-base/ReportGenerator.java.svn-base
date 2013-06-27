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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;


/**
 * Reference 
 * 1. Generate Jasper Report
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperReport.html
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JasperPrint.html
 * 2. Export HTML Export 
 * 		http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/JRExporter.html
 */

/**
 * This servlet is used to generate the jasper report
 * 
 * @author rajavel
 * 
 */
// @WebServlet("/ReportGenerator")
public class ReportGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JasperReport jasReport; // holds compiled jrxml file
	static JasperPrint jasPrint; // contains report after result filling process

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportGenerator() {
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
		try {
			doProcess(request, response);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to check the report category whether student, quiz or
	 * course
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * @throws JRException
	 */
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JRException {
		//Avoid to store catch of previous response
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
		
		System.out.println("Inside ReportGenerator");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		String userType = session.getAttribute("Usertype").toString();
		String username ="";
		double topScore = 1.0;
		if(userType.equals("Instructor")){
			username= session.getAttribute("InstructorID").toString();			
		}
		String ReportType = request.getParameter("report");
		System.out.println( "Report Type :" + ReportType);
		if (ReportType.equals("Student Report")) {
			System.out.println("Inside REport");
			String Cid = request.getParameter("hide_cid");
			String SID = request.getParameter("hide_sid");
			String reportname = request.getParameter("hide_rptname");
			System.out.println(reportname);
			String path = getServletContext().getRealPath("/");
			out.println(studentReport(Cid, SID, reportname, path));
		} else if (ReportType.equals("Quiz Report")) {
			String Cid = request.getParameter("hide_cid1");
			String QID = request.getParameter("hide_qid");
			String QTS = request.getParameter("hide_qts");
			String reportname = request.getParameter("hide_qrptname");
			String path = getServletContext().getRealPath("/");
			System.out.println(reportname);
			if (reportname.equals("QuizResponse")) {				
				if (session.getAttribute("QuestionIDs")!=null){
					String [] questionIDs = (String [])session.getAttribute("QuestionIDs");
					StringBuffer htmlFile = new StringBuffer("<html><head><META HTTP-EQUIV='CACHE-CONTROL' CONTENT='NO-CACHE'></META><title>Quiz Response</title></head><body>");
					htmlFile.append("<div style='width: 60%; height: 100%; float:left; overflow-x: hidden; overflow-y:auto;'>");
					htmlFile.append(quizReport(Cid, QID, QTS, "QuizResponseHeader", path, topScore));
					String tContent = "";
					StringBuffer content = new StringBuffer();
					String tImg = "";
					StringBuffer img = new StringBuffer();
					StringBuffer downloadPDF = new StringBuffer(htmlFile);
					String correctAns[] = session.getAttribute("correctAns").toString().split("@");
					for(int i=0;i<questionIDs.length;i++){
						tContent = questionReport(questionIDs[i], QID, QTS, "QuestionResponse", path, correctAns[i]);
						tImg = "<div style='margin:auto;'><img align='middle' src='"+username+"/Chart"+i+".jpeg?"+new Date().getTime()+"'></img></div><br/><br/><br/>";
						downloadPDF.append(tImg + tContent);
						content.append(tContent);
						img.append(tImg);
					}
					htmlFile.append(content + "</div><div style='width: 40%; float: right; height: 100%; overflow-x: hidden; overflow-y:auto;'>");
					htmlFile.append(img + "</div></body></html>");
					downloadPDF.append("</div></body></html>");
					session.setAttribute("ReportContent", downloadPDF.toString());
					if(session.getAttribute("QuestionIDs")!=null){
						session.removeAttribute("QuestionIDs");
					}
					if(session.getAttribute("correctAns")!=null){
						session.removeAttribute("correctAns");
					}
					out.println(htmlFile);				
				}
			} else if (reportname.equals("QuizResult")) {
				if(session.getAttribute("topPercentage") != null){
					topScore = Double.parseDouble(session.getAttribute("topPercentage").toString());
					session.removeAttribute("topPercentage");
				}
				String htmlFile = "<html><head><META HTTP-EQUIV='CACHE-CONTROL' CONTENT='NO-CACHE'></META><title>Quiz Result</title></head><body>";
				htmlFile += "<div style='width: 60%; height: 900px; float:left; overflow:auto;'>";
				htmlFile += quizReport(Cid, QID, QTS, reportname, path, topScore);
				htmlFile += "</div>";
				htmlFile += "<div style='width: 40%; float: right;'>"
						+ "<img src='"+username+"/QuizResult.jpeg?"+new Date().getTime()+"'></img> <br/> <br/>"
						+ "<img src='"+username+"/QuizGrade.jpeg?"+new Date().getTime()+"'></img>" + "</div>";
				htmlFile += "</body></html>";			
				session.setAttribute("QuizResultContent", htmlFile);
				out.println(htmlFile);
			}else {
				out.println(quizReport(Cid, QID, QTS, reportname, path, topScore));
			}
			
		} else if (ReportType.equals("Course Report")) {
			String Cid = request.getParameter("hide_cid2");
			String ATS = request.getParameter("hide_att_ts");
			String reportname = request.getParameter("hide_crptname");
			String path = getServletContext().getRealPath("/");
			String queryDate = request.getParameter("hide_query_date");
			if (reportname.equals("Attendance")) {
				String htmlFile = "<html><head><META HTTP-EQUIV='CACHE-CONTROL' CONTENT='NO-CACHE'></META><title>Attendance</title></head><body>";
				htmlFile += "<div style='width: 60%; height: 900px; float:left; overflow:auto;'>";
				htmlFile += courseReport(Cid, ATS, reportname,queryDate, path);
				htmlFile += "</div>";
				htmlFile += "<div style='width: 40%; float: right;'>"
						+ "<img src='"+username+"/Attendance.jpeg?"+new Date().getTime()+"' />" + "</div>";
				out.println(htmlFile);
			}else{
			out.println(courseReport(Cid, ATS, reportname,queryDate, path));
			}
		}
	}

	/**
	 * This method is used to generate student report
	 * 
	 * @param Cid
	 *            Course Id
	 * @param SID
	 *            Student Id
	 * @param reportname
	 *            jasper report name
	 * @param path
	 *            location of jasper report
	 * @return
	 */
	public String studentReport(String Cid, String SID, String reportname,
			String path) {
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();
		try {
			HashMap<String, String> hmapParam = new HashMap<String, String>();
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/" + reportname + ".jrxml");
			hmapParam.put("Cid", Cid);
			hmapParam.put("SID", SID);
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			
			// Appending PDF download link in report
			file.append("<div align=\"right\"><a href='DownloadPDF?reptype=stud&cid="
					+ Cid
					+ "&sid="
					+ SID
					+ "&repname="
					+ reportname
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
		} catch (Exception ex) {
		}
		return file.toString();
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
	private String quizReport(String Cid, String QID, String QTS,
			String reportname, String path, double topScore) {
		// TODO Auto-generated method stub
		System.out.println("Inside quizreport");
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
		StringBuffer file = new StringBuffer();
		String studentCount ="";
		try {
			
			HashMap hmapParam = new HashMap();
			System.out.println(path + "jasperreport/" + reportname
					+ ".jrxml");
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/" + reportname + ".jrxml");
			if (reportname.equals("QuizResponseHeader")) {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select count(distinct qrq.StudentID) as studCount from quiz q, quizrecord qr, quizrecordquestion qrq, student s, options o where q.CourseID= '"+Cid+"' and qr.QuizID = q.QuizID and qr.QuizID = '"+QID+"' and qr.TimeStamp = '"+QTS+"' and qrq.QuizRecordID = qr.QuizRecordID and s.StudentID=qrq.StudentID and o.OptionID = qrq.OptionID");
				if(rs.next()){
					studentCount = rs.getString("studCount");
				}
				hmapParam.put("Cid", Cid);
				hmapParam.put("QID", QID);
				hmapParam.put("QTS", QTS);
				hmapParam.put("studCount", studentCount);	
			}else if (reportname.equals("QuizResult")) {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select count(distinct qrq.StudentID) as studCount from " +
						"quiz q, quizrecord qr, quizrecordquestion qrq, student s, options o " +
						"where q.CourseID= '"+Cid+"' and qr.QuizID = q.QuizID and qr.QuizID = '"+QID+"' and " +
								"qr.TimeStamp = '"+QTS+"' and qrq.QuizRecordID = qr.QuizRecordID and " +
										"s.StudentID=qrq.StudentID and o.OptionID = qrq.OptionID");
				if(rs.next()){
					studentCount = rs.getString("studCount");
				}				
				
				System.out.println(topScore);
				hmapParam.put("Cid", Cid);
				hmapParam.put("QID", QID);
				hmapParam.put("QTS", QTS);
				hmapParam.put("studCount", studentCount);	
				hmapParam.put("topScore", topScore);
				rs.close();
				st.close();
			}else if (reportname.equals("QuizDetail")) {
				hmapParam.put("Cid", Cid);
				hmapParam.put("QID", QID);
			} 
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			
			if(reportname.equals("QuizResponseHeader")){
				file.append("<div align=\"right\"><a href='HTMLtoPDF?reportType=Response'>Download PDF </a></div>");
			}else if(reportname.equals("QuizResult")){
				file.append("<div align=\"right\"><a href='HTMLtoPDF?reportType=Result'>Download PDF </a></div>");
			}else{
			// Appending PDF download link in report
			file.append("<div align=\"right\"><a href='DownloadPDF?reptype=quiz&cid="
					+ Cid
					+ "&qid="
					+ QID
					+ "&qts="
					+ QTS
					+ "&studCount="
					+ studentCount
					+ "&topScore="
					+ topScore
					+ "&repname="
					+ reportname
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
			
			//Export to xls
			/*JasperPrint print = JasperFillManager.fillReport(jasReport, hmapParam, con);
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        OutputStream outputfile= new FileOutputStream(new File("/home/rajavel/Desktop/JasperReport.xls"));
		
	       JRXlsExporter exporterXLS = new JRXlsExporter();
	         exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
	         exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
	         exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	         exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	         exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	         exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	         exporterXLS.exportReport();
	         outputfile.write(output.toByteArray()); */
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file.toString();
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
	private String questionReport(String QstnID, String QID, String QTS, String reportname, String path, String ans) {
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
			hmapParam.put("ans", ans.toUpperCase());
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

	/** 
	 * 
	 * This method is used to generate the course jasper report 
	 * 
	 * @param Cid Course id
	 * @param ATS Attendance Time stamp
	 * @param reportname Report name
	 * @param path Path of jasper report
	 * @return
	 */
	public String courseReport(String Cid, String ATS, String reportname, String queryDate,
			String path) {
		DatabaseConnection dbcon = new DatabaseConnection();
        Connection con = dbcon.createDatabaseConnection();
        String StudCount = "0";
        String AttSummary ="";
        String NoofQuiz ="";
        StringBuffer file = new StringBuffer();
		
		try {
			HashMap hmapParam = new HashMap();
			jasReport = JasperCompileManager.compileReport(path
					+ "jasperreport/" + reportname + ".jrxml");

			if (reportname.equals("Attendance")) {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select A.Attendance_Flag, count(*) as attCount from student S, attendance A where A.StudentID = S.StudentID and A.CourseID='"+Cid+"' and A.AttendanceTS = '"+ATS+"' group by A.Attendance_Flag");				
				while(rs.next()){
					String Flag = rs.getString("Attendance_Flag");
					if(Flag.equals("0")){
						AttSummary += "Absent :" + rs.getString("attCount") + " ";
					}else if(Flag.equals("1")){
						AttSummary += "Present :" + rs.getString("attCount") + " ";
					}
				}
				hmapParam.put("Cid", Cid);
				hmapParam.put("TS", ATS);
				hmapParam.put("AttSummary", AttSummary);
			} else if (reportname.equals("StudentList")) {
				hmapParam.put("Cid", Cid);				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT count(*) as NoofStudent FROM studentcourse sc, student s where sc.CourseID = '" + Cid + "' and s.StudentID = sc.StudentID");
				if(rs.next()){
					StudCount = rs.getString("NoofStudent");
				}
				hmapParam.put("StudCount", StudCount);
			}else if (reportname.equals("QuizSummary")) {
				hmapParam.put("Cid", Cid);				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT count(*) as quizCount FROM quiz q, quizrecord qr, quizquestion qq where q.CourseID= '"+Cid+"' and qr.QuizID = q.QuizID and qq.QuizID = q.QuizID");
				if(rs.next()){
					NoofQuiz = rs.getString("quizCount");
				}
				hmapParam.put("NoofQuiz", NoofQuiz);
			}else if (reportname.equals("StudentQuery")) {
				hmapParam.put("Cid", Cid);				
				hmapParam.put("queryDate", queryDate);
			}else {
				hmapParam.put("Cid", Cid);
			}
			jasPrint = JasperFillManager.fillReport(jasReport, hmapParam, con);
			String data;
			BufferedReader fbr;
			// Appending PDF download link in report
			file.append("<div align=\"right\"><a href='DownloadPDF?reptype=course&cid="
					+ Cid
					+ "&ATS="
					+ ATS
					+ "&StudCount="
					+ StudCount
					+ "&AttSummary="
					+ AttSummary
					+ "&NoofQuiz="
					+ NoofQuiz
					+ "&queryDate="
					+ queryDate
					+ "&repname="
					+ reportname
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
		} catch (Exception ex) {
		}
		return file.toString();
	}

}
