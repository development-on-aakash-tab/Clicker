package com.clicker.util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 
 * @author manjur
 */
public class ParameterXMLParse {

    /**
     * Current Quiz ID
     */
    /**
     * Current Quiz Time in Seconds
     */
    public String QuizID, QuizTime;

    /**
     * To get parameters from XML
     * @param FilePath XML File Path
     * @return String Array of contents getting from parameters of XML
     */
    public String[] getParametersfromXML(String FilePath) {
        String ClickerParameters[] = new String[2];

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(FilePath));

            NodeList QuizIDList = doc.getElementsByTagName("QuizID");
            QuizID = QuizIDList.item(0).getTextContent();

            System.out.println("Quiz ID is " + QuizID);

            NodeList QuizTimeList = doc.getElementsByTagName("QuizTime");
            QuizTime = QuizTimeList.item(0).getTextContent();

            System.out.println("Quiz Time is " + QuizTime);

            ClickerParameters[0] = QuizID;
            ClickerParameters[1] = QuizTime;            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ClickerParameters;
    }
}
