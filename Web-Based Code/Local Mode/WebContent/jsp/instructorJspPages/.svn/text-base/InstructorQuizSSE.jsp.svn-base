<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%
response.setHeader("pragma", "no-cache,no-store");  
response.setHeader("cache-control", "no-cache,no-store,max-age=0,max-stale=0");  
response.setContentType("text/event-stream;charset=UTF-8");
int messagesSent=0;
String status = request.getParameter("Status");
if(session.getAttribute("InstructorID")==null && session.getAttribute("StudentID")==null){
	return;
}

if (status==null) {
	if(session.getAttribute("previousThread")!=null){
	Thread previousThread = (Thread)session.getAttribute("previousThread");
	previousThread.interrupt();}
	Thread currentThread = Thread.currentThread();
	session.setAttribute("previousThread", currentThread);
	while(!currentThread.isInterrupted()){
	if(application.getAttribute("SSE"+session.getAttribute("courseID"))!=null){
		if(application.getAttribute("SSE"+session.getAttribute("courseID")).equals("Started")){
			System.out.println("Get SSE !!!");
			String courseID = session.getAttribute("courseID").toString();
			session.setAttribute("StudentCourse",courseID);		
			if(session.getAttribute("ResultAccessCount")!=null){
				session.removeAttribute("ResultAccessCount");
			}
			if(session.getAttribute("TempQuestionResponseArray")!=null){
				session.removeAttribute("TempQuestionResponseArray");
			}
			out.print("data: Get Start\n\n");
			out.flush();
			break;
		}else if(application.getAttribute("SSE"+session.getAttribute("courseID")).equals("InstantQuizStarted")){
			System.out.println("Get Instant Quiz SSE !!!");
			out.print("data: Get Instant Quiz Start\n\n");
			out.flush();
			break;
		}
	}
	try {
		Thread.sleep(5000);
    } catch (InterruptedException e) {
        return;
    }	
	}
}else if (status.equals("start")){
	String cid= request.getParameter("cid");
	application.setAttribute("SSE"+cid, "Started");
	System.out.println("Start !!!");
}
else if (status.equals("startInstant")){
	String cid= request.getParameter("cid");
	application.setAttribute("SSE"+cid, "InstantQuizStarted");
	ConcurrentHashMap <String, String> InstantResponse = new ConcurrentHashMap<String, String>();
	application.setAttribute( cid + "InstantResponse", InstantResponse);		
	application.setAttribute(cid + "InstantResponseCount", 0);
	System.out.println("Start Instant Quiz !!!");
	int noofoptions = Integer.parseInt(request.getParameter("nunoptions"));
	int durationSec = Integer.parseInt(request.getParameter("time"));
	String correctAns = request.getParameter("cor_resp");
	session.setAttribute(cid + "cor_ans", correctAns);
	String instrID = session.getAttribute("InstructorID").toString();
	application.setAttribute(cid + "optiontime", noofoptions + ":" + "00:" + durationSec );
	DatabaseConnection dbcon = new DatabaseConnection();
	Connection con = dbcon.createDatabaseConnection();
	PreparedStatement pstmt = null;
	try{
		pstmt = con.prepareStatement("Insert into instantquiz(NoofOptions,CorrectAns,InstrID, DurationSec, CourseID) values(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, noofoptions);
		pstmt.setString(2, correctAns);
		pstmt.setString(3, instrID);
		pstmt.setInt(4, durationSec);	
		pstmt.setString(5, cid);
		pstmt.executeUpdate();
		ResultSet keyResultSet = pstmt.getGeneratedKeys();
        int iquizid = 0;
        if (keyResultSet.next()) {
        	iquizid = (int) keyResultSet.getInt(1);            
        }
        System.out.println("Instant Quiz ID  = " + iquizid);
        session.setAttribute("iquizid", iquizid);
	}catch(SQLException ex){
		ex.printStackTrace();
	}finally{
		pstmt.close();
		con.close();
	}
	
}
else if (status.equals("stop")){	
	String cid= request.getParameter("cid").toString();
	application.removeAttribute("SSE"+cid);
	System.out.println("Stoped !!!");	
}else if (status.equals("stopInstant")){	
	String cid= request.getParameter("cid").toString();
	application.removeAttribute("SSE"+cid);
	System.out.println("Stoped !!!");	
}

%>