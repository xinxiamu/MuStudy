package co.jufeng.util.encrypt.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64UtilTest {
	
	public static void main(String[] args) {
		String string = "{\"loginBackUrl\":\"http://b2c-jingtao.xcsqjr.com:8080/xcsqjr-controller-mall-b2c/cart/addCartAfterLogin.do?goodsId=395&num=1\"}";
		String base64String = getEncoderBase64(string);
		System.out.println(base64String);
		System.out.println(getDecoderBase64(base64String));
	}
	 
    public static String getEncoderBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = Base64.getEncoder().encodeToString(b);  
        }  
        return s;  
    }  
  
     
    public static String getDecoderBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            try {  
                b = Base64.getDecoder().decode(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  

}
