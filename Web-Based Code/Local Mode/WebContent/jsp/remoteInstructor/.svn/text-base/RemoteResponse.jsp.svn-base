<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control", "no-cache"); //forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
}

String QuizID = application.getAttribute(InstructorID+"QuizID").toString();

String CorrectAnswer[]=(String[])session.getAttribute(InstructorID + "CorrectAnswer");
String CorrectAnswerString="";

Vector<Question> Questiondetails = (Vector <Question>)application.getAttribute(InstructorID+"Questiondetails");
for (int i=0; i<Questiondetails.size(); i++)
{
	CorrectAnswerString = CorrectAnswerString + CorrectAnswer[i]+"/";
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/RemoteResponses.js">
</script>
<script type="text/javascript">
var CorrectAnswer = '<%=CorrectAnswerString%>';
</script>
<title>Remote Center Responses</title>
</head>
<body onload="responseType()">
	<center>
		<%@ include
			file="../../jsp/newMenu/newMenuForRemoteInst.jsp"%>
		<div id="content_in">
			<div style="min-height: 425px; font-weight: bold">
				<center>
					<div style="height: 20px"></div>
					<div id="respquizid_hidden" style="visibility: hidden"></div>
					<h2 align="center">Remote Center Responses</h2>
					<div style="margin-top: 20px" id="responseSummary"></div>
					<div id="Responsediv"></div>
					<table align="center">
						<tr>
							<td>
								<div style="float: left;">
									<form action="" name="bar" method="post">
										<input type="text" name="barqueryString" id="barqueryString"
											hidden="true" style="visibility: hidden; width: 20px" /> <input
											type="button" value="Correct Bar Chart" name="barchart"
											onclick="showCorrectBarChart(CorrectAnswer);" />
									</form>
								</div>
							</td>
							<td>
								<div style="float: left;">
									<form name="pie" method="post">
										<input type="text" name="piequeryString" id="piequeryString"
											hidden="true" style="visibility: hidden; width: 20px" /> <input
											type="button" value="Pie Chart" name="piechart"
											onclick="showPieChart();" />
									</form>
								</div>
							</td>
							<td>
								<div style="float: left;">
									<form action="" name="bar" method="post">
										<input type="text" name="barqueryString" id="barqueryString"
											hidden="true" style="visibility: hidden; width: 20px" /> <input
											type="button" value="Bar Chart" name="barchart"
											onclick="showBarChart();" />
									</form>
								</div>
							</td>

							<td>
								<div id="saveResponse_div"
									style="margin-left: 50px; float: left; visibility: hidden">
									<input type="button" id="saveresponse" value="Save Response"
										onclick="saveResponse();" />
								</div>
							</td>
						</tr>
					</table>
					<div id="respquizid_hidden" style="visibility: hidden"></div>
					<div id="responseValue_hidden" style="visibility: hidden"></div>
				</center>
			</div>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>