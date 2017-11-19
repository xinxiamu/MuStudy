package com.j8.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本语法。
 */
public class SyntaxDemo {

    public static void main(String[] args) {
        List<String> results = new ArrayList<>();

        String txt = "Java Sample Approach provides Java Technology, Spring Framework, Sample Code.123";

//        String pattern = "[0-9]"; //匹配正则,数字
//        String pattern = "[^0-9]"; //匹配正则,非数字
//        String pattern = "[\\t\\n\\x0B\\f\\r]\n"; //匹配正则,空格
//        String pattern = "\t[^\\s]"; //匹配正则,非空格
        String pattern = "[a-zA-Z_0-9]"; //匹配正则

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);

        while (m.find()) {
            String s = m.group();
            results.add(s);
        }

        System.out.println(results);
    }
}
