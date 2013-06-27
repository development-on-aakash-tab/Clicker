package com.clicker.util;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * HTTP File Transfer Java Class
 * @author manjur
 */
public class HTTP_FileTransfer {
    String inputString = "";
    String input;

    /**
     * To send XML File through HTTP to URL
     * @param xmlpath File path of XML File
     */
    public boolean sendthroughHTTP(String xmlpath, String XMLName) {
        System.out.println("Path of xml is " + xmlpath);
        try {
            BufferedReader in = new BufferedReader(new FileReader(xmlpath));
            inputString = "";
            while ((input = in.readLine()) != null) {
                inputString = inputString + input;
            }
            in.close();          
            //URL targetURL = new URL("http://localhost:8080/AakashClickerV3/HttpRemoteResponseServlet?fileName=" +XMLName);
            URL targetURL = new URL("http://www.it.iitb.ac.in/clicker/HttpRemoteResponseServlet?fileName=" +XMLName);
            HttpURLConnection c = (HttpURLConnection) (targetURL.openConnection());
            c.setDoOutput(true);
            PrintWriter out = new PrintWriter(c.getOutputStream());
            out.println("xmldoc=" + URLEncoder.encode(inputString));
            out.close();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            while ((inputLine = in2.readLine()) != null) {
                System.out.println(inputLine);
            }
            in2.close();
            return true;
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }
    }
}
