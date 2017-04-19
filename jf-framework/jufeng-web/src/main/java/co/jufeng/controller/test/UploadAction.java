package co.jufeng.controller.test;


import java.io.File;
import java.text.DecimalFormat;

import org.apache.commons.fileupload.FileItem;

import co.jufeng.BaseAction;
import co.jufeng.util.PathUtil;
import co.jufeng.web.multipart.commons.CommonsMultipartFile;
import co.jufeng.web.servlet.bind.annotation.DefaultAction;
import co.jufeng.web.servlet.bind.annotation.RestController;

@RestController
public class UploadAction extends BaseAction{
	
	private String savePath = PathUtil.getWebRootPath() + "static" + File.separator + "upload" + File.separator ;
	
	@DefaultAction
	public String index(CommonsMultipartFile files) throws Exception{
        System.out.println(savePath);
        File file = new File(savePath);
    	if (!file .exists()  && !file .isDirectory()) {       
    		file .mkdir();    
    	} 
        for (FileItem item : files.getFileItems()) {
        	System.out.println("获取上传文件的总共的容量：" + readableFileSize(item.getSize()));
        	item.write(new File(savePath, item.getName()));
        }
		return "test/open-upload.html";
	}
	public static String readableFileSize(long size) {  
        if (size <= 0) {  
            return "0";  
        }  
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};  
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));  
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];  
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}