package com.clicker.poll;

import java.util.ArrayList;
public class Course {
	int no_no,no_yes;
	double yes_percent,no_percent;
	int timeout;
	String courseID,Question;
	ArrayList<String> alist=new ArrayList<String>();
	double quizTime;            //This is used to store the time when the quiz is started in milliseconds.
	boolean toBeRemoved;
	int PollID;
	Course()
	{
		no_no=0;
		Question="";
		no_yes=0;
		courseID="";
		timeout=30;
		toBeRemoved=false;
		PollID=0;
	}
}
