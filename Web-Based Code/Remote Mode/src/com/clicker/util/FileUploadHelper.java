package com.clicker.util;

import java.io.*;
import java.util.List;
import com.clicker.QuestionBank.XLSimport;

import java.net.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class FileUploadHelper extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String filepath="/home/manjur/Desktop/";
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("In servlet");
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		boolean fileFlag=ServletFileUpload.isMultipartContent(request);
		List<FileItem> items=null;
		System.out.println("File Flag:"+fileFlag);
		
		if(fileFlag){
			try{
				items=upload.parseRequest(request);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			for(FileItem item:items){
				if(!item.isFormField()&&!item.getName().equals("")){
					String fileName = new File(item.getName()).getName();
					System.out.println("The File Name :"+item.getName());
					item.getName();
					ServletContext context = request.getServletContext();
					String pathurl = context.getRealPath("/uploads");
					file=new File(pathurl + "/" + fileName);
					
					System.out.println("FilePath: " + file.getPath());
					//System.out.println("File: " + new File(item.getName()).getAbsolutePath());
					try {
						item.write(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("File Uploaded.");
				}
			}
		}
		
		//XLSimport xls=new XLSimport();
			//String status=xls.readQuestionXLSFile(file);
			
			//response.sendRedirect("jsp/QuestionBank/questionbank.jsp?fileuploadstatus=" + status);
			//return;
		
	}
	
}
