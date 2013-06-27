<%@page import="java.net.URL"%>
<%@page import="com.clicker.util.*"%>
<%
if (ServerIPCheck.isInternetReachable())
{
	//URL urlstring = new URL("http://localhost:8080/AakashClickerV3/sversion.txt");
	URL urlstring = new URL("http://www.it.iitb.ac.in/clicker/sversion.txt");
	String s = URL_ReadPage.readPageText(urlstring);
	int serverversion = Integer.parseInt(s);
	if (serverversion != 0) {		
		out.print(1);
	}else{
		out.print(0);
	}
}else{
	out.print(0);
}
%>