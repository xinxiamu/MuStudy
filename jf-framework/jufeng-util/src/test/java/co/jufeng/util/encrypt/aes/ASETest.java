package co.jufeng.util.encrypt.aes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
//http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
public class ASETest {  
	
	static String path = System.getProperty("user.dir") + File.separator + "key.dat";
	
    public static void main(String[] args) throws Exception {  
    	 System.out.println(path);
         KeyGenerator keyGen = KeyGenerator.getInstance("AES");  
         keyGen.init(256);  
         SecretKey key = keyGen.generateKey();  
         ObjectOutputStream oop = new ObjectOutputStream(new  FileOutputStream(path));  
         oop.writeObject(key);  
         oop.close();  
          
        String strTest = "Hello, JuFeng，商品";  
        byte[] strAfterAES = encryptData(strTest.getBytes());  
        System.out.println(new String(strAfterAES));
        
        byte[] strOriContent = decryptData(strAfterAES);  
        System.out.println(new String(strOriContent));  
    }  
  
  
    public static byte[] encryptData(byte[] input) throws Exception {  
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));  
        SecretKey aeskey = (SecretKey) in.readObject();  
        Cipher c1 = Cipher.getInstance("AES");  
        c1.init(Cipher.ENCRYPT_MODE, aeskey);  
        in.close();
        byte[] cipherByte = c1.doFinal(input);  
        return cipherByte;  
    }  
  
  
    public static byte[] decryptData(byte[] input) throws Exception {  
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));  
        SecretKey aeskey = (SecretKey) in.readObject();  
        Cipher c1 = Cipher.getInstance("AES");  
        c1.init(Cipher.DECRYPT_MODE, aeskey); 
        in.close();
        byte[] clearByte = c1.doFinal(input);  
        return clearByte;  
    }  
} 