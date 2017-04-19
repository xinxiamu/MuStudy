package co.jufeng.login.key;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class PPKeys {
	private String keyPwd = "";
	private JEncoder jEncoder = null;
	
    public static final String KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";  
    
    private static final int MAX_ENCRYPT_BLOCK = 117;  
    
    private static final int MAX_DECRYPT_BLOCK = 128;  

	public PPKeys() {
		jEncoder = new JEncoder();
	}

	public PPKeys(String keyPwd) {
		this.keyPwd = keyPwd;
		jEncoder = new JEncoder(keyPwd);
	}

	public String byteToString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			if (i == 0) {
				buffer.append(bytes[i]);
			} else {
				buffer.append(" " + bytes[i]);
			}
		}
		return buffer.toString();
	}

	public byte[] byteStringToByte(String byteString) {
		String[] arr = byteString.split(" ");
		byte[] bytes = new byte[arr.length];
		for (int j = 0; j < arr.length; j++) {
			bytes[j] = new Byte(arr[j]);
		}
		return bytes;
	}

	public String getKeyPwd() {
		return keyPwd;
	}

	public JEncoder getjEncoder() {
		return jEncoder;
	}
	
    public String encrypt1( PrivateKey privateKey, String text) throws Exception {  
    	byte[] msg = text.getBytes();
    	Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        int inputLen = msg.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(msg, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(msg, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return byteToString(encryptedData);  
    }  


	public String encrypt(PrivateKey privateKey, String text) throws Exception {
		String encryptString = "";
		byte[] msg = text.getBytes();

		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] msg1 = cipher.doFinal(msg);

			String bstring = byteToString(msg1);
			encryptString = jEncoder.encrypt(bstring);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return encryptString;
	}

    public String decrypt1(PublicKey publicKey, String text)  throws Exception {  
    	String bstring = jEncoder.decrypt(text);
		byte[] msg1 = byteStringToByte(bstring);
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        int inputLen = msg1.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(msg1, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(msg1, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return byteToString(decryptedData);  
    }  

	public String decrypt(PublicKey publicKey, String text) throws Exception {
		String deEncryptString = "";
		try {
			String bstring = jEncoder.decrypt(text);
			byte[] msg1 = byteStringToByte(bstring);

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] msg2 = cipher.doFinal(msg1);
			deEncryptString = new String(msg2);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return deEncryptString;
	}
}
