package co.jufeng.login.key;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PPKey {
	private String KEY_ALGORITHM = "RSA";
	public static String PUBLIC_KEY = "RSAPublicKey";
	public static String PRIVATE_KEY = "RSAPrivateKey";
	private String puText = "";
	private String prText = "";
	private Map<String, Object> keyMap = null;

	public PPKey() {
		try {
			initKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initKey() throws Exception {
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
	}

	public String getPublicKey() throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		byte[] publicKey = key.getEncoded();
		try {
			puText = encryptBASE64(publicKey);
			return puText;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String getPrivateKey() throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] privateKey = key.getEncoded();
		try {
			prText = encryptBASE64(privateKey);
			return prText;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private byte[] decryptBASE64(String key) throws Exception {
		try {
			return Base64.getDecoder().decode(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private String encryptBASE64(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
	}

	public String sign(String privateKeyText, String plainText)
			throws Exception {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					privateKeyText.getBytes());
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);

			// 用私钥对信息生成数字签名
			Signature signet = Signature.getInstance("MD5withRSA");
			signet.initSign(prikey);
			signet.update(plainText.getBytes());
			byte[] signed = signet.sign();// base64.encodeToByte(signet.sign());
			return new String(signed);
		} catch (Exception e) {
			System.out.println("签名失败");
			e.printStackTrace();
			throw e;
		}

	}

	public boolean verify(String publicKeyText, String plainText,
			String signText) throws Exception {
		try {
			// 解密由base64编码的公钥,并构造X509EncodedKeySpec对象
			// X509EncodedKeySpec bobPubKeySpec = new
			// X509EncodedKeySpec(Base64.decode(pubKeyText));
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
					decryptBASE64(publicKeyText));
			// RSA对称加密算法
			java.security.KeyFactory keyFactory = java.security.KeyFactory
					.getInstance("RSA");
			// 取公钥匙对象
			java.security.PublicKey pubKey = keyFactory
					.generatePublic(bobPubKeySpec);
			// 解密由base64编码的数字签名
			byte[] signed = decryptBASE64(signText);
			java.security.Signature signatureChecker = java.security.Signature
					.getInstance("MD5withRSA");
			signatureChecker.initVerify(pubKey);
			signatureChecker.update(plainText.getBytes());
			// 验证签名是否正常
			if (signatureChecker.verify(signed))
				return true;
			else
				return false;
		} catch (Throwable e) {
			System.out.println("校验签名失败");
			e.printStackTrace();
			return false;
		}
	}

	public String getPuText() {
		return puText;
	}

	public String getPrText() {
		return prText;
	}

	public Map<String, Object> getKeyMap() {
		return keyMap;
	}

	@SuppressWarnings("unchecked")
	public void read(String path) {
		if (path == null) {
			return;
		}
		File f = new File(path);
		if (f.exists() == false) {
			return;
		}
		ObjectInputStream oiStream = null;
		try {
			oiStream = new ObjectInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			keyMap = (Map<String, Object>) oiStream.readObject();
			oiStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "PPKey [KEY_ALGORITHM=" + KEY_ALGORITHM + ", puText=" + puText
				+ ", prText=" + prText + ", keyMap=" + keyMap + "]";
	}
	
}
