<%@page import="java.io.*"%> <%@page import="java.util.*"%> <%@page import="org.apache.poi.hssf.usermodel.HSSFSheet"%> <%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%> <%@page import="org.apache.poi.hssf.usermodel.HSSFRow"%> <%@page import="org.apache.poi.hssf.usermodel.HSSFCell"%>

<% short a=0; short b=1; short c=2; short d=3; int i=0; String value1="", value2="",value3=" " ;double value4=0,value5=0;
//String filename ="/users/extusr/INTERNS/vishwajeet12/Downloads/a.xls"; 
String path1=request.getParameter("xls");
ServletContext context = pageContext.getServletContext();
String filename=context.getInitParameter("file")+path1;
if (filename != null && !filename.equals("")) {
try{
FileInputStream fs =new FileInputStream(filename);
HSSFWorkbook wb = new HSSFWorkbook(fs);
   HSSFSheet sheet = wb.getSheetAt(0);
int rows  = sheet.getPhysicalNumberOfRows();
out.println(rows);
for (int r = 1; r < rows; r++){
HSSFRow row   = sheet.getRow(r);
    HSSFCell cell1  = row.getCell(0);

  value1 = cell1.getStringCellValue();
  out.println("<br>");
  out.println(value1);
  HSSFCell cell2  = row.getCell(1);

  value2 = cell2.getStringCellValue();
  out.println("<br>");
  out.println(value2);
  HSSFCell cell3  = row.getCell(2);
  value3 = cell3.getStringCellValue();
  out.println("<br>");
  out.println(value3);
  HSSFCell cell4  = row.getCell(3);
  value4 = cell4.getNumericCellValue();
  out.println(value4);
  HSSFCell cell5  = row.getCell(4);
  value5 = cell5.getNumericCellValue();
  out.println(value4);

}
}
catch(Exception e){
    System.out.println(e); 
}
}
%>