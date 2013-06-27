
ctr=0;
function loadQuestions(InstID){
	var xmlhttp;
	var val = (document.getElementById("questiontype").options[document.getElementById("questiontype").selectedIndex].value);
	var InstrID=InstID;
	if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{

		if(xmlhttp.readyState==4 && xmlhttp.status==200){

			document.getElementById("select1").innerHTML=xmlhttp.responseText;
			//removing options from select box 1 that are common in both select boxes
			var selectBox1 = document.getElementById("select1");
			var selectBox2 = document.getElementById("select2");
			var i,j;
			for(i=selectBox2.options.length-1;i>=0;i--){
				for(j=selectBox1.options.length-1;j>=0;j--){
					if(selectBox1.options[j].value==selectBox2.options[i].value)
						selectBox1.removeChild(selectBox1.options[j]);
				}
			}
		}
	}

	xmlhttp.open("GET","../../retrieveQuestions?question="+document.getElementById("filter").value + "&qtype=" + val+"&InstrID="+InstrID,true);
	xmlhttp.send();
}
function loadOptions(choice){
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp=new XMLHttpRequest();
	}
	else{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById("optionsDiv").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","../../retrieveOptions?questionID="+(document.getElementById("select"+choice).options[document.getElementById("select"+choice).selectedIndex].value),true);
	xmlhttp.send();

}
function clearOptions(){
	document.getElementById("optionsDiv").innerHTML="";
}
function addOrRemove(choice){
	try{
		var i;
		var flag=false;
		var selectBox=document.getElementById("select"+choice);
		var secondBox=1+(choice%2);
		for(i=selectBox.options.length-1;i>=0;i--){
			if(selectBox.options[i].selected){
				flag=true;
				break;
			}
		}	
		if(!flag){
			alert("Select a question first");
			return;
		}
		if(choice==1){
			ctr++;
			document.getElementById("count").value=ctr;
		}
		var createdOption=document.createElement("option");
		createdOption.setAttribute("value", selectBox.options[selectBox.selectedIndex].value);
		createdOption.setAttribute("id", selectBox.options[selectBox.selectedIndex].value);



		if(choice==1){
			addHiddenField(selectBox.options[selectBox.selectedIndex].value);
		}
		else{
			for(i=selectBox.options.length-1;i>=0;i--){
				if(selectBox.options[i].selected)
					removeHiddenField(i+1);
			}	
		}
		createdOption.setAttribute("name", selectBox.options[selectBox.selectedIndex].value);
		createdOption.innerHTML=selectBox.options[selectBox.selectedIndex].text;
		document.getElementById("select"+secondBox).appendChild(createdOption);
		for(i=selectBox.options.length-1;i>=0;i--){
			if(selectBox.options[i].selected)
				selectBox.remove(i);
		}
		if(choice==2){
			ctr--;
			document.getElementById("count").value=ctr;
		}
	}catch(err){
		alert(err.message);
	}
}
function addHiddenField(val){
	try{
		var hiddenField=document.createElement("input");
		hiddenField.setAttribute("type","hidden");

		hiddenField.setAttribute("name", ""+ctr);
		hiddenField.setAttribute("id", "hidden"+ctr);
		hiddenField.setAttribute("value",""+val);
		var before=document.getElementById("sub");
		var par=before.parentNode;
		par.insertBefore(hiddenField,before);
	}catch(err){
		alert(err.message);
	}
}
function removeHiddenField(val){
	var i;
	for(i=val;i<ctr;i++)
	{	
		document.getElementById("hidden"+i).value=document.getElementById("hidden"+(i+1)).value;
	}
	var field=document.getElementById("hidden"+ctr);
	var par=field.parentNode;
	par.removeChild(field);
}


function onloadDisableField(){
	
	document.getElementById('select2').disabled=true;
	document.getElementById('filter').disabled=true;
	document.getElementById('select1').disabled=true;
	document.getElementById('button1').disabled=true;
	document.getElementById('button2').disabled=true;
	document.getElementById('quizName').disabled=true;
	document.getElementById('qt').disabled=true;
	document.getElementById('sub').disabled=true;
	document.getElementById('questiontype').disabled=true;
	
}

function getQuizTypeAndEnableFields(input){
	
	if (input == "2"){
		document.getElementById('QuizTypeName').value="Normal";
		document.getElementById('filter').disabled=false;
		document.getElementById('select2').disabled=false;
		document.getElementById('select1').disabled=false;
		document.getElementById('button1').disabled=false;
		document.getElementById('button2').disabled=false;
		document.getElementById('quizName').disabled=false;
		document.getElementById('qt').disabled=false;
		document.getElementById('sub').disabled=false;
		document.getElementById('questiontype').disabled=false;
		
	}
	else{
		document.getElementById('QuizTypeName').value="Spot";
		document.getElementById('filter').disabled=false;
		document.getElementById('select1').disabled=false;
		document.getElementById('select2').disabled=false;
		document.getElementById('button1').disabled=false;
		document.getElementById('button2').disabled=false;
		document.getElementById('quizName').disabled=false;
		document.getElementById('qt').disabled=false;
		document.getElementById('sub').disabled=false;
		document.getElementById('questiontype').disabled=false;
		
	}
}

function spotQuizValidation(){

	var Quiztype=document.getElementById('QuizTypeName').value;
	
	if(Quiztype!="Spot"){
		alert("You have Sucessfully Added New Quiz \n Thankyou");
	}
	else{
		var cnt=document.getElementById("count").value;
		//alert(cnt);
		if(cnt==0)
		{
			alert("Please add some questions");
			return false;
		}
	}

}

function deletequizenable(input, CourseID)
{
	if (input != "2")
	{
		
		var quiztype = 1;
		$('#QuizNameSelect').load("../../jsp/createquiz/QuizNameSelect.jsp?CourseID=" + CourseID + "&quiztype=" + quiztype);
	}
	else
	{
		
		quiztype = 2;
		$('#QuizNameSelect').load("../../jsp/createquiz/QuizNameSelect.jsp?CourseID=" + CourseID + "&quiztype=" + quiztype);
	}
}

function cq( )
{
	
	try
	{
		
		document.getElementById("dqhead").style.display = 'none';
		document.getElementById("createquiz").style.color = '#FF0000';
		document.getElementById("deletequiz").style.color = '#0000FF';
		document.getElementById("cqhead").style.display = 'block';
	}
	catch(err)
	{
		alert(err.message);
	}
}

function dq( )
{
	try
	{
		document.getElementById("dqhead").style.display = 'block';
		document.getElementById("createquiz").style.color = '#0000FF';
		document.getElementById("deletequiz").style.color = '#FF0000';
		document.getElementById("cqhead").style.display = 'none';
	}
	catch(err)
	{
		alert(err.message);
	}
}

/*function selectQuiz(val)
{
	Delete_Quiz dq = new Delete_Quiz( );
	dq.quiz_Name(val);
}*/