package com.example.ymu;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class MainTest {
    @Test
    public void encryptPwd() {
    	BasicTextEncryptor encryptor = new BasicTextEncryptor();
    	encryptor.setPassword("123456");
    	String encrypted = encryptor.encrypt("abcd");
    	System.out.println(encrypted);
    }
}
