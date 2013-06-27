package com.clicker.util;

public class OptionEncription {
	public static void main (String st[]){	
		OptionEncription crypt = new OptionEncription();
		StringBuffer cipher = crypt.charEC("BC");
		System.out.println( cipher);
		System.out.println(crypt.charDC(cipher.toString()));
		cipher = crypt.numberEC("27");
		System.out.println(cipher);
		System.out.println(crypt.numberDC(cipher.toString()));
	}
	
	public int randomDigit(){
		double random = Math.random();
		return (int)(random * 10);
	}
	public StringBuffer charEC(String msg){
		StringBuffer cipher = new StringBuffer();
		int len = msg.length();		
		double random = Math.random();
		int ival  = ((int)(random * 100)) % 17;		
		cipher.append((char) (randomDigit() + ival + 65));
		cipher.append((char) (len + 64)); 
		cipher.append((char) (ival + 65));
		for(int i=0;i<len;i++){
			cipher.append((char) (msg.charAt(i)  + ival));	
			cipher.append((char) (randomDigit() + ival + 65));
		}		
		return cipher;
	}
	public StringBuffer charDC(String cipher){
		StringBuffer msg = new StringBuffer();
		int len = cipher.length();
		int ival = (cipher.charAt(2) - 65);
		for(int i=3;i<len;i+=2){
			msg.append((char) (cipher.charAt(i) - ival));		
		}		
		return msg;
	}
	public StringBuffer numberEC(String msg){
		StringBuffer cipher = new StringBuffer();
		int len = msg.length();		
		double random = Math.random();
		int ival  = ((int)(random * 100)) % 15;		
		cipher.append((char) (randomDigit() + ival + 65));
		cipher.append((char) (len + 64)) ;
		cipher.append((char) (ival + 65));
		for(int i=0;i<len;i++){
			cipher.append((char) (msg.charAt(i)  + ival + 19));	
			cipher.append((char) (randomDigit() + ival + 65));
		}		
		return cipher;
	}
	public StringBuffer numberDC(String cipher){
		StringBuffer msg = new StringBuffer();
		int len = cipher.length();
		int ival = (cipher.charAt(2) - 65);
		for(int i=3;i<len;i+=2){
			msg.append((char) (cipher.charAt(i) - ival - 19));		
		}		
		return msg;
	}
}
