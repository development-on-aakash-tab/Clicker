
var alpha_array=new Array("E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","W","X","Y","Z");


var ctr = 0;
var checked=false;
function validateForm()
{
	if(document.forms["form"].elements["Question"].value=="")
	{
		alert("Please enter the question first");
		return false;
	}
	for(var i=1;i<=ctr+4;i++)
		{
		//alert(document.forms["form"].elements["check"+i].checked);
			if(document.forms["form"].elements["txt"+i].value=="")
				{
				alert("Please give appropriate value for answer");
				return false;
				}
			if(document.forms["form"].elements["check"+i].checked==true)
				{checked=true;
				}
		}
	if(!checked){
		return false;
	}
	//alert(checked);
	//alert(String.fromCharCode(64+i));
//	if(document.forms["form"].elements[String.fromCharCode(64+i)].checked==false)
//		{
//		alert("Please indicate correct answer");
//		return false;
//		}
	alert("Question Submitted Successfully");
	return true;
}
function addOption()
{
	if(ctr<2)
	{
		ctr++;
		var label = document.createElement("label");
		var checkButton = document.createElement("input");
		var textbox = document.createElement("input");
		var removeButton = document.createElement("input");
		var newLine = document.createElement("br");
		var newLine2=document.createElement("br");
		var spaceLabel=document.createElement("label");
		var spaceLabel2=document.createElement("label");
		
		spaceLabel.setAttribute("id", "spaceLabel"+(ctr+4));
		spaceLabel2.setAttribute("id", "2spaceLabel"+(ctr+4));
		textbox.setAttribute("name", ""+(ctr+4));
		textbox.setAttribute("type", "text");
		textbox.setAttribute("id","txt"+(ctr+4));
		textbox.setAttribute("class", "inputtext");
		checkButton.setAttribute("type", "checkbox");
		checkButton.setAttribute("name", String.fromCharCode(68+ctr));
		checkButton.setAttribute("value", String.fromCharCode(68+ctr));
		checkButton.style.marginLeft = "5px";
		checkButton.setAttribute("id", "check"+(ctr+4));
		label.setAttribute("id", "label"+(ctr+4));
		removeButton.setAttribute("type", "button");
		removeButton.setAttribute("value", "  Remove  ");
		removeButton.setAttribute("onclick","removeOption("+(ctr+4)+")");
		removeButton.setAttribute("id","remove"+(ctr+4));
		removeButton.setAttribute("class","remove");
		newLine.setAttribute("id", "br"+(ctr+4));
		newLine2.setAttribute("id", "2br"+(ctr+4));
		
		//alert(ctr+4);
		try
		{
			var before=document.getElementById("add");
			var par=before.parentNode;
			par.insertBefore(label,before);
			par.insertBefore(checkButton,before);
			par.insertBefore(spaceLabel,before);
			spaceLabel.innerHTML=" ";
			spaceLabel2.innerHTML=" ";
			par.insertBefore(textbox,before);
			par.insertBefore(spaceLabel2,before);
			par.insertBefore(removeButton, before);
			par.insertBefore(newLine, before);
			par.insertBefore(newLine2,before);
			label.innerHTML=(String.fromCharCode(68+ctr)+". ");
			//dalert(ctr);
			
			document.getElementById("hidden_count").value=ctr;
		}
		catch(err)
		{
			alert('3 '+err.message);
		}
	}
	else
	{
		alert("Options not more than 6! "+ctr);
	}
}

function removeOption(opt)
{	
	var i=0;	
	if(ctr>0)
	{
		for(i=opt;i<(ctr);i++)
		{	
			try
			{	alert("here");
				document.getElementById("txt"+i).value=document.getElementById("txt"+(i+1)).value;
				document.getElementById("check"+i).checked = document.getElementById("check"+(i+1)).checked;
			}
			catch(err)
			{
				alert(opt+"---infor---"+err.message);
			}
		}		
		var child1=document.getElementById("txt"+(ctr+4));	
		var child2=document.getElementById("check"+(ctr+4));
		var child3=document.getElementById("label"+(ctr+4));
		var child4=document.getElementById("br"+(ctr+4));		
		var child8=document.getElementById("2br"+(ctr+4));
		var child5=document.getElementById("remove"+(ctr+4));
		var child6=document.getElementById("spaceLabel"+(ctr+4));
		var child7=document.getElementById("2spaceLabel"+(ctr+4));
		var parent=document.getElementById("content_in");
		try
		{
		parent.removeChild(child1);
		parent.removeChild(child2);
		parent.removeChild(child3);
		parent.removeChild(child4);
		parent.removeChild(child8);
		parent.removeChild(child5);
		parent.removeChild(child6);		
		parent.removeChild(child7);
		
	}
		catch(err){alert(err.message+"----remove msg!----"+opt);}
		ctr--;
		document.forms["form"].elements["hidden_count"].value=ctr;
	}
	else
	{
		alert("Options must be more than 4!");
	}
}
