package com.clicker.util;

import java.io.File;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class XLSImport {

	/**
	 * @param args
	 */
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XLSImport xls = new XLSImport();
		//xls.importRCList();
		xls.importStudentList();
		 
	}

	public void importRCList(){
		File f1 = new File("/home/rajavel/Desktop/rc.xls");
        Workbook w;
        String url = "jdbc:mysql://localhost:3306/aakashclicker";
		String dbClass = "com.mysql.jdbc.Driver";		
		String user = "clicker_ui";
		String pw = "clicker_ui";
        
        try {
        	Class.forName(dbClass);
	        Connection con = DriverManager.getConnection (url, user, pw );
            w = Workbook.getWorkbook(f1);
            Sheet sheet = w.getSheet(0);            
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));    
            Statement stmt = con.createStatement();
            for(int i = 1;i< 244 ; i++)      {
            	Cell cid = sheet.getCell(1,i);
                Cell cname = sheet.getCell(2,i);
               // Cell state = sheet.getCell(3,i);
                //Cell city = sheet.getCell(4,i);
                System.out.println();
            	//String query = "Insert into remotecenter values("+Integer.parseInt(cid.getContents())+",'"+cname.getContents()+"','"+state.getContents()+"','"+city.getContents()+"')";
                String query = "Insert into remotecenter values("+Integer.parseInt(cid.getContents())+",'"+cname.getContents()+"')";
            	System.out.println(query);
            	stmt.executeUpdate(query);
            }    
        } catch (BiffException e) {
            e.printStackTrace();
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importStudentList(){
		File f1 = new File("/home/dipti/Desktop/students.xls");
        Workbook w;
        String url = "jdbc:mysql://localhost:3306/aakashclicker";
		String dbClass = "com.mysql.jdbc.Driver";		
		String user = "clicker_ui";
		String pw = "clicker_ui";
        
        try {
        	Class.forName(dbClass);
	        Connection con = DriverManager.getConnection (url, user, pw );
            w = Workbook.getWorkbook(f1);
            Sheet sheet = w.getSheet(0);            
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));    
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            for(int i = 1;i< 40 ; i++)      {
            	Cell sid = sheet.getCell(2,i);
                Cell sname = sheet.getCell(0,i);
                Cell email = sheet.getCell(3,i);
               // Cell state = sheet.getCell(3,i);
                //Cell city = sheet.getCell(4,i);
                System.out.println();
            	//String query = "Insert into remotecenter values("+Integer.parseInt(cid.getContents())+",'"+cname.getContents()+"','"+state.getContents()+"','"+city.getContents()+"')";
                String query = "Insert into student values('"+sid.getContents()+"','"+sid.getContents()+"','"+sname.getContents()+"',2013,0,'dept004','iit"+sid.getContents()+"',0,'"+email.getContents()+"','')";
            	System.out.println(query);
            	stmt.executeUpdate(query);
            	String query1 = "Insert into studentcourse values(2013, 'winter', 'CL692', '"+sid.getContents()+"')";
            	System.out.println(query1);
            	stmt1.executeUpdate(query1);
            }    
            con.close();
        } catch (BiffException e) {
            e.printStackTrace();
        }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
