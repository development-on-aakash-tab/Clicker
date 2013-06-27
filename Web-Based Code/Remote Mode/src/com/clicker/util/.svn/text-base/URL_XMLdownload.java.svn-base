package com.clicker.util;

import java.io.*;
import java.net.*;

/**
 * Class to download XML File from URL
 * @author manjur
 */
public class URL_XMLdownload {

    final static int size = 1024;

    /**
     * To download XML File from URL
     * @param fAddress File URL Address String
     * @param localFileName Local File Name
     * @param destinationDir Destination Directory name
     * @param Status int status of downloading XML File from URL
     * 0 indicates Download is not done
     * 1 indicates Download is done
     * @return int status of downloading XML File from URL
     * 0 indicates Download is not done or Required File is not there
     * 1 indicates Download is done
     */
    public static int XML_fileUrl(String fAddress, String localFileName, String destinationDir, int Status) {
        OutputStream outStream = null;
        URLConnection uCon = null;

        InputStream is = null;
        try {
            URL Url;
            byte[] buf;
            int ByteRead, ByteWritten = 0;
            Url = new URL(fAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "/" + localFileName));

            uCon = Url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[size];
            while ((ByteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, ByteRead);
                ByteWritten += ByteRead;
            }
            System.out.println("Downloaded Successfully.");
            System.out.println("File name:\"" + localFileName + "\"\nNo ofbytes :" + ByteWritten);

            Status=1;

            is.close();
            outStream.close();
            return Status;
        } catch (Exception e) {
            System.out.println("Required File is not there  "+e.getMessage());
            Status=0;
            return Status;
        }
    }

    /**
     * 
     * @param fAddress Specific File Path String
     * @param destinationDir Destination Directory Name
     * @param Status Status int status of downloading XML File from URL
     * 0 indicates Download is not done
     * 1 indicates Download is done
     * @return Status int status of downloading XML File from URL
     * 0 indicates Download is not done
     * 1 indicates Download is done
     */
    public static int download_XML_File(String fAddress, String destinationDir, int Status) {

        int slashIndex = fAddress.lastIndexOf('/');
        int periodIndex = fAddress.lastIndexOf('.');

        String fileName = fAddress.substring(slashIndex + 1);

        System.out.println("Filename is "+fileName);

        if (periodIndex >= 1 && slashIndex >= 0
                && slashIndex < fAddress.length() - 1) {
            Status = XML_fileUrl(fAddress, fileName, destinationDir, Status);
        } else {
            System.err.println("path or file name.");
        }
        return Status;
    }
    
    
    public static void folderCreateOrDelete(String path){
		boolean iscreated = (new File(path)).mkdir();
		if (iscreated) {
			System.out.println("Directory: " + path + " created");
		}else {	
			File folder = new File (path);
			File[] files = folder.listFiles();
		    if(files!=null) { //some JVMs return null for empty dirs
		        for(File f: files) {
		            if(f.isDirectory()) {
		               continue;
		            } else {
		                f.delete();
		            }
		        }
		    }
		    System.out.println("Folder Content is deleted");
		}
	}
}
