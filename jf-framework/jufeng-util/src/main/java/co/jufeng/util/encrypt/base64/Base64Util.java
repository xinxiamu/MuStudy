package co.jufeng.util.encrypt.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util { 
	
	private Base64Util() {
	}
	
	public static String getEncoderBase64(String string) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = string.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = Base64.getEncoder().encodeToString(b);  
        }  
        return s;  
    }  
  
     
    public static String getDecoderBase64(String base64String) {  
        byte[] b = null;  
        String result = null;  
        if (base64String != null) {  
            try {  
                b = Base64.getDecoder().decode(base64String);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();
            }  
        }  
        return result;  
    }  
}