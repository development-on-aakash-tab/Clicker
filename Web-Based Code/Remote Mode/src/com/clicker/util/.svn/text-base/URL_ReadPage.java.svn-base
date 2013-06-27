package com.clicker.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class is used to read HTTP URL Page Text
 * @author manjur
 */
public class URL_ReadPage {

    /**
     * It returns Textual String from URL HTTP Page Text
     * @param parURL HTTP URL
     * @return String textual data of URL HTTP Page
     * @throws Exception
     */
    public static String readPageText(URL parURL) throws Exception {
        StringBuffer sb = new StringBuffer("");
        HttpURLConnection httpcon = (HttpURLConnection) parURL.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
        BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
        int length = httpcon.getContentLength();
        for (int n = 0; n < length; n++) {
            sb.append((char) in.read());
        }
        return sb.toString();
    }   
}
