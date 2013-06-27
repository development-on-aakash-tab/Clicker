<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../../jquery-ui-1.8.21.custom.css" rel="stylesheet"	type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<script type="text/javascript" src="../../javascript/jquery.js"></script>
<script type="text/javascript" src="../../javascript/jquery-ui.min.js"></script>



</head>

<script type="text/javascript">


</Script>


<body onload="open_page()">
<center>

   <%@ include file="../newMenu/newMenuwithCSS.jsp" %>
   </center>
   
 <style type="text/css">

#cssmenu {font-family: arial, sans-serif;text-align:center; width:auto; height:30px;font-weight:bold; font-size:32px;border: 1}
#cssmenu ul li a, #cssmenu ul li a:visited {display:block; text-decoration:none; color:#000; padding:0px 15px 0px 15px; height:25px; text-align:center; color:#fff ; border:1.5px outset ;border-bottom-color:red; background:#996515; line-height:20px; font-size:15px; overflow:hidden;} /*Main menu display on page load */
#cssmenu ul li a.submenu{width:112px;}
#cssmenu ul {padding:0; margin:0; list-style: none;}
#cssmenu ul li {float:left; position:relative;}
#cssmenu ul li ul {display: none;}
#cssmenu ul li:hover a {color:#000000; background:#C8B560;} /*When menu selected Background */
#cssmenu ul li:hover ul {display:block; position:absolute; top:25px; left:0; width:115px;}
#cssmenu ul li:hover ul li a.hide {background:#6a3; color:#fff;}
#cssmenu ul li:hover ul li:hover a.hide {background:#6fc; color:#000;}
#cssmenu ul li:hover ul li ul {display: none;}
#cssmenu ul li:hover ul li a {display:block; background:#996515; color:#fff;} /*For submenu */
#cssmenu ul li:hover ul li a:hover {background:#C8B560; color:#000000;} /*For Submenu Selection*/
#cssmenu ul li:hover ul li:hover ul {display:block; position:absolute; left:105px; top:0;}
#cssmenu ul li:hover ul li:hover ul.left {left:-105px;}
</style>
    <div align="center">
      
    <div id='cssmenu' >
    <ul>
   <li class='active '><a href='courses.jsp'><span>Course</span></a></li>
   <li><a href='department.jsp'><span>Department</span></a></li>
   <li><a href='instructor.jsp'><span>Instructor</span></a></li>
   <li><a href='student.jsp'><span>Student</span></a></li>
</ul>

</div>
</div>
    
 <center>  
    <%@ include file="../../jsp/includes/footer.jsp"%>
</center>
</body>
</html>