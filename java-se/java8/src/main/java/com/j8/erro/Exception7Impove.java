package com.j8.erro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * try{}在java7版本中的改善。
 */
public class Exception7Impove {

    public static void main(String[] args) {
        jdk6AndEarly();

//        errorNew();
    }

    /**
     * 在jdk6以及之前的版本。要手动关闭系统资源。
     */
    public static void jdk6AndEarly() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C://readfile/input.txt"));
            String line;
            while (null != (line = br.readLine())) {
                // process each line of File
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //要手动关闭io资源
            try {
                if (null != br)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 不需要手动关系资源，会自动关闭。
     */
    public static void errorNew(){
        try (BufferedReader br = new BufferedReader(new FileReader("C://readfile/input.txt"))) {
            String line;
            while (null != (line = br.readLine())) {
                // processing each line of file
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
