package com.clicker.poll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clicker.databaseconn.DatabaseConnection;

import com.clicker.poll.Course;

/**
 * Servlet implementation class Poll
 */
public class Poll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int no_yes=0,no_no=0;
	static String instructor_id="",student_id="";
	String poll_question;
	ArrayList<Course> al=new ArrayList<Course>();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Poll() {
        super();
        poll_question="";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * This method will be used by the Ajax request from the student.
		 */
		/*
		 * This method will also be used as the post request of a teacher.
		 */
		System.out.println("Inside poll");
		HttpSession session1=request.getSession(false);
		System.out.println("Inpoll " + session1.getAttribute("UserType").toString());
		boolean found=false;
		int i=0;
		//System.out.println(request.getParameter("UserType"));
			if(session1.getAttribute("UserType").equals("Instructor"))
			{
				//System.out.println("inside if");
				
					//instructor_id=session1.getAttribute("courseid").toString();
					//poll_question=request.getParameter("poll_question");
					//response.setHeader("Refresh","5");
				//System.out.println("Refreshed");
					for(i=0;i<al.size();i++)
					{
						if(al.get(i).courseID.equals(session1.getAttribute("courseID")))
						{
							found=true;
							break;
						}
					}
				
				/*instructor_id=request.getParameter("course").toString();
				poll_question=request.getParameter("poll_question");
				response.setHeader("Refresh","5");
				for(i=0;i<al.size();i++)
				{
					if(al.get(i).courseID.equals(instructor_id))
					{
						found=true;
						break;
					}
				}*/
				PrintWriter pw=response.getWriter();
				if(!found)
				{
					//System.out.println("Not Found");
					Course c= new Course();
					c.courseID=session1.getAttribute("courseID").toString();
					c.Question=request.getParameter("poll_question");
					c.quizTime=new java.util.Date().getTime();
					al.add(c);
					//response.setHeader("Refresh", "5");
//					response.sendRedirect("../../../WebContent/jsp/polls/poll.jsp?no_yes="+);
					session1.setAttribute("active", "true");
					session1.setAttribute("Question", c.Question);
					session1.setAttribute("TimeStamp",c.quizTime);
					System.out.println("Question added");
					try{
					DatabaseConnection dbconn=new DatabaseConnection();
					Connection con=dbconn.createDatabaseConnection();
					Statement st=con.createStatement();
					
					st.executeUpdate("insert into pollquestion(Question) values('"+c.Question+"')",Statement.RETURN_GENERATED_KEYS);
					ResultSet rs=st.getGeneratedKeys();
					if (rs.next()) {
						c.PollID = rs.getInt(1);
					} else {
						throw new RuntimeException("PIB, can't find most recent insert we just entered");
					}
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else
				{
					double newtime=new java.util.Date().getTime();
					if(Math.round(30-((newtime-al.get(i).quizTime)/1000))>0)//al.get(i).timeout>0)
					{
						//response.setHeader("Refresh","25");
						//response.setHeader("Refresh", new Double(30-((new java.util.Date().getTime()-al.get(i).quizTime)/1000)).toString());
						//al.get(i).timeout-=30;
					}
					//System.out.println("Found");
//					pw.println("Your Question to be polled is");
//					pw.println(al.get(i).Question);
//					pw.println("Current Results");
					if(al.get(i).no_no!=0 || al.get(i).no_yes!=0)
					{
						al.get(i).yes_percent=(al.get(i).no_yes/(al.get(i).no_no+al.get(i).no_yes+0.0)*100);
						al.get(i).no_percent=(al.get(i).no_no/(al.get(i).no_no+al.get(i).no_yes+0.0)*100);
						System.out.println(al.get(i).yes_percent+":"+al.get(i).no_percent);
						response.setContentType("text/html");
						
						pw.println("<p> Total Students participated: "+(al.get(i).no_yes+al.get(i).no_no)+"</p><br>");
						pw.println("<div style=\"float: left; \" >YES<br>("+Math.round(al.get(i).yes_percent)+")</div>");
						pw.println("<div style=\"margin-left: 50px; height: 50px; width: "+(5*al.get(i).yes_percent)+"px;background-color: green; opacity: 0.70;\"></div><br>");
						pw.println("<div style=\"float: left; width: 70px;\" >NO<br>("+Math.round(al.get(i).no_percent)+")</div>");
						pw.println("<div style=\"margin-left: 50px; height: 50px; width: "+(5*al.get(i).no_percent)+"px;background-color: red; opacity: 0.70;\"></div>");
												
//						pw.println("<html>");
//						pw.println("<body>");
//						pw.println("<table>");
//						pw.println("<tr>");
//						pw.println("<td>Yes</td>");
//						pw.println("<td><img src=\"poll.png\" width='"+al.get(i).yes_percent+"' height=25/> "+al.get(i).yes_percent+"%"+"</td></tr>");
//						pw.println("<tr>");
//						pw.println("<td>No</td>");
//						pw.println("<td><img src=\"poll.png\" width='"+al.get(i).no_percent+"' height=25/>"+al.get(i).no_percent+"%"+"</td></tr>");
//						pw.println("</table>Total Number of votes:"+(al.get(i).no_no+al.get(i).no_yes)+"</body></html>");
//						session1.setAttribute("total_votes",al.get(i).no_no+al.get(i).no_yes);
//						session1.setAttribute("no_yes", al.get(i).yes_percent);
//						session1.setAttribute("no_no", al.get(i).no_percent);
						

						if(Math.round(30-((newtime-al.get(i).quizTime)/1000))<=0)
						{
							pw.println("<input id='submitB' type='button' value='  Reset  ' onclick='reset()' > ");
							al.get(i).toBeRemoved=true;
						}
						if(al.get(i).toBeRemoved)
						{
							session1.setAttribute("Results",al.get(i));
							session1.setAttribute("active","false");
							al.remove(i);
							System.out.println("Course Removed");
						}
					}
					else
					{System.out.println("Time Remaining:"+Math.round((30-((newtime-al.get(i).quizTime)/1000))));
						response.setContentType("text/html");
						
						pw.println("<p> Total Students participated: "+(0)+"</p><br>");
						pw.println("<div style=\"float: left; \" >YES<br>("+Math.round(al.get(i).yes_percent)+")</div>");
						pw.println("<div style=\"margin-left: 50px; height: 50px; width: "+(5*al.get(i).yes_percent)+"px;background-color: green; opacity: 0.70;\"></div><br>");
						pw.println("<div style=\"float: left; width: 70px;\" >NO<br>("+Math.round(al.get(i).no_percent)+")</div>");
						pw.println("<div style=\"margin-left: 50px; height: 50px; width: "+(5*al.get(i).no_percent)+"px;background-color: red; opacity: 0.70;\"></div>");
//						pw.println("<html>");
//						pw.println("<body>");
//						pw.println("<table>");
//						pw.println("<tr>");
//						pw.println("<td>Yes</td>");
//						pw.println("<td><img src=\"poll.png\" width=0 height=25/>0%</td></tr>");
//						pw.println("<tr>");
//						pw.println("<td>No</td>");
//						pw.println("<td><img src=\"poll.png\" width=0 height=25/>0%</td></tr>");
//						pw.println("</table>Total Number of votes:0</body></html>");
						//session1.setAttribute("total_votes", 0);
						if(Math.round(30-((newtime-al.get(i).quizTime)/1000))<=0)
						{
							pw.println("<input id='submitB' type='button' value='  Reset  ' onclick='reset()' > ");
							al.get(i).toBeRemoved=true;
						}
						if(al.get(i).toBeRemoved)
						{
							session1.setAttribute("Results",al.get(i));	
							session1.setAttribute("active","false");
							al.remove(i);
							System.out.println("Course Removed");
						}
					}
				}
				//response.setHeader("Refresh","5");
				//poll_question=request.getParameter("poll_question");
				/*PrintWriter pw=response.getWriter();
				pw.println("Your Question to be polled is");
				pw.println(poll_question);
				pw.println("Current Results");
				if(no_no!=0 || no_yes!=0)
				{
					double yes_percent=(no_yes/(no_no+no_yes+0.0))*100;
					double no_percent=(no_no/(no_no+no_yes+0.0))*100;
					System.out.println(yes_percent+":"+no_percent);
					response.setContentType("text/html");
					pw.println("<html>");
					pw.println("<body>");
					pw.println("<table>");
					pw.println("<tr>");
					pw.println("<td>Yes</td>");
					pw.println("<td><img src=\"poll.png\" width='"+yes_percent+"' height=\"100%\"/> "+yes_percent+"%"+"</td></tr>");
					pw.println("<tr>");
					pw.println("<td>No</td>");
					pw.println("<td><img src=\"poll.png\" width='"+no_percent+"' height=\"100%\"/>"+no_percent+"%"+"</td></tr>");
					pw.println("</table></body></html>");*/
				//}
				//cannot do sendRedirect after you have committed a response.	
				
			}
			else
			{
				/*
				 * When a student makes a request we see if his course is there in the poll list.
				 * If it is not then we print out that no live poll currently.(case dealt)
				 * Otherwise there are two cases 
				 * 1.Either he has already voted
				 * 2.Or he has voted.
				 * in first case we will have to see if he is already there in the arraylist of the course object.
				 * if he is then he has already voted and a message can be flashed on the screen that you cannot vote twice.
				 * else if he is not in the arraylist then we know he  is voting for the first time and he is added to arraylist 
				 * and shown the question.
				 */
				//System.out.println("Inside else");
				//student_id=session1.getAttribute("courseid").toString();
				//System.out.println(student_id+":"+instructor_id);
				System.out.println(session1.getAttribute("StudentID").toString()+session1.getAttribute("courseID").toString());
				response.setContentType("text/plain");
				PrintWriter out=response.getWriter();
				//if(student_id.equals(instructor_id))
				int j=0,length=al.size();
				boolean course_found=false;
				for(j=0;j<length;j++)
				{
					if(al.get(j).courseID.equals(session1.getAttribute("courseID")))
					{
						course_found=true;
						break;
					}
				}
				if(!course_found)
				{
					System.out.println("No Live Poll Currently");
					out.println("No Live Poll Currently");
				}
				else
				{
					int size=al.get(j).alist.size();
					boolean student_found=false;
					for(int k=0;k<size;k++)
					{
						if(al.get(j).alist.get(k).contains(session1.getAttribute("StudentID").toString()))
						{
							System.out.println("Twice");
							student_found=true;
							out.println("You cannot cast your vote twice");
//							out.println("Refresh Page for new Polls!");
							break;
						}
					}
					if(!student_found)
					{
						System.out.println("Once");
						//al.get(j).alist.add(session1.getAttribute("StudentID").toString());
						/*
						 * Only after student has given the vote we will add him to the student list.
						 * Therefore,the above line  of code is commented out.
						 */
						//System.out.println("Student ID "+session1.getAttribute("StudentID").toString()+"added.");
						out.println(al.get(j).Question+"~"+Math.round((30-((new java.util.Date().getTime()-al.get(j).quizTime)/1000))));
					}
				}
				//out.println(poll_question);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * This method will be used for the Ajax response to the question by the student.
		 */
		HttpSession session=request.getSession(false);
				boolean found=false;
		//System.out.println("Here");
		try
		{
			String vote=request.getParameter("vote").toString();
			System.out.println(vote);
			if(vote!=null)
			{
						
				if(vote.equalsIgnoreCase("  Yes"))
				{
				for(int i=0;i<al.size();i++)
				{
					System.out.println(al.get(i).courseID+":"+session.getAttribute("courseID").toString());
					if(al.get(i).courseID.equals(session.getAttribute("courseID").toString()))
					{
						System.out.println("Inside If:"+al.get(i).courseID+":"+session.getAttribute("courseID").toString());
						found=true;
						al.get(i).alist.add(session.getAttribute("StudentID").toString());
						System.out.println("Student ID "+session.getAttribute("StudentID").toString()+"added.");
						al.get(i).no_yes++;
						try{
							DatabaseConnection dbconn1=new DatabaseConnection();
							Connection con1=dbconn1.createDatabaseConnection();
							Statement st1=con1.createStatement();
							java.sql.Timestamp ts=new java.sql.Timestamp(new java.util.Date().getTime());
							st1.executeUpdate("insert into poll(StudentID,Response,TimeStamp,CourseID,PollID) values('"+session.getAttribute("StudentID").toString()+"',1,'"+ts+"','"+session.getAttribute("courseID").toString()+"','"+al.get(i).PollID+"')");
							}
							catch(Exception e)
							{
								System.out.println(e);
							}
						break;
					}
				}
				if(!found)
				{
					System.out.println("Code should not be executed:Yes");
					Course c=new Course();
					c.courseID=request.getParameter("course");
					c.no_yes++;
					al.add(c);
				}
				//no_yes++;
				//System.out.println("Yes:"+no_yes);
//				response.sendRedirect("successyes.jsp");
				response.sendRedirect("jsp/studentJspPages/StudentQuizListener.jsp");
			}
			else if(vote.equalsIgnoreCase("  No"))
			{
				for(int i=0;i<al.size();i++)
				{
					System.out.println("Inside If:"+al.get(i).courseID+":"+session.getAttribute("courseID").toString());
					if(al.get(i).courseID.equals(session.getAttribute("courseID").toString()))
					{
						System.out.println("Inside If:"+al.get(i).courseID+":"+session.getAttribute("courseID").toString());
						found=true;
						al.get(i).alist.add(session.getAttribute("StudentID").toString());
						System.out.println("Student ID "+session.getAttribute("StudentID").toString()+"added.");
						al.get(i).no_no++;
						try{
							DatabaseConnection dbconn2=new DatabaseConnection();
							Connection con2=dbconn2.createDatabaseConnection();
							Statement st2=con2.createStatement();
							java.sql.Timestamp ts=new java.sql.Timestamp(new java.util.Date().getTime());
							st2.executeUpdate("insert into poll(StudentID,Response,TimeStamp,CourseID,PollID) values('"+session.getAttribute("StudentID").toString()+"',0,'"+ts+"','"+session.getAttribute("courseID").toString()+"','"+al.get(i).PollID+"')");
							}
							catch(Exception e)
							{
								System.out.println(e);
							}
						break;
					}
				}
				if(!found)
				{
					System.out.println("Code should not be executed:No");
					Course c=new Course();
					c.courseID=session.getAttribute("courseID").toString();
					c.no_no++;
					al.add(c);
				}
				//no_no++;
				//System.out.println("No:"+no_no);
//				response.sendRedirect("successno.jsp");
				response.sendRedirect("jsp/studentJspPages/StudentQuizListener.jsp");
			}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
