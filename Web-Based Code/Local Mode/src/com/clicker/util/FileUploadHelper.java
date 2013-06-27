package com.clicker.util;

import java.io.File;
import java.util.List;

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
	private String filepath="/home/deejay/workspace/clicker/Files/";
	protected void doPost(HttpServletRequest request,HttpServletResponse response){
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
					System.out.println("The File Name :"+item.getName());
					item.getName();
					file=new File(filepath+item.getName());
					System.out.println(filepath+item.getName());
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
	}
	
}
