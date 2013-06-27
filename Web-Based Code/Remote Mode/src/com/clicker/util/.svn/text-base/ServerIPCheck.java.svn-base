/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicker.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to check Internet Connectivity before Clicker Usage
 * @author manjur
 */
public class ServerIPCheck {

    /**
     * To check whether Internet in reachable or not
     * @return boolean status for Internet Connection
     */
    public static boolean isInternetReachable() {
        try {
            //URL url = new URL("http://localhost:8080/AakashClickerV3/sversion.txt");
            URL url = new URL("http://www.it.iitb.ac.in/clicker/sversion.txt");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ServerIPCheck.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
