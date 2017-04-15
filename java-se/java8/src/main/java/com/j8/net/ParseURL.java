package com.j8.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ParseURL {

	public static void main(String[] args) {

		try {
			 URL aURL = new URL(
			 "http://example.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING");
			 System.out.println("protocol = " + aURL.getProtocol());
			 System.out.println("authority = " + aURL.getAuthority());
			 System.out.println("host = " + aURL.getHost());
			 System.out.println("port = " + aURL.getPort());
			 System.out.println("path = " + aURL.getPath());
			 System.out.println("query = " + aURL.getQuery());
			 System.out.println("filename = " + aURL.getFile());
			 System.out.println("ref = " + aURL.getRef());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void readFromUrl(String url) {
		try {
			// 读取url内容
			URL myURL = new URL(url == null || url.isEmpty() ? "http://example.com/" : url);
			URLConnection myURLConnection = myURL.openConnection();
			myURLConnection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					myURLConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writToUrl(String u) throws IOException {
		String stringToReverse = URLEncoder.encode(u, "UTF-8");

        URL url = new URL(u);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(
                                         connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();

        BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();
	}

}
