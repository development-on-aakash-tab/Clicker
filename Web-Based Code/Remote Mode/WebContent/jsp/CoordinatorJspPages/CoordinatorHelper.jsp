<%@page import="com.clicker.databaseconn.DBHelper"%>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

String helperText = request.getParameter("helperText");
if(helperText!=null){
	DBHelper dbHelper = new DBHelper();
	if(helperText.equals("participantList")){
		String participantList[] = dbHelper.getParticipantList().split(";");
		String participantTable = "<table border='1' style='margin-top:10px;margin-left:auto;margin-right:auto;width:60%;'>"+
		"<tr><th>Participant ID</th><th>Delete</th></tr><tr><td align='center' style='color:#aa0000;'>Delete All</td><td align='center'><input type='checkbox' onChange='deleteAllAction()' id='plistall' name='plist' value='all'></td></tr>";
		for(int i=0;i<participantList.length;i++){
			participantTable += "<tr><td align='center'>" + participantList[i] + 
					"</td><td align='center'><input type='checkbox' id='plist"+i+"' name='plist' value='"+participantList[i]+"'></td></tr>";
		}
		participantTable += "</table>";
		out.println(participantTable);
	}	
	else if(helperText.equals("deleteParticipantList")){
		String action = request.getParameter("action");		
		dbHelper.deleteParticipantList(action);
		out.println("Successfully deleted");
	}
}
%>