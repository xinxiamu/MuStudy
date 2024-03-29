package com.mu.utils;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * String工具类
 * @author 
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	
	public static boolean isEmpty(Object str) {
		if (str == null || str.equals("") || "null".equals(str)) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		if (list == null||list.isEmpty()) {
			return true;
		}
		return false;
	}
	

	/**
	 * 去掉所有空格
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	/**
	 * 提取html文件中的文本信息。
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {      
        String htmlStr = inputString; // 含html标签的字符串      
        String textStr = "";      
        java.util.regex.Pattern p_script;      
        java.util.regex.Matcher m_script;      
        java.util.regex.Pattern p_style;      
        java.util.regex.Matcher m_style;      
        java.util.regex.Pattern p_html;      
        java.util.regex.Matcher m_html;      
    
        java.util.regex.Pattern p_html1;      
        java.util.regex.Matcher m_html1;      
    
       try {      
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>      
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>      
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式      
            String regEx_html1 = "<[^>]+";      
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);      
            m_script = p_script.matcher(htmlStr);      
            htmlStr = m_script.replaceAll(""); // 过滤script标签      
    
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);      
            m_style = p_style.matcher(htmlStr);      
            htmlStr = m_style.replaceAll(""); // 过滤style标签      
    
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);      
            m_html = p_html.matcher(htmlStr);      
            htmlStr = m_html.replaceAll(""); // 过滤html标签      
    
            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);      
            m_html1 = p_html1.matcher(htmlStr);      
            htmlStr = m_html1.replaceAll(""); // 过滤html标签      
    
            htmlStr = htmlStr.replaceAll("\t", "").replaceAll("\n", "").replaceAll("\r", "")
            		.replaceAll("&amp;","").replaceAll("&lt;", "").replaceAll("&gt;", "")
            		.replaceAll("&nbsp;", "").replaceAll("<br/>", "").replaceAll("&ldquo;", "");
            textStr = htmlStr;      
    
        } catch (Exception e) {   
        	e.printStackTrace();
        }      
    
       return textStr;// 返回文本字符串      
    }
	
	/**
	 * 获取独一无二的字符串系列号。
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		 StringBuffer htmlStr = new StringBuffer();  
	        htmlStr.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>")  
	               .append("<html xmlns='http://www.w3.org/1999/xhtml' xml:lang='en'><head><title>aaa</title><mce:script type='text/javascript'></mce:script>")  
	               .append("<link href='static_files/help.css' mce_href='static_files/help.css' rel='stylesheet' type='text/css' media='all' />")  
	               .append("</head><body><ul><li>XXXX</li></ul></body></html>")
	               .append("<span style='line-height: 19px; font-family: 宋体; color: rgb(85,85,85)'>李志峰将货物来回老家，扣货要求货主赔偿，强迫货主到河南商丘处理，后货主按司机要求打钱，司机安排另一台车将货倒走，送到目的地。无论什么原因，扣货要挟的司机一律被列为驾驶员黑名单。</span></p>");  
	          
	        System.out.println(Html2Text(htmlStr.toString()));
	}

}
