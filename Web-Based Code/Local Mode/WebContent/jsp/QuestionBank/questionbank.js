var index_1=null;
var index_2=null;
var questions=null;
function uploadXLS() {
	setTimeout("previewXLS()",1000);
	//alert("In Upload");
    var xlsFile = document.getElementById("file").files[0];
    var formdata = new FormData();
    formdata.append("file", xlsFile);
    var xhr = new XMLHttpRequest();       
    xhr.open("POST","../../fileUploader", true);
    xhr.send(formdata);
    
}

function getXlsUrl(){
	var url=$("#file").val();
	if(url.lastIndexOf("\\")!=-1){
		url=url.slice(url.lastIndexOf("\\")+1);
	}
	return url;
}

function previewXLS() {
	$("#dialog").dialog({
		modal:true,
		height:600,
		position:"top",
	    width:640,
	    autoOpen:false
	});
	var url=getXlsUrl();
	if(url!=null&&url!=""){
		$("#frame").attr("src","../../previewXLS?xls="+url);
		$("#xls").attr("value",url);
		$("#dialog").css("visibility","visible"); 	
	    $("#dialog").dialog("open",function(){
	  	    $("#dialog").css("visibility","hidden"); 	
	    }); 	
	}
	else{
		alert("Please select an XLS file first");
	}
}
function closeDialog(){
	$('#dialog').dialog("close");
}
function loadQuestion(InstrID)
{
	/*
	This code just loads the xmlhttp request object that will fetch the contents from the server.
	*/
	var xmlhttp;
	if(window.XMLHttpRequest)
		{
			xmlhttp=new XMLHttpRequest();
		}
	else
		{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	//Now we have xmlhttp object ready now we can fetch data from the server asynchronously.
	var value = (document.getElementById("questiontype").options[document.getElementById("questiontype").selectedIndex].value);
	if(document.getElementById("archived").checked)
		xmlhttp.open("GET","../../jsp/QuestionBank/getAllQuestions.jsp?archived=enabled&question_type=" + value+"&InstrID="+InstrID, true);
	else
		xmlhttp.open("GET","../../jsp/QuestionBank/getAllQuestions.jsp?question_type=" + value+"&InstrID="+InstrID, true);
	//Here the request will be send to the server.
	xmlhttp.send();
	//This function is called everytime the state changes which is typically 4 times.
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
			//questions variable contains all the questions in the question table separated by ;.
			index_1=xmlhttp.responseText.indexOf("<body>", 0);
			index_2=xmlhttp.responseText.indexOf("</body>",index_1);
			questions=(xmlhttp.responseText).trim();
			filterQuestions();
			qb();
			if(questions==""){
				alert("Question are not available for this Question type");
			}
			
			}
	}
}

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
};

function filterQuestions()
{
	var found=false;
	var myListBox=document.getElementById("quest").options;
	var htmlSelect;
	while(myListBox.length > 0)
	{
		myListBox.remove(i);
	}
	var query=document.getElementById("e").value;	
	var question=questions.split("@@");
	for(var i=0;i<question.length-1;i++)
	{
		if(question[i].indexOf(query)!=-1)
			{				
			qid=question[i].split("@~");
			htmlSelect=document.getElementById("quest");
			var option_to_be_added=document.createElement("option");
			option_to_be_added.value=qid[1];
			option_to_be_added.text=qid[0];
			option_to_be_added.title=qid[0];					
			option_to_be_added.id=qid[1];
			htmlSelect.add(option_to_be_added,null);
			}
	}
}

function User(val)
{
	var url = "quesbnk.jsp";
	var qtype = document.getElementsByName("qtype");
	var qid = document.getElementById("quest").selectedIndex;
	var y = document.getElementById("quest").options;
	var questno = "";
	var answer="";
	if(val=="addxls")
	{
         window.location.href="file.html";
	}
	else if(val=="  Edit  ")
	{
		if(qid!="-1" && y[qid].id!="undefined")
		{
			url = "edit.jsp?qid="+y[qid].id;
			window.location.href=url;
		}
		else
		{
			alert("Please select the Question");
		}
	}
	else if(val=="  ADD  ")
	{
		if(!qtype)
		{
			alert("add "+qtype[3].value);
      		if(qtype[0])
      		{
      			window.location.href="singleanswer.jsp";
      		}
      		else if(qtype[1])
      		{
      			window.location.href="MultChoice.jsp";
      		}
      		else if(qtype[2])
  			{
  				window.location.href="trueFalse.jsp";
  			}
      		else if(qtype[3])
  			{
  				window.location.href="integer.jsp";
  			}
		}
            
		else
		{
			 alert("Please select question type");
		}
	}
	else if(val=="  Delete  ")
	{
		if(qid!="-1" && y[qid].id!="undefined")
		{
			for(var i = 0; i < quest.options.length; i++)
				if(quest.options[i].selected)
					questno += quest.options[i].id + ",";
			answer=confirm('Are you sure, you want to delete this question.');
			if(answer){
           	url = "delete.jsp?id=" + escape(questno);
			window.location.href=url;
		}
		}
		else
		{
			alert("Please select a Question");
		}
   	}
	else if (val == "History")
	{
		if (qid != "-1" && y[qid].id!="undefined")
		{
			 window.location = "history.jsp?qid="+y[qid].id;
			
		}
		else
		{
			alert ("Please select a Question");
		}
	}
}
function loadOptions()
{
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
			if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
		    	document.getElementById("options").innerHTML=xmlhttp.responseText;
			}
	}
	xmlhttp.open("GET","../../retrieveOptions?questionID="+(document.getElementById("quest").options[document.getElementById("quest").selectedIndex].value),true);
	xmlhttp.send();

}
function qb()
{
try
{
	document.getElementById("addnewQ").style.display = 'none';
	document.getElementById("qbhead").style.color = '#FF0000';
	document.getElementById("addnewQhead").style.color = '#0000FF';
	document.getElementById("qb").style.display = 'block';
}
catch(err)
{
	alert(err.message);
}
	
}
function addQs()
{
	
try
{
	document.getElementById("qb").style.display = 'none';
	document.getElementById("qbhead").style.color = '#0000FF';
	document.getElementById("addnewQhead").style.color = '#FF0000';
	document.getElementById("addnewQ").style.display = 'block';
	
}
catch(err)
{
	alert(err.message);
}
}