/*
 * This is one extra added class for simulator purpose.
 * It simply creates random number for any IDs.
 */


package com.clicker.util;
import java.util.Random;

public class GenerateRandomChar {	
	public char getRandomAnswer()
	{
		char Response ='X';
		
		Random r = new Random();
		String alphabet = "ABCD";		
		Response = alphabet.charAt(r.nextInt(alphabet.length()));
		
		return Response;
	}
	
	public static void main (String args[])
	{
		char Response ='X';
		
		Random r = new Random();
		String alphabet = "ABCD";		 
		Response = alphabet.charAt(r.nextInt(alphabet.length()));
		System.out.println( Response);
	}
}
