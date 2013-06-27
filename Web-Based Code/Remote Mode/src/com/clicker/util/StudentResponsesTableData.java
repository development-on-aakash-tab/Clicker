/*
 * It is used in localhost mode.
 * Specially showing the responses of the Student.
 */

package com.clicker.util;


import java.util.Vector;

import com.clicker.wrappers.StudentResponse;

public class StudentResponsesTableData {

	public static String getStudentResponsesTableData(
			Vector<StudentResponse> StudentResponses) {
		String StudentResponsesTableData = "";
		String StudentID = "";
		String StudentName = "";
		String Response = "";

		for (int i = 0; i < StudentResponses.size(); i++) {
			StudentID = StudentResponses.elementAt(i).getStudentID();
			StudentName = StudentResponses.elementAt(i).getStudentName();
			Response = StudentResponses.elementAt(i).getResponse();
//			if(Response.equals("Z"))
//				Response="No Response";
			StudentResponsesTableData = StudentResponsesTableData + "<tr>"
					+ "<td>" + StudentID + "</td>" + "<td>" + StudentName
					+ "</td>" + "<td>" + Response + "</td>" + "</tr>";
		}
		return StudentResponsesTableData;
	}

	
	public static String getDemoStudentResponsesTableData(
			Vector<StudentResponse> StudentResponses) {
		String StudentResponsesTableData = "";
		String StudentID = "";
		String Response = "";

		for (int i = 0; i < StudentResponses.size(); i++) {
			StudentID = StudentResponses.elementAt(i).getStudentID();
			Response = StudentResponses.elementAt(i).getResponse();

			StudentResponsesTableData = StudentResponsesTableData + "<tr>"
					+ "<td>" + StudentID + "</td>" + "<td>" + Response + "</td>" + "</tr>";
		}
		return StudentResponsesTableData;
	}
	

}
