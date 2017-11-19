package com.j8.io;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * InputStream转换为字符串
 */
public class InputStream2Str {

    static String myString = "Java Sample Approach";

    public static void main(String[] args) throws IOException {

        // using IOUtils.toString
        InputStream is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        String result1 = IOUtils.toString(is, "UTF-8");
        System.out.println("using IOUtils.toString >> " + result1);

        // using CharStreams
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        String result2 = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        System.out.println("using CharStreams >> " + result2);

        // using Scanner
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        Scanner s = null;
        try {
            s = new Scanner(is);
            s.useDelimiter("\\Z");
            String result3 = s.hasNext() ? s.next() : "";

            System.out.println("using Scanner >> " + result3);
        } finally {
            s.close();
        }

        // using StringWriter
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        String result4 = writer.toString();

        System.out.println("using StringWriter >> " + result4);

        // using ByteArrayOutputStream
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        ByteArrayOutputStream bArrOutStrem = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            bArrOutStrem.write(buffer, 0, length);
        }
        String result5 = bArrOutStrem.toString("UTF-8");

        System.out.println("using ByteArrayOutputStream >> " + result5);

        // using BufferedInputStream
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int ris = bis.read();
        while (ris != -1) {
            buf.write((byte) ris);
            ris = bis.read();
        }
        String result6 = buf.toString();

        System.out.println("using BufferedInputStream >> " + result6);

        // using Java 8 Stream
        is = new ByteArrayInputStream(myString.getBytes("UTF-8"));

        String result7 = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

        System.out.println("using Java 8 Stream >> " + result7);
    }
}
