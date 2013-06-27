function search_result()
{
	//alert("hello");
	var search=document.getElementById("search_box").value;
	if(search=="")
	{
	alert("enter the name!!");
	return false;
	}
	$("#my_table").remove();
	document.getElementById("addnew").style.visibility='hidden';
	var xmlhttp;
	if (window.XMLHttpRequest)
	  { 
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("wrapper").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","search.jsp?search="+search+"&type=student",true);
	xmlhttp.send();
}	
function add_new()
{	
	var l = document.getElementById("count").value;
	for(var i=1;i<l;i++)
	{
		document.getElementById("e_"+i).removeAttribute("onClick");
	}
	document.getElementById("addnew").removeAttribute("onClick");
	$('#my_table > tbody:last').after('<tr><td width= "10%"><input type="text" id="new_value_1" name="new_value_1"  size= "8"  style="border:none "/></td><td width= "10%"><input type="text" style="border:none " id="new_value_2" name="new_value_2" size= "8"/></td><td width= "13%"><input type="text" style="border:none " id="new_value_3" name="new_value_3" size= "12"/></td><td width= "10%"><input type="text" style="border:none " id="new_value_4" name="new_value_4" size= "8" maxlength = "4"/></td><td width= "10%"><input type="text" id="new_value_5" name="new_value_5"  size= "8"  style="border:none "/></td><td width= "10%"><select name="new_value_6" id="new_value_6"></select></td><td width = "10%"><input type="text" id="new_value_7" name="new_value_7"  size= "8"  style="border:none "/></td><td width= "10%"><select name="new_value_8" id="new_value_8"></select></td><td width="8%"><img src="save.jpg" height="32" width="32" value = "s" onclick= "s_add()" alt="button" title = "Save"></td><td width="6%"><img src="del.png" height="37" width="37"value="cancel" title = "Cancel" onClick="window.location.reload()"/></td></tr>');
	$('#dept option').clone().appendTo('#new_value_6');
	$('#course option').clone().appendTo('#new_value_8');
	
	document.getElementById("new_value_1").focus();
}
function s_add()
{
	document.getElementById("hid").value = "2";
	var stud_id= document.getElementById("new_value_1").value;
	var roll = document.getElementById("new_value_2").value;
	var priv = document.getElementById("new_value_5").value;
	var year = document.getElementById("new_value_4").value;
	var email= document.getElementById("new_value_7").value;
	var course=document.getElementById("new_value_8").value;
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");
	if(stud_id== "" || (isNaN(stud_id)==true))
	{
		alert("Please Enter a Valid Student Id !!!");
		return false;
	}
	else if(roll== "" || (isNaN(roll)==true))
	{
		alert("Please Enter the Valid Roll No.!!!");
		return false;
	}
	else if(document.getElementById("new_value_3").value== "")
	{
		alert("Please Enter the Student Name !!!");
		return false;
	}
	else if(year<1950 || year > 2030 || (isNaN(year)== true))
		{
			alert("Enter the Valid Year of Joining !!");
			return false;
		}
	else if(priv== "" || (isNaN(priv)==true))
	{
		alert("Please Enter the Valid Priviledges !!!");
		return false;
	}
	else if(document.getElementById("new_value_6").value== "")
	{
		alert("Please Enter the Dept ID !!!");
		return false;
	}
	else if (atpos<1 || dotpos<atpos+2 || email == "")
	  {
	  alert("Not a valid e-mail address !!!");
	  return false;
	  }
	document.forms["my_form"].submit();
}

function edit_values(n)
{
	var l = document.getElementById("count").value;
	for(var i=1;i<l;i++)
	{
		document.getElementById("e_"+i).removeAttribute("onClick");
	}
	document.getElementById("addnew").removeAttribute("onClick");
	$('#my_table tr#check_'+n).append('<td width = "1%"><img src="save.jpg" height="32" width="32" value = "s" onclick= "save_values()" alt="button" title = "Save"></td>');
	var d_id =  $('tr#check_'+n).find('td#td1_'+n).html();
	var d_n =  $('tr#check_'+n).find('td#td2_'+n).html();
	var h =  $('tr#check_'+n).find('td#td3_'+n).html();
	var ins_id =  $('tr#check_'+n).find('td#td4_'+n).html();
	var priv =  $('tr#check_'+n).find('td#td5_'+n).html();
	var email =  $('tr#check_'+n).find('td#td7_'+n).html();
	var course_id =  $('tr#check_'+n).find('td#td8_'+n).html();
	$('#check_'+n+ ' td#td1_'+n).empty();
	$('tr#check_'+n+ ' td#td1_'+n).append('<input type="text" id="text11" name = "edit_txt1"  size= "12" style = "border:none; width:35px;" readonly/>');
	document.getElementById("text11").value = d_id;
	$('#check_'+n+ ' td#td2_'+n).empty();
	$('tr#check_'+n+ ' td#td2_'+n).append('<input type="text" id="text8" name = "edit_txt2" size= "10" style = "border:none; width:90px;"/>');
	document.getElementById("text8").value = d_n;
	document.getElementById("text8").focus();
	$('#check_'+n+ ' td#td3_'+n).empty();
	$('tr#check_'+n+ ' td#td3_'+n).append('<input type="text" id="text9" size= "8" name = "edit_txt3" style = "border:none; width:90px;"/>');
	document.getElementById("text9").value = h;
	$('#check_'+n+ ' td#td4_'+n).empty();
	$('tr#check_'+n+ ' td#td4_'+n).append('<input type="text" id="text10" name = "edit_txt4" size= "5" style = "border:none; width:70px;" maxlength = "4" />');
	document.getElementById("text10").value = ins_id;
	$('#check_'+n+ ' td#td5_'+n).empty();
	$('tr#check_'+n+ ' td#td5_'+n).append('<input type="text" id="text5" name = "edit_txt5" size= "3" style = "border:none" />');
	document.getElementById("text5").value = priv;
	$('#check_'+n+ ' td#td6_'+n).empty();
    $('tr#check_'+n+ ' td#td6_'+n).append('<select name="edit_txt6" id="text6" style = "width:90px;"></select>');
	$('#dept option').clone().appendTo('#text6');
	$('#check_'+n+ ' td#td7_'+n).empty();
	$('tr#check_'+n+ ' td#td7_'+n).append('<input type="text" id="text7" name = "edit_txt7" size= "10" style = "border:none" />');
	document.getElementById("text7").value = email;
	$('#check_'+n+ ' td#td8_'+n).empty();
    $('tr#check_'+n+ ' td#td8_'+n).append('<select name="edit_txt8" id="text61" style = "width:90px;"></select>');
	$('#course option').clone().appendTo('#text61');	

}
function save_values()
{
	document.getElementById("hid").value = "1";
	var stud_id= document.getElementById("text11").value;
	var roll = document.getElementById("text8").value;
	var priv = document.getElementById("text5").value;
	var year = document.getElementById("text10").value;
	var email = document.getElementById("text7").value;
	var course = document.getElementById("text61").value;	
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");
	if(stud_id== "" || (isNaN(stud_id)==true))
	{
		alert("Please Enter a Valid Student Id !!!");
		return false;
	}
	else if(roll== "" || (isNaN(roll)==true))
	{
		alert("Please Enter the Valid Roll No.!!!");
		return false;
	}
	else if(document.getElementById("text9").value== "")
	{
		alert("Please Enter the Student Name !!!");
		return false;
	}
	else if((isNaN(year)== true) || year<1950 || year > 2030 )
	{
		alert("Enter the Valid Year of Joining !!");
		return false;
	}
	else if(priv== "" || (isNaN(priv)==true))
	{
		alert("Please Enter the Valid Priviledges !!!");
		return false;
	}
	else if(document.getElementById("text6").value== "")
	{
		alert("Please Enter the Dept ID !!!");
		return false;
	}
	else if (atpos<1 || dotpos<atpos+2 || email == "")
	  {
	  alert("Not a valid e-mail address !!!");
	  return false;
	  }
document.forms["my_form"].submit();
}
function delete_values(a){
	document.getElementById("hid").value = "3" ;
	var where_to= confirm("Do you really want to delete?");
	{
		if (where_to== true)
		 {
			alert("Record Deleted Successfully !!!");
		 }
		else
			{
			return false;
			}
		}	

	var stud_id =  $('tr#check_'+a).find('td#td1_'+a).html();
    var rollno =  $('tr#check_'+a).find('td#td2_'+a).html();
    var stud_name =  $('tr#check_'+a).find('td#td3_'+a).html();
    var year=  $('tr#check_'+a).find('td#td4_'+a).html();
    var priviledge=  $('tr#check_'+a).find('td#td5_'+a).html();
    var dept_id=  $('tr#check_'+a).find('td#td6_'+a).html();
    document.getElementById("hid1").value = stud_id;
    
    $('tr#check_'+a).remove();
    document.forms["my_form"].submit();
    }