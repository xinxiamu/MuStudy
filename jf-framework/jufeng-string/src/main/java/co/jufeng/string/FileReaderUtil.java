package co.jufeng.string;
import java.io.*;

public class FileReaderUtil {
	
	public static String read(String fileName) throws Exception {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String s;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		fr.close();
		return s;
	}

}