/*
 * It is used during Remote Mode.
 * Specially to display statistics related to Remote Center Responses.
 */

package com.clicker.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.clicker.databaseconn.DatabaseConnection;
import com.clicker.wrappers.*;

/**
 * This class is used to get remote center response and parse response xml file
 * @author rajavel
 */
public class RemoteCenterResponse {

    /**
     * This method is used to get response status of all remote centers
     * @param quizID Quiz Id for current quiz
     * @param path Path of all remote centers response file
     * @return Center ID and their response file
     */
    public static String getResponseStatus(String quizID, String path, String existingStatus) {
        String curdir = path;
        System.out.println("PATH is " + curdir + " QuizID " + quizID);
        File dir = new File(curdir);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            System.out.println(curdir + files.length);
            String centerIDs = "";
            String FileNames = "";
            String centerID="";
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains("Quiz_" + quizID)) {
                    String filename = files[i].getName();
                    System.out.print(filename);
                    int firstindex = filename.indexOf("_");
                    int lastindex = filename.indexOf("_", firstindex + 1);
                    centerID = filename.substring(firstindex + 1, lastindex);
                    //System.out.println("existingStatus.contains((centerID))  --- " + existingStatus.contains((centerID + ";")));
                    if(!existingStatus.contains((centerID + ";")) && !centerID.equals("0")){
                    	centerIDs +=  centerID + ";";
                    	FileNames += filename + ";";
                    }
                }
            }
            System.out.println("Token ===" + centerIDs + "@#" + FileNames);
            return centerIDs + "@#" + FileNames;
        }
        System.out.println("Outside");
        return "null";
    }

    // Author : Manjur
    // Modified : rajavel
    /**
     * This method is used to parse the response XML file to table format
     * @param FileName Response file name
     * @param path Path of the response file
     * @return Response as table format
     */
    public static String parseXMLFile_to_Table1(String FileName, String path) {
        String responseTable = "";
        try {
            File file = new File(path + FileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nl = doc.getElementsByTagName("id");
            NodeList n2 = doc.getElementsByTagName("response");
            NodeList nodeLst = doc.getElementsByTagName("Clicker");
            String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
            String Response = "", UserID="";
            
            for (int s = 0; s < nodeLst.getLength(); s++) {
            	UserID = nl.item(s).getFirstChild().getNodeValue();                 
                Response = n2.item(s).getFirstChild().getNodeValue();
                responseTable += "<tr><td>" + RemoteCenterID + "</td><td>" + UserID + "</td><td>" + Response + "</td></tr>";
            }
        } catch (SAXException ex) {
        } catch (IOException ex) {
        } catch (ParserConfigurationException ex) {
        }
        return responseTable;
    }
    
    public static String parseXMLFile_to_Table(String FileName, String path) {
        String responseTable = "";
        try {
            File file = new File(path + FileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList qid_nlist = doc.getElementsByTagName("qid");
            String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
            System.out.println(qid_nlist.getLength());
            for(int i=0; i<qid_nlist.getLength();i++){
            	Element qid_Element = (Element)qid_nlist.item(i);
            	String qid = qid_Element.getAttribute("qid").toString();         
            	System.out.println("Qid : "+qid);
            	NodeList Response_nlist = qid_Element.getElementsByTagName("Response");                    	
            	for(int j=0;j<Response_nlist.getLength();j++){
            		Element Response_Element = (Element)Response_nlist.item(j);
            		NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
            		NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
            		String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
            		String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
            		System.out.println("pid : "+pid + " resp : " +resp);       
            		responseTable += "<tr><td>" + RemoteCenterID + "</td><td>" + qid + "</td><td>" + pid + "</td><td>" + resp + "</td></tr>";
            	}
            }
        } catch (SAXException ex) {
        } catch (IOException ex) {
        } catch (ParserConfigurationException ex) {
        }
        return responseTable;
    }
    
    public static String parseXMLFile_to_Table(String FileName, String path, String questid, int sno) {
        String responseTable = "";
        int count = sno;
        try {
            File file = new File(path + FileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList qid_nlist = doc.getElementsByTagName("qid");
            String RemoteCenterID = FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1);
            System.out.println(qid_nlist.getLength());
            for(int i=0; i<qid_nlist.getLength();i++){
            	Element qid_Element = (Element)qid_nlist.item(i);
            	String qid = qid_Element.getAttribute("qid").toString();         
            	if(qid.equals(questid)){
            		System.out.println("Qid : "+qid);
            		NodeList Response_nlist = qid_Element.getElementsByTagName("Response");                    	
            		for(int j=0;j<Response_nlist.getLength();j++){
            			Element Response_Element = (Element)Response_nlist.item(j);
            			NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
            			NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
            			String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
            			String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
            			System.out.println("pid : "+pid + " resp : " +resp);       
            			responseTable += "<tr><td>" + (count) + "</td><td>" + RemoteCenterID + "</td><td>" + pid + "</td><td>" + resp + "</td></tr>";
            			count++;
            		}
            		break;
            	}
            }
        } catch (SAXException ex) {
        } catch (IOException ex) {
        } catch (ParserConfigurationException ex) {
        }
        return responseTable +"%8%"+ count;
    }
    
    public static String getRCQuestionResponse_Table(int centerid, String qstnid, String quizRecordID){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String responseTable="";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select GROUP_CONCAT(qrq.Response SEPARATOR '') as Response, qrq.ParticipantID from remotequizrecordquestion qrq, options o, question q where qrq.QuizRecordID = '"+quizRecordID+"' and qrq.CenterID = "+centerid+" and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.ParticipantID order by qrq.ParticipantID";
            System.out.println(query);
            rs = st.executeQuery(query);
            int count=1;
            while (rs.next()) {
            	responseTable += "<tr><td>" + (count) + "</td><td>" + rs.getString("ParticipantID") + "</td><td>" + rs.getString("Response") + "</td></tr>";
            	count++;
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(responseTable);
        return responseTable;
	}
    
    public static String getRCInstantQuizResponse_Table(int centerid, String qid){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String responseTable="";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select Response, ParticipantID from remoteinstantquizresponse where CenterID = "+centerid+" and IQuizID = '"+qid+"' order by ParticipantID";
            System.out.println(query);
            rs = st.executeQuery(query);
            int count=1;
            while (rs.next()) {
            	responseTable += "<tr><td>" + (count) + "</td><td>" + rs.getString("ParticipantID") + "</td><td>" + rs.getString("Response") + "</td></tr>";
            	count++;
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(responseTable);
        return responseTable;
	}
    
    public static String getAllRCQuestionResponse_Table(String qstnid, String quizRecordID){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String responseTable="";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select GROUP_CONCAT(qrq.Response SEPARATOR '') as Response, qrq.ParticipantID, qrq.CenterID from remotequizrecordquestion qrq, options o, question q where qrq.QuizRecordID = '"+quizRecordID+"' and qrq.QuestionID = '"+qstnid+"' and q.QuestionID = qrq.QuestionID and o.OptionID = qrq.OptionID group by qrq.ParticipantID order by qrq.CenterID";
            System.out.println(query);
            rs = st.executeQuery(query);
            int count=1;
            while (rs.next()) {
            	responseTable += "<tr><td>" + (count) + "</td><td>" + rs.getString("CenterID") + "</td><td>" + rs.getString("ParticipantID") + "</td><td>" + rs.getString("Response") + "</td></tr>";
            	count++;
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(responseTable);
        return responseTable;
	}
    
    public static String getAllRCInstantQuizResponse_Table(String qid){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String responseTable="";
        try {
        	DatabaseConnection dbcon = new DatabaseConnection();
            con = dbcon.createDatabaseConnection();
            st = con.createStatement();
            String query = "select Response, ParticipantID, CenterID from remoteinstantquizresponse where IQuizID="+qid+" order by CenterID, ParticipantID";
            System.out.println(query);
            rs = st.executeQuery(query);
            int count=1;
            while (rs.next()) {
            	responseTable += "<tr><td>" + (count) + "</td><td>" + rs.getString("CenterID") + "</td><td>" + rs.getString("ParticipantID") + "</td><td>" + rs.getString("Response") + "</td></tr>";
            	count++;
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        System.out.println(responseTable);
        return responseTable;
	}
    
    public static int saveXMLFile_to_DB(String FileName, String path, Vector<Question> Questiondetails, int quizrecordID) {
    	Connection conn =null;
         try {
        	DatabaseConnection dbcon = new DatabaseConnection();
    		conn= dbcon.createDatabaseConnection();
            File file = new File(path + FileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList qid_nlist = doc.getElementsByTagName("qid");
            int RemoteCenterID = Integer.parseInt(FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1));
            System.out.println(qid_nlist.getLength());
            String GenOpt = "ABCDEF";
            for(int i=0; i<qid_nlist.getLength();i++){
            	Element qid_Element = (Element)qid_nlist.item(i);
            	String qid = qid_Element.getAttribute("qid").toString();         
            	System.out.println("Qid : "+qid);
            	Vector <Option> Options = new Vector<Option>(); 
            	int questionType=0;
            	Question qust = Questiondetails.get(i);
            	if(qust.getQuestionID().equals(qid)){
            		Options = (Vector<Option>)qust.getOptions();
            		questionType = qust.getQuestionType();
            	}else{
            		for(int k=0;k<Questiondetails.size();k++){
            			qust = Questiondetails.get(k);
            			if(qust.getQuestionID().equals(qid)){
            				Options = (Vector<Option>)Questiondetails.get(i).getOptions();
            				questionType = qust.getQuestionType();
            				break;
            			}
            		}
            	}    	
            	NodeList Response_nlist = qid_Element.getElementsByTagName("Response");
            	for(int j=0;j<Response_nlist.getLength();j++){
            		Element Response_Element = (Element)Response_nlist.item(j);
            		NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
            		NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
            		String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
            		String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
            		System.out.println("pid : "+pid + " resp : " +resp);       
            		String OptionID ="0";           		
            		int ResponseLength = resp.length();
            		int OptionIndex = 0;
            		if (resp.equalsIgnoreCase("X") || resp.equalsIgnoreCase("Z")) {
            			OptionID = "0";
            			insertRecord(conn, RemoteCenterID, quizrecordID, qid, OptionID, pid, resp);
            		} else if (ResponseLength == 1 && questionType == 1) {
            			if (GenOpt.contains(resp) && Options.size() >= 3) {
            				char ResponseFirstChar = resp.charAt(0);
            				OptionIndex = GenOpt.indexOf(ResponseFirstChar);
            				if (OptionIndex < Options.size()) {
           						OptionID = ((Option) Options.elementAt(OptionIndex)).getOptionID();
           					} else {
           						OptionID = "0";
           					}
           				}
           				insertRecord(conn, RemoteCenterID, quizrecordID, qid, OptionID, pid, resp);
           			} else if (ResponseLength >= 1 && questionType == 2) {
           				for(int q=0;q<ResponseLength;q++){
           					String ResponseChar= Character.toString(resp.charAt(q));					
           					if (GenOpt.contains(ResponseChar) && Options.size() >= 3) {						
          						OptionIndex = GenOpt.indexOf(ResponseChar);
           						if (OptionIndex < Options.size()) {
           							OptionID = ((Option) Options.elementAt(OptionIndex)).getOptionID();
           						} else {
           							OptionID = "0";
           						}
           					}
           					insertRecord(conn, RemoteCenterID, quizrecordID, qid, OptionID, pid, ResponseChar);
           				}
           			}
           			else if (questionType == 3) {
           				System.out.println("size of num options :" + Options.size());
           				System.out.println("Response of num :" + ((Option) Options.elementAt(0)).getOptionValue());
           				if (resp.equals(((Option) Options.elementAt(0)).getOptionValue())) {
           					OptionID = ((Option) Options.elementAt(0)).getOptionID();
           				} else {
           					OptionID = "0";
           				}            					
          				insertRecord(conn, RemoteCenterID, quizrecordID, qid, OptionID, pid, resp);
           			}
           			else if (questionType == 4) {				
           				if (GenOpt.contains(resp)) {
           					char ResponseFirstChar = resp.charAt(0);
           					OptionIndex = GenOpt.indexOf(ResponseFirstChar);
           					if (OptionIndex < Options.size()) {
           						OptionID = ((Option) Options.elementAt(OptionIndex)).getOptionID();
           					} else {
           						OptionID = "0";
           					}
           				}
           				insertRecord(conn, RemoteCenterID, quizrecordID, qid, OptionID, pid, resp);
           			}      		
           		//responseTable += "<tr><td>" + RemoteCenterID + "</td><td>" + qid + "</td><td>" + pid + "</td><td>" + resp + "</td></tr>";
            	}
           }
       } catch (SAXException ex) {
       } catch (IOException ex) {
       } catch (ParserConfigurationException ex) {
       } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
       return 1;
    }
   
    
    public static int saveXMLFile_to_DB(String FileName, String path, int iquizid) {
    	Connection conn =null;
         try {
        	DatabaseConnection dbcon = new DatabaseConnection();
    		conn= dbcon.createDatabaseConnection();
            File file = new File(path + FileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList qid_nlist = doc.getElementsByTagName("qid");
            int RemoteCenterID = Integer.parseInt(FileName.substring(FileName.indexOf("_") + 1, FileName.indexOf("Quiz") - 1));
            System.out.println(qid_nlist.getLength());
            for(int i=0; i<qid_nlist.getLength();i++){
            	Element qid_Element = (Element)qid_nlist.item(i);
            	String qid = qid_Element.getAttribute("qid").toString();         
            	System.out.println("Qid : "+qid);
            	NodeList Response_nlist = qid_Element.getElementsByTagName("Response");
            	for(int j=0;j<Response_nlist.getLength();j++){
            		Element Response_Element = (Element)Response_nlist.item(j);
            		NodeList pid_nlist = Response_Element.getElementsByTagName("pid");
            		NodeList qres_nlist = Response_Element.getElementsByTagName("qres");
            		String pid = pid_nlist.item(0).getFirstChild().getNodeValue();
            		String resp = qres_nlist.item(0).getFirstChild().getNodeValue();
            		System.out.println("pid : "+pid + " resp : " +resp);       
            		insertRecord(conn, RemoteCenterID, pid, iquizid, resp);            				
           		}
           }
       } catch (SAXException ex) {
       } catch (IOException ex) {
       } catch (ParserConfigurationException ex) {
       }catch (Exception ex) {    	   
       }finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
         
       return 1;
    }
       
    public static void insertRecord(Connection conn, int RemoteCenterID, int latestQuizRecordID, String QuestionID, String OptionID, String ParticipantID, String Response) throws Exception {
    	try {
    		PreparedStatement ps;
    		System.out.println(RemoteCenterID + " " + latestQuizRecordID + " " + QuestionID + " " + OptionID + " " + ParticipantID + " " + Response);
    		String Query = "INSERT INTO remotequizrecordquestion (CenterID, ParticipantID, Response, QuizRecordID, QuestionID, OptionID) VALUES(?,?,?,?,?,?)";
    		ps = conn.prepareStatement(Query);
    		ps.setInt(1, RemoteCenterID);
    		ps.setString(2, ParticipantID);
    		ps.setString(3, Response);
    		ps.setInt(4, latestQuizRecordID);
    		ps.setInt(5, Integer.parseInt(QuestionID));
    		ps.setInt(6, Integer.parseInt(OptionID));    			
    		ps.execute();
    		ps.close();
    	} catch (Exception e) {
    		e.printStackTrace();
   		}
   	} 
    
    public static void insertRecord(Connection conn, int RemoteCenterID, String ParticipantID, int iquizid, String Response) {
    	try {
    		PreparedStatement ps;
    		String Query = "INSERT INTO remoteinstantquizresponse (CenterID, ParticipantID, IQuizID, Response) VALUES(?,?,?,?)";
    		ps = conn.prepareStatement(Query);
    		ps.setInt(1, RemoteCenterID);
    		ps.setString(2, ParticipantID);
    		ps.setInt(3, iquizid);
    		ps.setString(4, Response);    				
    		ps.execute();
    		ps.close();
    	} catch (Exception e) {
    		e.printStackTrace();
   		}
   	} 
}
