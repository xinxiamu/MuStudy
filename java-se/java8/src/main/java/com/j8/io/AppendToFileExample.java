package com.j8.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 在文件中不断添加文本。
 * @author mutou
 *
 */
public class AppendToFileExample {

	public static void main(String[] args) {
		try{
    		String data = " This content will append to the end of the file\n";

    		File file =new File(System.getProperty("user.dir"),"new2.txt");

    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}

    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();

	        System.out.println("Done");

    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}

}
