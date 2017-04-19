package co.jufeng.login;

import java.io.File;

import co.jufeng.login.gene.KeyGenerator;
import co.jufeng.login.key.KeyManager;
import co.jufeng.login.key.PPKey;

public class Snippet {
	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
		System.out.println(path);
		
		String domain="zyx56.net";
		String cookieName="jufeng";
		KeyGenerator generator=new KeyGenerator(domain, cookieName);
		generator.generateKey(path + "zyx56-net.cer");
		System.out.println("finish");
		
		KeyManager.loadKey(path + "zyx56-net.cer");
		PPKey ppKey = KeyManager.getMap().get("zyx56.net");
		System.out.println("PublicKey=" + ppKey.getPublicKey());
		System.out.println();
		System.out.println("PrivateKey=" + ppKey.getPrivateKey());
		
	}
}

