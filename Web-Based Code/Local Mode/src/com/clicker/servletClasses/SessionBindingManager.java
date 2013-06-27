/* 
 * This is one experimental class in which focus is given to
 * procedure towards if user forgets to logout.
 * It is incomplete.
 */

package com.clicker.servletClasses;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionBindingManager implements HttpSessionBindingListener {
	private static Map<String, HttpSession> logins = new HashMap<String, HttpSession>();
	private String studentID;

	@Override
	public boolean equals(Object other) {
		return (other instanceof String) && (studentID != null) ? studentID
				.equals(((String) other)) : (other == studentID);
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("Binding...");
		studentID = (String) event.getSession().getAttribute("StudentID");
		HttpSession session = logins.remove(studentID);
		if (session != null) {
			session.invalidate();
		}
		logins.put(studentID, event.getSession());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("Unbinding...");
		studentID = (String) event.getSession().getAttribute("StudentID");
		logins.remove(studentID);
	}
}
