package co.jufeng.web.util;

import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.io.Writer;  
import java.util.HashMap;
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
///**
// * FreeMarker模版引擎工具
// * @author ZhengJianYan
// * @since JDK1.6
// * @version	V1.0, 2012-06-20
// * @see UpdateName:
// * @see UpdateDate:
// * @see Copyright:JuFeng
// * @see QQ:375273058
// * @see <a href="mailto:13664988858@139.com">Email</a>
// */
public class FreeMarkerUtil {  

//	/** 
//     * 获取指定目录下的模板文件 
//     * @param name       模板文件的名称 
//     * @param pathPrefix 模板文件的目录 
//     */  
    public static Template getTemplate(String name, File ftlTemplatePath) throws IOException{  
        @SuppressWarnings("deprecation")
		Configuration cfg = new Configuration(); 
        cfg.setDirectoryForTemplateLoading(ftlTemplatePath);
        cfg.setDefaultEncoding("UTF-8");      
        Template template = cfg.getTemplate(name);   
        return template;  
    }  
      
//    /** 
//     * 根据模板文件输出内容到控制台 
//     * @param name       模板文件的名称 
//     * @param pathPrefix 模板文件的目录 
//     * @param rootMap    模板的数据模型 
//     */  
    public static void print(String name, String ftlTemplatePath, Map<String,Object> rootMap) throws TemplateException, IOException{  
    	getTemplate(name, new File(ftlTemplatePath)).process(rootMap, new PrintWriter(System.out));  
    }  
      
//    /** 
//     * 根据模板文件输出内容到指定的文件中 
//     * @param name       模板文件的名称 
//     * @param pathPrefix 模板文件的目录 
//     * @param rootMap    模板的数据模型 
//     * @param file       内容的输出文件 
//     */  
    public static void createFile(String ftlName, String ftlTemplatePath, Map<String,Object> rootMap, String outFile) throws TemplateException, IOException{  
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));  
        getTemplate(ftlName, new File(ftlTemplatePath)).process(rootMap, out); //将模板文件内容以UTF-8编码输出到相应的流中  
        if(null != out){  
            out.close();  
        }  
    }  
   
	
	public static void main(String[] args) {
		Map<String,Object> root = new HashMap<String,Object>();
		Map<String,Object> i18n = new HashMap<String,Object>();
		i18n.put("title", "飓丰软件科技有限公司");
		root.put("i18n", i18n);
		try {
			String pathp = "/home/jufeng/tmp/freemarker/";
			new File(pathp).mkdirs();
			FreeMarkerUtil.print("index.ftl", "/home/jufeng/tmp/freemarker", root);
			FreeMarkerUtil.createFile("index.ftl", "/home/jufeng/tmp/freemarker", root, "/home/jufeng/tmp/freemarker/index.html");
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}  