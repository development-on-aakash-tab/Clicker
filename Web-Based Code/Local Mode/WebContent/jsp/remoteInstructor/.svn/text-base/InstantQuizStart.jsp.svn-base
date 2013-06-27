<%@page import="java.sql.*"%>
<%@page import="com.clicker.databaseconn.DatabaseConnection"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="com.clicker.util.QuizParamXML"%>
<%@page import="com.clicker.util.RemoteXML"%>
<%@page import="java.io.*"%>
<%
int noofoptions = Integer.parseInt(request.getParameter("nunoptions"));
int durationSec = Integer.parseInt(request.getParameter("time"));
String correctAns = request.getParameter("cor_resp");
session.setAttribute("cor_ans", correctAns);
String instrID = session.getAttribute("InstructorID").toString();
DatabaseConnection dbcon = new DatabaseConnection();
Connection con = dbcon.createDatabaseConnection();
PreparedStatement pstmt = null;
int iquizid = 0;
try{
	pstmt = con.prepareStatement("Insert into remoteinstantquiz(NoofOptions,CorrectAns,InstrID, DurationSec) values(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
	pstmt.setInt(1, noofoptions);
	pstmt.setString(2, correctAns);
	pstmt.setString(3, instrID);
	pstmt.setInt(4, durationSec);	
	pstmt.executeUpdate();
	ResultSet keyResultSet = pstmt.getGeneratedKeys();
    if (keyResultSet.next()) {
    	iquizid = (int) keyResultSet.getInt(1);            
    }
    System.out.println("Remote Instant Quiz ID  = " + iquizid);
    session.setAttribute("iquizid", iquizid);
    session.setAttribute("cor_ans", correctAns);
}catch(SQLException ex){
	ex.printStackTrace();
}finally{
	pstmt.close();
	con.close();
}

String filepath = getServletContext().getRealPath("/");
File file = new File(filepath + "sversion.txt");

if (!file.exists()) {
    try {
        file.createNewFile();
        Writer output = new BufferedWriter(new FileWriter(file));
        output.write("0");
        output.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
else
{
	 try {
	        file.delete();
	        file.createNewFile();
		 	Writer output = new BufferedWriter(new FileWriter(file));
	        output.write("0");
	        output.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }	
}

RemoteXML remoteXML = new RemoteXML();
remoteXML.makeClickerRemoteResponseArchive();

String Filepath = getServletContext().getRealPath("/");
QuizParamXML.createQuizXML(Filepath, Integer.toString(iquizid), Integer.toString(durationSec), noofoptions, correctAns);
session.setAttribute("ResponseReceivedRCID", "");
session.setAttribute("ResponseReceivedFiles", "");
session.setAttribute("qztyp", "instant");
%>