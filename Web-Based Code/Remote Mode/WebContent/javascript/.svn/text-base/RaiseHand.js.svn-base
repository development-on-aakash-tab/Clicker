
function validateCourseID(){
	try{
		if(document.forms["CourseIDform"].elements["courseID"].value=="Select Course"){
			alert("Select a valid course");
			return false;
		}
		else{
			return true;
		}
	}
	catch(err){
		alert(err.message);
	}
}
$(document).ready(function(){
	checkValidityOfStudForm();
});
var previousValidity=true;
function checkValidityOfStudForm(){
	$.get("../../studentRaiseInvalid",function(result){
		if(result=="true"){
			if(previousValidity==false){
				window.location.reload();	
			}
			previousValidity=true;
		}
		else{
			$("#formdiv").html(result);
			previousValidity=false;
		}
		
	});
	setTimeout("checkValidityOfStudForm()",4000);
}

