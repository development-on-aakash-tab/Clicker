package com.clicker.util;

import java.io.UnsupportedEncodingException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

/*
 * References :
 * http://stackoverflow.com/questions/10303767/encrypt-and-decrypt-in-java
 */

public class CryptoHelper {

	public static void main (String st[]){
		CryptoHelper crypt = new CryptoHelper();
		String plainText = "ABC";
		System.out.println("Plain Text : " + plainText);
		String cipher = crypt.encrypt(plainText);
		System.out.println("Encrypted Text : " + cipher);
		String msg = crypt.decrypt(cipher);
		System.out.println("decrypted Text : " + msg);
	}
	
	public String encrypt(String msg){		
		int len = msg.length();
		StringBuffer cipher = new StringBuffer();
		double random = Math.random();
		int interval = (int)(random * 100);
		if(interval>93)
			interval %= 93;
		char irval = (char) ((interval) + 32);
		int start =interval % len;
		msg = (msg.substring(start) + msg.substring(0,start));
		for(int i=0;i<len;i++){
			char ch = (char) ((msg.charAt(i) + (interval + i)) % 93 + 32);
			cipher.append(ch);
		}		
		cipher.append(irval);
		String cip = cipher.toString();
		//cip = cip.replace("=", "~").replace("&", "}");		
		return cip;
	}
	
	public String decrypt(String cipher){		
		int len = cipher.length()-1;
		//cipher = cipher.replace("~", "=").replace("}", "&");		
		StringBuffer message = new StringBuffer();
		int interval = cipher.charAt(len) - 32;
		int start =len - (interval % len) ;
		for(int i=0;i<len;i++){
			int t = ((cipher.charAt(i) - (interval + i)) % 93 - 32);
			if(t<0){
				t += 93;
			}
			char ch = (char) t;
			message.append(ch);
		}		
		String msg = (message.substring(start, len) + message.substring(0,start));
		return msg;
	}
}

class TrippleDes {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String ALGORITHM = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    SecretKey key;

    public TrippleDes() throws Exception {
        myEncryptionKey = "thisisclickerfromiitbombay";
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(ALGORITHM);
        cipher = Cipher.getInstance(ALGORITHM);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String message) {
        String encryptedText = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = message.getBytes(UNICODE_FORMAT);
            byte[] cipherText = cipher.doFinal(plainText);
            encryptedText = new String(Base64.encodeBase64(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }


    public String decrypt(String cipherMessage) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherText = Base64.decodeBase64(cipherMessage.getBytes());
            byte[] plainText = cipher.doFinal(cipherText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }


    public static void main(String args []) throws Exception
    {
        TrippleDes td= new TrippleDes();
        String target="1324671235";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);
        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);
    }
}


