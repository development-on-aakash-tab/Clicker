<%@ page import = "com.clicker.CreateQuiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! String InstrID; %>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility

	String InstructorID = (String) session.getAttribute("InstructorID");
    session.setAttribute("insid",InstructorID);
    InstrID=InstructorID;
    String courseID = (String)session.getAttribute("courseID");
	System.out.println("Instructor ID is : " + InstructorID);
	System.out.println("Course ID :"+(String)session.getAttribute("courseID"));
	System.out.println((String)session.getAttribute("Usertype"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Quiz</title>
<link rel="stylesheet" media="all" type="text/css" href="../../jsp/newMenu/dropdown.css" />
<link rel="stylesheet" href="../../djstyles.css" type="text/css" media="all"/>
<script type="text/javascript" src="CreateQuiz.js">
</script>
<script type="text/javascript">
	function validate(InstrID) {
		document.getElementById('InstrID').value=InstrID;
		var quiztype = document.getElementById('QuizTypeName').value;
		var x = document.forms["form2"].elements["quizName"].value;
		var z = document.getElementById("select2").value;
		var y = document.forms["form2"].elements["durationM"].value;
		var i = 1;
		var cnt = document.getElementById("count").value;
		if (cnt == 0) {
			alert("Please add some questions");
			return false;
		}
		else if (x == null || x == "" || x.trim() == "") {
			alert("Please Fill Quiz name!!!");
			return false;

		} else if (y == null || y == "" || y.trim() == "" || isNaN(y) == true) {
			alert("Invalid time");
			return false;
		}
		else if (quiztype != 'Normal' && cnt != i) {
			alert("Note: Spot quiz contain only one question.");

			return false;
		} else {
			alert("You have successfully Added a new Quiz.\nThank you");
		}
		
	}	
	
	
	function onloadDisableField(InstrID){
	
		document.getElementById('InstrID').value=InstrID;
		document.getElementById('select2').disabled=true;
		document.getElementById('filter').disabled=true;
		document.getElementById('select1').disabled=true;
		document.getElementById('button1').disabled=true;
		document.getElementById('button2').disabled=true;
		document.getElementById('quizName').disabled=true;
		document.getElementById('qt').disabled=true;
		document.getElementById('sub').disabled=true;
		document.getElementById('questiontype').disabled=true;
		
		document.getElementById("dqhead").style.display = 'none';
		document.getElementById("createquiz").style.color = '#FF0000';
		document.getElementById("deletequiz").style.color = '#0000FF';
		document.getElementById("cqhead").style.display = 'block';
		
	}
	
</script>
</head>
<body onload="onloadDisableField('<%=InstrID%>')">

<% if(session.getAttribute("Mode").equals("Remote")){ %>
<%@ include
			file="../newMenu/newMenuForRemoteInst.jsp"%>
<%}else{ %>
	<%@ include file="../../jsp/raisehand/RaiseHandSideBar.jsp" %>
	<%@ include file="../newMenu/newMenuwithCSS.jsp"%>
<%}%>

<div id="content_in">
<br/>
<div id="createquiz" style="width: auto; text-shadow:3px 3px 3px #88FFFF; float: left; margin: 10px 100px; cursor: pointer;" onclick="cq();" >Create Quiz </div>
<div id="deletequiz" style="width: auto; text-shadow:3px 3px 3px #88FFFF; float: left; margin: 10px 100px; cursor: pointer;" onclick="dq();" >Delete Quiz</div>

<br /> <br /> <br /> 
<div style="margin-left: 0px 0px; clear: both;" align="left" id="cqhead" >

<div id="QuizType" style="background-color:#C0C0C0 ">
<label style=" color:#000099;">Firstly, Please Select Quiz type :</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp<input type="radio" class="radio-btn" value="SpotQuiz" name="QuizType" id="QuizType"
								title="Select to create SpotQuiz" onclick="getQuizTypeAndEnableFields('1')"/>&nbsp<label for="text1"
								title="Select to create SpotQuiz">Spot Quiz</label>&nbsp&nbsp
								<input type="radio" class="radio-btn" value="NormalQuiz" name="QuizType" id="QuizType"
								title="Select to create Normal Quiz" onclick="getQuizTypeAndEnableFields('2')"/>&nbsp<label for="text1"
								title="Select to create Normal Quiz">Normal Quiz</label><br /> 
</div>
<br/>

	<center>
	<p>Search Questions: <textarea style="margin-bottom:;" class="inputtext" name="question" id="filter" cols="60" rows="1" onkeyup="loadQuestions()"></textarea></p>
	</center>
<div> Select the type of question: <select id = "questiontype" onchange = "loadQuestions('<%=InstrID%>')" >
											<option value = "0" > All Questions </option>
											<option value = "1"> Single Choice correct </option>
											<option value = "2" > Multiple Choice correct </option>
											<option value = "3" > Numerical Answer </option>
											<option value = "4" > True or False </option>
									  	</select> </div>
<form id="f1" action="../../quizCreator" name="form2" method="POST" onsubmit="return validate('<%=InstrID%>');">
				<input type="hidden" name="QuizTypeName" id="QuizTypeName">
				<input type="hidden" name="InstrID" id="InstrID">
    <br>
    <table>
    	<tr>
    		<td>
    			<p style="text-align: center;">Available Questions</p>
    			<select size="10" name="select1" id="select1" multiple="multiple" onchange="loadOptions(1)" >
					<script type="text/javascript">
						loadQuestions('<%=InstrID%>');
					</script>
				</select>
			</td> 
			<td align="center" id="buttonstd">
				<table >
					<tr>
						<td align="center">
							<input class="buttons" type="button" id="button1" onclick="addOrRemove(1)" value=" >> "/>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input class="buttons" type="button" id="button2" onclick="addOrRemove(2)" value=" << "/>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<p style="text-align: center;">Quiz Questions</p>
				<select size="10" name="select2" id="select2" onchange="loadOptions(2)" >
				</select>
			</td>
		</tr>
	</table>
	<br/>	
	<div>
		<p style="margin-left: 120px;">Options</p>
		<div id="optionsDiv"></div>	
		
		<div id="nameDuration">
			<p>Quiz Name: <input class="inputtext" style="width: 300px;" type="text" name="quizName" id="quizName"/></p> 
<!-- 			<p>Duration: &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" name="durationH" class="dur" size="2" maxlength="2"/>:<input type="text" name="durationM" class="dur" size="2" maxlength="2"/>:<input type="text" name="durationS" class="dur" size="2" maxlength="2"/></p> --><br>
			<p>Duration: &nbsp;&nbsp;&nbsp;&nbsp;<input style="width: 50px;" class="inputtext" type="text" id="qt" name="durationM" size="2" maxlength="2"/> (mins)</p>
			<input type="hidden" value="0" id="count" name="count"/>
			<br>
			<center><input type="submit" value="Create Quiz" id="sub"/></center>
		</div>
	</div>
	

</form>
</div>


<div style="margin-left: 0px 0px; clear: both;" align="left" id="dqhead" >
<center><div id="QuizTypeSelect" style="background-color:#C0C0C0 ">
<label style=" color:#000099;">Firstly, Please Select Quiz type :</label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
&nbsp&nbsp&nbsp<input type="radio" class="radio-btn" value="SpotQuiz" name="QuizType" id="QuizType"
								title="Select to Delete SpotQuiz" onclick="deletequizenable('2', '<%=courseID%>')"/>&nbsp<label for="text1"
								title="Select to Delete SpotQuiz">Spot Quiz</label>&nbsp&nbsp
								<input type="radio" class="radio-btn" value="NormalQuiz" name="QuizType" id="QuizType"
								title="Select to Delete Normal Quiz" onclick="deletequizenable('1',  '<%=courseID%>')"/>&nbsp<label for="text1"
								title="Select to Delete Normal Quiz">Normal Quiz</label><br /> 
</div>

<br /> <div id = "QuizNameSelect"> </div>
</center>
</div>
</div>
			<center><%@ include file="../../jsp/includes/footer.jsp" %>
</center>
</body>
</html>