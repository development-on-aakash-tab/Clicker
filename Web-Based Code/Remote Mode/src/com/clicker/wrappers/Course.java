/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicker.wrappers;

/**
 * This class set and get the details of Course
 * 
 * @author manjur
 */
public class Course {

	private String courseID;
	private String courseName;
	private String Desc;

	/**
	 * This method set the course Id
	 * 
	 * @param id
	 *            Course Id to set
	 */
	public void setCourseID(String id) {
		courseID = id;
	}

	/**
	 * This method set the name of course
	 * 
	 * @param n
	 *            Course name to set
	 */
	public void setCourseName(String n) {
		courseName = n;
	}

	/**
	 * This method set the description of the course
	 * 
	 * @param n
	 *            Description about the course
	 */
	public void setDesc(String n) {
		Desc = n;
	}

	/**
	 * This method is used to get the description of the course
	 * 
	 * @return Description of course as string
	 */
	public String getDesc() {
		return Desc;
	}

	/**
	 * This method is used to get the course id
	 * 
	 * @return Course id as string
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * This method is used to get he course name
	 * 
	 * @return Course name as string
	 */
	public String getCourseName() {
		return courseName;
	}
}