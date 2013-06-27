/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicker.wrappers;

/**
 * This class is used to get and set the details of Quiz
 * 
 * @author manjur
 */
public class Quiz {

	private String quizID;
	private String quizName;

	/**
	 * This method is used to set quiz id
	 * 
	 * @param i
	 *            Quiz id to set
	 */
	public void setquizID(String i) {
		quizID = i;
	}

	/**
	 * This method is used to set Quiz name
	 * 
	 * @param n
	 *            Quiz name to set
	 */
	public void setQuizName(String n) {
		quizName = n;
	}

	/**
	 * This method is used to get Quiz name
	 * 
	 * @return Quiz name as string
	 */
	public String getQuizName() {
		return quizName;
	}

	/**
	 * This method is used to get Question id
	 * 
	 * @return Quiz id as string
	 */
	public String getQuizID() {
		return quizID;
	}
}