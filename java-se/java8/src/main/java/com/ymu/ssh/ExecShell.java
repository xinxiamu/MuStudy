package com.ymu.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 执行本机外部的shell命令。通常指执行linux中shell命令。
 * @author mutou
 *
 */
public class ExecShell {

	public static void main(String[] args) {
		  methodB();  
	}
	
	public static void methodB() {  
        
        BufferedReader br = null;  
        try {  
              
            Process proc = Runtime.getRuntime().exec("ping www.baidu.com");  
              
            InputStream in = proc.getInputStream();  
              
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
            String line = null;  
              
            while((line=br.readLine())!=null){  
                System.out.println(line);  
            }  
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(br!=null){  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  

}
