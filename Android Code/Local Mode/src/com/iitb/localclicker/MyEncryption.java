package com.iitb.localclicker;

import java.io.UnsupportedEncodingException;
/*import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;*/
import org.apache.commons.codec.binary.Base64;

public class MyEncryption {
	
	public int randomDigit(){
		double random = Math.random();
		return (int)(random * 10);
	}
	public  String encrypt(String msg){
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
		String encryptedText = new String(Base64.encodeBase64(cipher.toString().getBytes()));;
		return encryptedText;
	}
	public String decrypt(String cipher){
		byte[] cipherText = Base64.decodeBase64(cipher.getBytes());
		String decodedbase64 = "";
		try {
			decodedbase64 = new String(cipherText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer msg = new StringBuffer();
		int len = decodedbase64.length();
		int ival = (decodedbase64.charAt(2) - 65);
		for(int i=3;i<len;i+=2){
			msg.append((char) (decodedbase64.charAt(i) - ival - 19));		
		}		
		return msg.toString();
	}
	
	/*private static final String ALGORITHM = "AES";
	private static final String sharedKey = "AakashClickerApplication";
	private static final byte[] privateKey =  sharedKey.getBytes();	
	public static String encrypt(String msg) throws Exception {
		Key mykey = keyGenerator();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, mykey);
		byte[] encMsg = cipher.doFinal(msg.getBytes());
		String encryptedMsg = new String(Base64.encodeBase64(encMsg));
		return encryptedMsg;
	}
	public static String decrypt(String cipherMsg) throws Exception {
	        Key mykey = keyGenerator();
        	Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, mykey);
	        byte[] decordedValue = Base64.decodeBase64(cipherMsg.getBytes());
        	byte[] decMsg = cipher.doFinal(decordedValue);
	        String decryptedMsg = new String(decMsg);
	        return decryptedMsg;
    	}
	    
	private static Key keyGenerator() throws Exception {
        	Key myKey = new SecretKeySpec(privateKey, ALGORITHM);
        	return myKey;
    	}*/
	/*public static void main(String st[]){
		AESCrypto ase = new AESCrypto();
		System.out.println("Started");
		try {
			String en =ase.encrypt("ra");
			System.out.println(ase.decrypt(en));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}*/
}
