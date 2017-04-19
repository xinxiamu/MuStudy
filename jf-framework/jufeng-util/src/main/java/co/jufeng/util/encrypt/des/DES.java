package co.jufeng.util.encrypt.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DES {
	
	private Key key;
	
	private String encryptAlgorithm = "DES";
	
	public DES(String strKey) {
		try {
			KeyGenerator generator;
			generator = KeyGenerator.getInstance(encryptAlgorithm);
			generator.init(new SecureRandom(strKey.getBytes()));
			this.key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = Base64.getEncoder().encodeToString(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	public String getDesString(String strMi) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = Base64.getDecoder().decode(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(encryptAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(encryptAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	
}