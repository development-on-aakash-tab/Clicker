package com.clicker.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.clicker.wrappers.Question;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class AppendXML {

	public void appendResponseXML(String StudentID, String Response,
			String fileName) {
		String FilePath = System.getProperty("user.home") + "/" + fileName;
		File docFile = new File(FilePath);
		Document doc = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(docFile);

			Element root = doc.getDocumentElement();
			System.out.println("The root element is " + root.getNodeName()
					+ ".\n");

			Element Clicker = doc.createElement("Clicker");
			root.appendChild(Clicker);

			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(StudentID));
			Clicker.appendChild(id);

			Element response = doc.createElement("response");
			response.appendChild(doc.createTextNode(Response));
			Clicker.appendChild(response);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(FilePath));

			// Output to console for testing
			transformer.transform(source, result);
			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't find the file");
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void appendResponseXML(
			String path, ConcurrentHashMap<String, Map<String, String>> AllParticipantResponse,
			String fileName, Vector<Question> Questiondetails) {
		
		String FilePath = path + fileName;
		File docFile = new File(FilePath);
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(docFile);

			Element root = doc.getDocumentElement();
			System.out.println("The root element is " + root.getNodeName()
					+ ".\n");
			Map<String, String> QuestionResponseArray;
			Set<String> participantIDs = AllParticipantResponse.keySet();
			Iterator iter = participantIDs.iterator();
			// int questionIndex =0;
			System.out.println("Questiondetails.size()"
					+ Questiondetails.size());

			for (int i = 0; i < Questiondetails.size(); i++) {
				Question quest = Questiondetails.get(i);
				Element qid = doc.createElement("qid");
				qid.setAttribute("qid", quest.getQuestionID());
				root.appendChild(qid);
			}
			while (iter.hasNext()) {
				String current_pid = iter.next().toString();
				QuestionResponseArray = AllParticipantResponse.get(current_pid);
				Element root1 = doc.getDocumentElement();
				NodeList qids = root1.getElementsByTagName("qid");
				for (int j = 0; j < qids.getLength(); j++) {
					Node qid = qids.item(j);

					Element Response = doc.createElement("Response");
					qid.appendChild(Response);

					Element pid = doc.createElement("pid");
					pid.appendChild(doc.createTextNode(current_pid));
					Response.appendChild(pid);

					Element qres = doc.createElement("qres");
					qres.appendChild(doc.createTextNode(QuestionResponseArray
							.get(Integer.toString(j))));
					Response.appendChild(qres);
				}

			}
			// root.appendChild(qid);
			// }

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(FilePath));

			// Output to console for testing
			transformer.transform(source, result);
			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't find the file");
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//
	public void appendResponseInXML(
			String path, ConcurrentHashMap<String, String> AllParticipantResponse,
			String fileName, Vector<Question> Questiondetails) {
		
		String FilePath = path + fileName;
		File docFile = new File(FilePath);
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(docFile);

			Element root = doc.getDocumentElement();
			System.out.println("The root element is " + root.getNodeName()
					+ ".\n");
			String[] QuestionResponseArray;
			Set<String> participantIDs = AllParticipantResponse.keySet();
			Iterator iter = participantIDs.iterator();
			// int questionIndex =0;
			System.out.println("Questiondetails.size()"
					+ Questiondetails.size());

			for (int i = 0; i < Questiondetails.size(); i++) {
				Question quest = Questiondetails.get(i);
				Element qid = doc.createElement("qid");
				qid.setAttribute("qid", quest.getQuestionID());
				root.appendChild(qid);
			}
			while (iter.hasNext()) {
				String current_pid = iter.next().toString();
				QuestionResponseArray = AllParticipantResponse.get(current_pid).split("@@");
				Element root1 = doc.getDocumentElement();
				NodeList qids = root1.getElementsByTagName("qid");
				for (int j = 0; j < qids.getLength(); j++) {
					Node qid = qids.item(j);

					Element Response = doc.createElement("Response");
					qid.appendChild(Response);

					Element pid = doc.createElement("pid");
					pid.appendChild(doc.createTextNode(current_pid));
					Response.appendChild(pid);

					Element qres = doc.createElement("qres");
					if(QuestionResponseArray.length > j){
					qres.appendChild(doc.createTextNode((QuestionResponseArray[j]).equals("")?"Z":QuestionResponseArray[j]));
					}else{
						qres.appendChild(doc.createTextNode("Z"));
					}
					Response.appendChild(qres);
				}

			}
			// root.appendChild(qid);
			// }

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(FilePath));

			// Output to console for testing
			transformer.transform(source, result);
			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't find the file");
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//
}
