package com.clicker.servletClasses;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.databaseconn.DatabaseQueries;
import com.clicker.global.AakashClickerGlobal;

public class AakashSessionManager implements HttpSessionListener, HttpSessionBindingListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Session has been created" + event.getSession().getId().toString());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();		
		if (session.getAttribute("ParticipantID") != null) {
			String ParticipantID= (String) session.getAttribute("ParticipantID");
			AakashClickerGlobal.ParticipantIDs.remove(ParticipantID);
			System.out.println("Participant has been destroyed for " + ParticipantID);
			System.out.println("Participant have logged out now");
		} else if (session.getAttribute("ParticipantID")== null) {
			System.out.println("Participant is null" + event.getSession().getId().toString());
			System.out.println("Participant have logged out already");
		}		
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("valueBound" + arg0.getSession().toString());
		System.out.println("getName" + arg0.getName());
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("valueUnbound" + arg0.getSession().toString());
		System.out.println("getName" + arg0.getName());
		
	}
}