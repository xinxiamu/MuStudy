package co.jufeng.util.encrypt.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class AES {
	
	private String key;
	
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	
	public static final int ENCRYPT_MODE = 1;
	
	public static final int DECRYPT_MODE = 2;
	
	public AES(String key){
		this.key = key;
	}
	  
	public byte[] encodeByte(String str){
		try {
			byte[] textBytes = str.getBytes("UTF-8");
			Cipher cipher = getCipher(ENCRYPT_MODE);
			return cipher.doFinal(textBytes);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String encode(String str){
		try {
			byte[] encodeByte = encodeByte(str);
			return Base64.getEncoder().encodeToString(encodeByte);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String decode(byte[] bytes) {
		try {
			Cipher cipher = getCipher(DECRYPT_MODE);
			return new String(cipher.doFinal(bytes), "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String decode(String base64) {
		try {
			return decode(Base64.getDecoder().decode(base64));
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	private Cipher getCipher(int i) {
		try {
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
//			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(i, newKey, ivSpec);
			return cipher;
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}