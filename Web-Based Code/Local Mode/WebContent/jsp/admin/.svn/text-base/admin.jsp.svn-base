<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
<link href="../../jquery-ui-1.8.21.custom.css" rel="stylesheet"	type="text/css" />
<link href="../../styles.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/jquery-ui.min.js"></script>

<script type="text/javascript">
function getStudent(){
	var url=$("#file").val();
	if(url.lastIndexOf("\\")!=-1){
		url=url.slice(url.lastIndexOf("\\")+1);
	}
	alert(url);	
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			xmlhttp.responseText;
		}
	};
	xmlhttp.open("GET", "studentxlsparser.jsp", true);
	xmlhttp.send();
}
</script>
</head>
<body>
	<center>
		<%@ include file="../newMenu/newMenuwithCSS.jsp"%> 		 
		<div id="content_in">
			<form action="studentxlsparser.jsp" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
		</div>
		<%@ include file="../../jsp/includes/footer.jsp"%>
	</center>
</body>
</html>