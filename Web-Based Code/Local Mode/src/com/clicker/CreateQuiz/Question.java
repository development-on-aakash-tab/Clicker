package com.clicker.CreateQuiz;
public class Question {
	public int QuestionID;
	public String Question;
	public Question(int id,String question){
		QuestionID=id;
		Question=question;
		System.out.println(QuestionID+","+Question+" Added. ");
	}
}
