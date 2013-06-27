<script type="text/javascript">
$(":checkbox").click(function(){
	var mailTo="location.href='mailto:";
	$(":checkbox:checked").each(
		function(){
			//alert($(this).parent().children(".email").val());
			if($(this).parent().children(".email").val()!="null")
				mailTo+=$(this).parent().children(".email").val()+",";
		}
	);
	if(mailTo.charAt(mailTo.length-1)==','){
		mailTo=mailTo.slice(0, -1);
		mailTo+="?Subject=Response%20to%20Your%20Doubt%20on%20"+$(this).val()+"'";
	}
	else{
		mailTo="alert('No EmailIDs available')";
	}
	//alert(mailTo);
	$("#reply").attr("onclick",mailTo);
});
</script>
<%@ page import="com.clicker.raisehand.*,java.util.*"%>
<%ArrayList<RaiseHandRowData> list = (ArrayList<RaiseHandRowData>)request.getAttribute("records"); %>
<%for(RaiseHandRowData rdata:list){%>
<div class="doubtWrap">
	<div class="doubtTextDiv">
		<br> <label style="margin-left: 5px;"> <%=rdata.getComment()%>
		</label>
	</div>
	<table class="studentInfo">
		<tr>
			<td style="width: 100px"><b>RollNo.</b></td>
			<td><%=rdata.getRollNo()%></td>
		</tr>
		<tr>
			<td style="width: 100px"><b>Student Name</b></td>
			<td><%=rdata.getName()%></td>
		</tr>
		<tr>
			<td style="width: 100px"><b>Semester</b></td>
			<td><%=rdata.getSemester()%></td>
		</tr>
	</table>
	<div class="emailDiv">
		<label style="padding-left: 5px; display: table-cell;"><b>Mark
				for Reply/Delete</b></label> <input type="hidden" class="email"
			value="<%=rdata.getEmail()%>"> <input type="checkbox"
			name="reply" class="checks"
			value="<%=rdata.getRaiseHandTimeStamp()%>" />
	</div>
	<table class="timeStampDiv">
		<tr>
			<td style="width: 100px"><b>TimeStamp</b></td>
			<td style="font-size: small;"><%=rdata.getRaiseHandTimeStamp()%></td>
		</tr>
	</table>
</div>
<%}%>