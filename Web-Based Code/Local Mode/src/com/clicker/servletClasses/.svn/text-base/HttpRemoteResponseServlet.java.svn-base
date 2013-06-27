/*
 * This class is active during Remote Center mode.
 * It will receive XML Files from Remote Centers and pasted into required folder.
 */

package com.clicker.servletClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HttpRemoteResponseServlet
 */
public class HttpRemoteResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * do Get Method of this Servlet
     * In which it will accept XML File and XML File Name as arguments
     * Later that File will be saved in System Located Folder
     * @param request HTTP Request for Servlet
     * @param response HTTP Response for Servlet
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String xmlname = request.getParameter("fileName");

        out.println("Target XML File " + xmlname);

        String xml = request.getParameter("xmldoc");

        out.println("XML DOC " + xml);

        String user_home = System.getProperty("user.home");

        String FolderName = user_home + "/ClickerRemoteFiles";
        System.out.println("New Folder Name is " + FolderName);
        File Folder = new File(FolderName);

        if (!Folder.exists()) {
            Folder.mkdir();
            System.out.println("Folder Created");
        }

        String ReceivedFolderName = FolderName + "/Received";
        System.out.println("New Folder Name is " + ReceivedFolderName);
        File ReceivedFolder = new File(ReceivedFolderName);

        if (!ReceivedFolder.exists()) {
            ReceivedFolder.mkdir();
            System.out.println("Received Folder Created");
        }

        String fileName = ReceivedFolderName + "/" + xmlname;

        System.out.println("New File Path is " + fileName);

        PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        fileWriter.write(xml);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * do Post method of this Servlet
     * @param request HTTP Request for Servlet
     * @param response HTTP Response for Servlet
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

}
