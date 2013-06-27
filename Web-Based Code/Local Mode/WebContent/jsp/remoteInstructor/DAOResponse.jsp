<%@page import="com.clicker.util.ResponseHelper"%>
<%@page import="com.clicker.util.RemoteCenterResponse"%>
<%@page import="com.clicker.databaseconn.*"%>
<%@page import="com.clicker.databaseconn.DatabaseQueries"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="com.clicker.wrappers.*"%>

<%
System.out.println("Inside DAOResponse.jsp");
String InstructorID = (String) session.getAttribute("InstructorID");

if (InstructorID == null) {
	request.setAttribute("Error",
			"Your session has expired.");
	RequestDispatcher rd = request
			.getRequestDispatcher("../../jsp/remoteInstructor/InstructorError.jsp");
	rd.forward(request, response);
}
String QuizID = "";
if(session.getAttribute("qztyp").toString().equals("normal")){
	if(application.getAttribute(InstructorID+"QuizID")!=null){
    	QuizID = application.getAttribute(InstructorID+"QuizID").toString();
	}
}
else if (session.getAttribute("qztyp").toString().equals("instant"))
{
	if(session.getAttribute("iquizid")!=null){
		QuizID = session.getAttribute("iquizid").toString();
	}
}
String ResponseType = request.getParameter("ResponseType").toString();

String path = System.getProperty("user.home") + "/ClickerRemoteFiles/Received/";

if (ResponseType.equals("ResponseStatus"))
{
	String existingRCID="";
	String existingFiles ="";
	String RCIDsFiles ="";
	if(session.getAttribute("ResponseReceivedRCID")==null){
		RCIDsFiles = RemoteCenterResponse.getResponseStatus(QuizID, path, existingRCID);
		String[] resparr = RCIDsFiles.split("@#");
		if(resparr.length>=1){
			existingRCID += resparr[0]; 
			existingFiles += resparr[1];
		}
		session.setAttribute("ResponseReceivedRCID", existingRCID);
		session.setAttribute("ResponseReceivedFiles", existingFiles);
	}else{
		existingRCID = session.getAttribute("ResponseReceivedRCID").toString();
		existingFiles = session.getAttribute("ResponseReceivedFiles").toString();
		RCIDsFiles = RemoteCenterResponse.getResponseStatus(QuizID, path, existingRCID);
		String[] resparr = RCIDsFiles.split("@#");
		if(resparr.length>=1){
			existingRCID += resparr[0]; 
			existingFiles += resparr[1];
		}
		session.setAttribute("ResponseReceivedRCID", existingRCID);
		session.setAttribute("ResponseReceivedFiles", existingFiles);
	}
	out.print(RCIDsFiles);
	System.out.println(RCIDsFiles);
}
else if (ResponseType.equals("getExistingRCStatus"))
{
	String existingRCID="";
	String existingFiles="";
	if(session.getAttribute("ResponseReceivedRCID")!=null){
		existingRCID = session.getAttribute("ResponseReceivedRCID").toString();
		existingFiles = session.getAttribute("ResponseReceivedFiles").toString();
	}
	out.print(existingRCID + "@" + existingFiles);
	System.out.println("------"+ existingRCID + "@" + existingFiles);
}
else if (ResponseType.equals("Response"))
{
	String fileName = request.getParameter("fileName");
	String centerResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>Center ID</td><td>Question ID</td><td>Participant ID</td><td>Response</td></tr>";
    centerResponses += RemoteCenterResponse.parseXMLFile_to_Table(fileName, path);
    centerResponses += "</table>";
    System.out.println("In DAO" + centerResponses);
    out.print(centerResponses);
}
else if (ResponseType.equals("AllResponses"))
{
	String fileNames = request.getParameter("fileNames");
    String files[] = fileNames.split(";");
    String allCenterResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>Center ID</td><td>Participant ID</td><td>Response</td></tr>";
    for (int i = 0; i < files.length; i++) {
        allCenterResponses += RemoteCenterResponse.parseXMLFile_to_Table(files[i], path);
    }
    allCenterResponses += "</table>";
    System.out.println("In DAO" + allCenterResponses);
    out.println(allCenterResponses);
}
else if (ResponseType.equals("QuestionResponse"))
{
	int centerid = Integer.parseInt(request.getParameter("centerid"));
	String centerResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>S.No</td><td>Participant ID</td><td>Response</td></tr>";
	String questionID = request.getParameter("questid");
    //centerResponses += RemoteCenterResponse.parseXMLFile_to_Table(fileName, path, questionID, 1).split("%8%")[0];
    String quizRecordID = session.getAttribute("QuizRecordID").toString();
    centerResponses +=RemoteCenterResponse.getRCQuestionResponse_Table(centerid, questionID, quizRecordID);
    centerResponses += "</table>";
    System.out.println("In DAO" + centerResponses);
    out.println(centerResponses);
}
else if (ResponseType.equals("QuestionAllResponse"))
{
	//String fileName = request.getParameter("fileName");
	//String files[] = fileName.split(";");
    String allCenterResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>S.No</td><td>Center ID</td><td>Participant ID</td><td>Response</td></tr>";
    String questionID = request.getParameter("questid");
    String quizRecordID = session.getAttribute("QuizRecordID").toString();
    allCenterResponses +=RemoteCenterResponse.getAllRCQuestionResponse_Table(questionID, quizRecordID);
    /*int count =1;
    for (int i = 0; i < files.length; i++) {
    	String resp = RemoteCenterResponse.parseXMLFile_to_Table(files[i], path, questionID, count);
    	count = Integer.parseInt(resp.split("%8%")[1]);
        allCenterResponses += resp.split("%8%")[0];
    }*/
    allCenterResponses += "</table>";
    System.out.println("In DAO" + allCenterResponses);
    out.println(allCenterResponses);
}

else if (ResponseType.equals("InstantQuizResponse"))
{
	int centerid = Integer.parseInt(request.getParameter("centerid"));
	String centerResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>S.No</td><td>Participant ID</td><td>Response</td></tr>";
	String qid = request.getParameter("qid");
    centerResponses +=RemoteCenterResponse.getRCInstantQuizResponse_Table(centerid, qid);
    centerResponses += "</table>";
    System.out.println("In DAO" + centerResponses);
    out.println(centerResponses);
}
else if (ResponseType.equals("InstantQuizAllResponse"))
{
	String allCenterResponses = "<table id='restbl' border='1' style='width: 580px;'><tr><td>S.No</td><td>Center ID</td><td>Participant ID</td><td>Response</td></tr>";
    String qid = request.getParameter("qid");
    allCenterResponses +=RemoteCenterResponse.getAllRCInstantQuizResponse_Table(qid);
    allCenterResponses += "</table>";
    System.out.println("In DAO" + allCenterResponses);
    out.println(allCenterResponses);
}
else if (ResponseType.equals("SaveResponse"))
{
	String fileNames = request.getParameter("filenames");
	System.out.println("fileNames--" + fileNames);
	String quiztype = request.getParameter("quiztype");
	fileNames = fileNames.trim();
	int count=0;
	if(!fileNames.equals("")){
	String [] filenames_array = fileNames.split(";");	
	if(quiztype.equals("normal")){
		Vector<Question> Questiondetails = new Vector<Question>();
		Questiondetails = (Vector<Question>) application.getAttribute(InstructorID+"Questiondetails");
		int quizrecordID = Integer.parseInt(session.getAttribute("QuizRecordID").toString());
		
		for(int i=0;i<filenames_array.length;i++){		
			count += RemoteCenterResponse.saveXMLFile_to_DB(filenames_array[i].trim(), path, Questiondetails, quizrecordID);
		}
	}else if (quiztype.equals("instant")){
		int iquizid = Integer.parseInt(session.getAttribute("iquizid").toString()); 
		for(int i=0;i<filenames_array.length;i++){		
			count += RemoteCenterResponse.saveXMLFile_to_DB(filenames_array[i].trim(), path, iquizid);
		}
	}
    System.out.println("In DAO" + count);
	}
    out.println(count);
}
else if (ResponseType.equals("Save")) 
{
    String responseValues = request.getParameter("responseValues");
    System.out.println(responseValues);
    String[] responseRows = responseValues.split("@");
    String[] responseColumn;    
    int successfullinsert = 0;    
    int Quiz_ID = Integer.parseInt(QuizID);
    
    Timestamp QuizTimeStamp = (Timestamp)session.getAttribute("QuizTimestamp");
    for (int i = 1; i < responseRows.length; i++) {
    	DatabaseConnection dbconn = new DatabaseConnection();
    	Connection conn = dbconn.createDatabaseConnection();
        responseColumn = responseRows[i].split(";");
        int CenterID = Integer.parseInt((responseColumn[0]).toString());
        int ParticipantID = Integer.parseInt((responseColumn[1]).toString());
        QuizRecordQueries qrq = new QuizRecordQueries();
        
        System.out.println("Avail Participant ID is"+ParticipantID);
        
        boolean ParticiapntAvail = qrq.isParticipantAvailable(conn, ParticipantID);
        
        System.out.println("If availabil is---------====================-----------  "+ParticiapntAvail);
        
        if (ParticiapntAvail==false)
        {
        	String Query ="INSERT INTO participant(ParticipantID, CenterID) VALUES('"+ParticipantID+"', '"+CenterID+"')";
        	Statement stmt = conn.createStatement();
            stmt.execute(Query); 
        }
         successfullinsert += insertRemoteQuizResponseRecords(conn,
        		Quiz_ID,
        		CenterID, 
        		(responseColumn[1]), 
        		responseColumn[2],  
        		QuizTimeStamp); 
        conn.close();
    }
    out.println(successfullinsert + " Response Saved successfully");
    
}
%>

<%!
private int insertRemoteQuizResponseRecords(Connection conn, 
		int QuizID, 
		int CenterID, 
		String ParticipantID, 
		String Response, 
		Timestamp Quiztimestamp)
{
	int insertstatus = 0;	
	try
	{
		System.out.println("SQL debugging ");
		
        Statement st = conn.createStatement();
        Statement st1 = conn.createStatement();
        //ResultSet rs = st1.executeQuery("Select * from remotequizresponses where CenterID = " + CenterID + " and ParticipantID=" + ParticipantID + " and Response='" + Response + "' and ResponseTS=" + Quiztimestamp);
        //if (rs.first()==false) 
        	
       	QuizRecordQueries qrq = new QuizRecordQueries();
    
   		int QuizRecordID = qrq.getLatestRemoteQuizRecordID(conn, QuizID);   		
   		int QuestionID = qrq.getRemoteQuestionID(conn, QuizID);
   		int OptionID = qrq.getRemoteOptionID(conn, QuizID, QuestionID, Response);
        
        {
        	System.out.println(CenterID+""+ParticipantID+""+Response+""+Quiztimestamp);
            insertstatus = st.executeUpdate("INSERT INTO remotequizrecordquestion(CenterID, ParticipantID, Response, QRTimeStamp, QuizRecordID, QuestionID, OptionID) VALUES(" + CenterID + "," + ParticipantID + ",'" + Response + "','"+Quiztimestamp+"','"+QuizRecordID+"','"+QuestionID+"','"+OptionID+"')");
        }
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}	
	return insertstatus;
}

%>