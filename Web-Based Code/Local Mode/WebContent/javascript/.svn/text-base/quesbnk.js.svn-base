function validate(){

	if(document.frm.question.value=="")
	{
		alert("Please enter question");
		document.frm.question.focus();
		return false;
	}
}
function ques(val){
var url="questionbank.jsp";
  var qtype = document.getElementById("qtype").value;
 var quest=document.getElementById("quest").value;
        if(val=="addxls")
		{
          window.location.href="uploadxls.jsp";
		}
		else if(val=="addnew")
		{
		   if(qtype!="-1")
			{
            	{
            		if(qtype=="0")
            			{
            			window.location.href="singleanswer.jsp";
            			}
            		else if(qtype=="1")
            			{
            			window.location.href="MultChoice.jsp";
            			}
            		else if(qtype=="2")
        			{
        			window.location.href="trueFalse.jsp";
        			}
            		else if(qtype=="3")
        			{
        			window.location.href="integer.jsp";
        			}
            	}
			}
             
			else
			{
				 alert("Please select question type");
			}
		}
		else if(val=="delete")
	   {
			 if(quest!="-1")
			{
             var url = "../../jsp/QuestionBank/delete.jsp?id="+quest;
			  window.location.href=url;
			}
	   
			else
			{
				 alert("Please select question ");
			}
	   }
			 else if(val=="edit")
			   {
					 if(quest!="-1")
					{
		             var url = "../../jsp/QuestionBank/edit.jsp?id="+quest;
					  window.location.href=url;
					}
					else
					{
						 alert("Please select question type");
					}
	           }
}