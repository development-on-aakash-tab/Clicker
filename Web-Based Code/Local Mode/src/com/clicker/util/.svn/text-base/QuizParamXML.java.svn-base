/*
 * This class is used for creating XML of Quiz details which
 * will be used for Remote Mode Remote Centers.
 * It also starts Versioning for Remote Procedure.
 * 
 * 0 : No action
 * 
 * 1 : Conduct Quiz
 * 
 */

package com.clicker.util;

import java.io.*;
import java.util.Vector;

import javax.xml.parsers.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.clicker.wrappers.Option;
import com.clicker.wrappers.Question;

public class QuizParamXML {
	public static void makeXML(String QuizIDData, String QuizTimeData, String filepath) {
        try {
            int no = 2;
            String root = "Quiz";
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement(root);
            document.appendChild(rootElement);
            for (int i = 1; i <= no; i++) {
            }

            String element1 = "QuizID";
            String data1 = QuizIDData;

            Element em1 = document.createElement(element1);
            em1.appendChild(document.createTextNode(data1));
            rootElement.appendChild(em1);

            String element2 = "QuizTime";
            String data2 = QuizTimeData;

            Element em2 = document.createElement(element2);
            em2.appendChild(document.createTextNode(data2));
            rootElement.appendChild(em2);

            DOMSource source = new DOMSource(document);

//            filepath = getServletContext().getRealPath("/");

            System.out.println("Servlet Path is " + filepath);

            File file = new File(filepath + "Quiz.xml");

            if (!file.exists()) {
                file.createNewFile();
            }

            System.out.println("File Path is " + file.getAbsolutePath());

            System.out.println("File Should be created");
            Transformer xformer = TransformerFactory.newInstance().newTransformer();

            Result result = new StreamResult(file);
            xformer.transform(source, result);

            File file2 = new File(filepath + "sversion.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }

            FileInputStream fis = new FileInputStream(file2);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String versionRead = br.readLine();
            System.out.println("version read is   " + versionRead + "<br><br>");

            Writer output = new BufferedWriter(new FileWriter(file2));
            int versionUpdate = Integer.parseInt(versionRead);
            versionUpdate++;
            output.write(versionUpdate + "");
            output.close();

            System.out.println("version updated to  " + versionUpdate + "<br><br>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static void createQuizXML(String path, Vector<Question> questionList, String qid, String time){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Quiz");
						
			Element QuizID = doc.createElement("QuizID");
			QuizID.appendChild(doc.createTextNode(qid));
			rootElement.appendChild(QuizID);

			Element QuizTime = doc.createElement("QuizTime");
			QuizTime.appendChild(doc.createTextNode(time));
			rootElement.appendChild(QuizTime);
			
			for (int i = 0; i < questionList.size(); i++) {
				Question quest = questionList.get(i);
				Element question = doc.createElement("question");
				
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(quest.getQuestionID()));
				question.appendChild(id);
				Element text = doc.createElement("text");
				text.appendChild(doc.createTextNode(quest.getQuestionText()));
				question.appendChild(text);
				Element type = doc.createElement("type");
				int qtype=quest.getQuestionType();
				type.appendChild(doc.createTextNode(Integer.toString(qtype)));
				question.appendChild(type);
				
				Element options = doc.createElement("options");
				Vector <Option> optns = new Vector<Option>();
				optns = quest.getOptions();
				StringBuffer correctionans= new StringBuffer();
				for(int j=0;j<optns.size();j++){
					Element option = doc.createElement("option");
					Option opt = optns.get(j);
					option.appendChild(doc.createTextNode(opt.getOptionValue()));
					if(opt.getCorrect()){
						correctionans.append(Character.toString((char)(j+65)));
					}
					options.appendChild(option);
				}
				question.appendChild(options);
				
				Element correctans = doc.createElement("correctans");
				OptionEncription crypto = new OptionEncription();
				String encryptedAns = "";
				if(qtype == 3){
					encryptedAns = crypto.numberEC(correctionans.toString()).toString();
				}else{
					encryptedAns = crypto.charEC(correctionans.toString()).toString();
				}
				correctans.appendChild(doc.createTextNode(encryptedAns));
				question.appendChild(correctans);
				
				rootElement.appendChild(question);
			}
			doc.appendChild(rootElement);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path+"Quiz.xml"));

			// Output to console for testing
			transformer.transform(source, result);
			//System.out.println("File saved!");
			
			File file2 = new File(path + "sversion.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }

            FileInputStream fis = new FileInputStream(file2);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String versionRead = br.readLine();
            System.out.println("version read is   " + versionRead + "<br><br>");

            Writer output = new BufferedWriter(new FileWriter(file2));
            int versionUpdate = Integer.parseInt(versionRead);
            versionUpdate++;
            output.write(versionUpdate + "");
            output.close();

            System.out.println("version updated to  " + versionUpdate + "<br><br>");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void createQuizXML(String path, String qid, String time, int noofoptions, String correctAns){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Quiz");
						
			Element QuizID = doc.createElement("QuizID");
			QuizID.appendChild(doc.createTextNode(qid));
			rootElement.appendChild(QuizID);

			Element QuizTime = doc.createElement("QuizTime");
			QuizTime.appendChild(doc.createTextNode(time));
			rootElement.appendChild(QuizTime);
			
			Element question = doc.createElement("question");
				
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode("1"));
			question.appendChild(id);
			Element text = doc.createElement("text");
			text.appendChild(doc.createTextNode("Choose a correct answer"));
			question.appendChild(text);
			Element type = doc.createElement("type");
			int qtype=1;
			type.appendChild(doc.createTextNode(Integer.toString(qtype)));
			question.appendChild(type);
			Element options = doc.createElement("options");
			for(int j=0;j<noofoptions;j++){
				Element option = doc.createElement("option");
				option.appendChild(doc.createTextNode(Character.toString((char)(j+65))));
				options.appendChild(option);
			}
			question.appendChild(options);
			Element correctans = doc.createElement("correctans");
			OptionEncription crypto = new OptionEncription();
			String encryptedAns = "";
			encryptedAns = crypto.charEC(correctAns).toString();
			correctans.appendChild(doc.createTextNode(encryptedAns));
			question.appendChild(correctans);
			rootElement.appendChild(question);
			doc.appendChild(rootElement);			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path+"Quiz.xml"));

			// Output to console for testing
			transformer.transform(source, result);
			//System.out.println("File saved!");
			
			File file2 = new File(path + "sversion.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }

            FileInputStream fis = new FileInputStream(file2);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            String versionRead = br.readLine();
            System.out.println("version read is   " + versionRead + "<br><br>");

            Writer output = new BufferedWriter(new FileWriter(file2));
            int versionUpdate = Integer.parseInt(versionRead);
            versionUpdate++;
            output.write(versionUpdate + "");
            output.close();

            System.out.println("version updated to  " + versionUpdate + "<br><br>");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	

}
