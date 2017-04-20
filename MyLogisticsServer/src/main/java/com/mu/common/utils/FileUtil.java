package com.mu.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import com.mu.common.exception.BaseException;

/**
 * 关于文件操作的工具
 * 
 * @author ZhengJianYan
 * @since JDK1.6
 * @version V1.0, 2012-06-20
 * @see UpdateName:
 * @see UpdateDate:
 * @see Copyright:JuFeng
 * @see QQ:375273058
 * @see <a href="mailto:13664988858@139.com">Email</a>
 */
public class FileUtil {
	
	/**
     * 
     * @描述:创建唯一的文件名。
     *
     * @param suffix 文件后缀名如：jpg、png、txt、doc
     */
    public static String createUniqueFileName(String suffix) {
        String uuid = UUID.randomUUID() + "." + suffix;
        return uuid;
    }
    
    public static String createUniqueFileName() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

	/**
	 * Function：统计文件夹文件总大小
	 * 
	 * @param dir
	 * @return unit byte
	 */
	public static double getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		double dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 如果遇到目录则通过递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * Function：小数点格式化
	 * 
	 * @param reserveDecimalPoint
	 * @return
	 */
	public static String decimalFormat(double sum) {
		double size = 0D;
		size = (sum + 0.0) / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(size);
	}

	/**
	 * Function：小数点格式化
	 * 
	 * @param sum
	 * @param reserveDecimalPoint
	 * @return
	 */
	public static String decimalFormat(double sum, String reserveDecimalPoint) {
		double size = 0D;
		size = (sum + 0.0) / (1024 * 1024);
		if ("" == reserveDecimalPoint || null == reserveDecimalPoint) {
			reserveDecimalPoint = "0.00";
		}
		DecimalFormat df = new DecimalFormat(reserveDecimalPoint);
		return df.format(size);
	}

	/**
	 * Function：统计文件夹里的文件个数
	 * 
	 * @param file
	 * @return int
	 */
	public static int getFileCount(File file) {
		file.mkdirs();
		return file.listFiles().length;
	}

	/**
	 * Function：删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹绝对路径
	 */
	public static boolean delFolder(String folderPath) {
		try {
			delFolderAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			BaseException.throwsException(FileUtil.class, e);
			return false;
		}
		return true;
	}

	/**
	 * Function：删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 * @return boolean
	 */
	public static boolean delFolderAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delFolderAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * Function：上传文件
	 * @param files 被上传的文件
	 * @param saveDir 上传的目标路径
	 * @return
	 */
	public static boolean uploadFile(File file, String savePath){
		File[] files = {file};
		return uploadFile(files, savePath, null);
	}

	/**
	 * Function：上传文件
	 * 
	 * @param files
	 *            被上传的文件一个或多个
	 * @param saveDir
	 *            上传的目标路径
	 * @return
	 */
	public static boolean uploadFile(File[] files, String savePath) {
		return uploadFile(files, savePath, null);
	}

	/**
	 * Function：上传文件
	 * 
	 * @param files
	 *            被上传的文件一个或多个
	 * @param saveDir
	 *            上传的目标路径
	 * @param saveFileName
	 *            上传后的文件名
	 * @return
	 */
	public static boolean uploadFile(File[] files, String savePath,
			String[] saveFileName) {
		try {
			if (files.length == 0) {
				return false;
			}
			new File(savePath).mkdirs();
			if (null == saveFileName) {
				saveFileName = new String[files.length];
				for (int i = 0; i < saveFileName.length; i++) {
					saveFileName[i] = files[i].getName();
				}
			}
			for (int i = 0; i < files.length; i++) {
				File saveFile = new File(savePath, saveFileName[i]);
				files[i].renameTo(saveFile);
			}
		} catch (Exception e) {
			BaseException.throwsException(FileUtil.class, e);
			return false;
		}
		return true;
	}

	/**
	 * 功能：目录复制
	 * 
	 * @param directoryPath
	 * @param saveDirectoryPath
	 * @return
	 */
	public static boolean directoryCopy(File directoryPath,
			String saveDirectoryPath) {
		try {
			new File(saveDirectoryPath).mkdir();
			File[] fs = directoryPath.listFiles();
			FileInputStream in;
			FileOutputStream out;
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isDirectory()) {
					File file = new File(saveDirectoryPath + fs[i].getName());
					file.mkdir();
					directoryCopy(fs[i], saveDirectoryPath + fs[i].getName()
							+ "\\");
				}
				in = new FileInputStream(fs[i]);
				out = new FileOutputStream(saveDirectoryPath + fs[i].getName());
				byte[] context = new byte[1024];
				int length;
				while ((length = in.read(context)) != -1) {
					out.write(context, 0, length);
					out.flush();
				}
				out.close();
				in.close();
			}
		} catch (Exception e) {
			BaseException.throwsException(FileUtil.class, e);
			return false;
		}
		return true;
	}

	/**
	 * 获取文件扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 获取不带扩展名的文件名
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	/**
	 * 复制文件夹
	 * 
	 * @param byCopyFolderPath
	 *            被复制文件夹路径
	 * @param goalPath
	 *            目标路径
	 * @throws Exception
	 */
	public static boolean copyFolder(File byCopyFolderPath, String goalPath) {
		try {
			if (!byCopyFolderPath.isDirectory()
					|| byCopyFolderPath.list().length == 0) {
				return false;
			}
			File[] files = byCopyFolderPath.listFiles();
			FileInputStream in;
			FileOutputStream out;
			if (0 == files.length) {
				return false;
			}
			File file = new File(goalPath);
			file.mkdirs();
			for (int i = 0; i < files.length; i++) {
				in = new FileInputStream(files[i]);
				out = new FileOutputStream(goalPath + files[i].getName());
				byte[] context = new byte[1024];
				int length;
				while ((length = in.read(context)) != -1) {
					out.write(context, 0, length);
					out.flush();
				}
				out.close();
				in.close();
			}
		} catch (Exception e) {
			BaseException.throwsException(FileUtil.class, e);
			return false;
		}
		return true;
	}

	/**
	 * 创建多级目录
	 * 
	 * @param aParentDir
	 *            String
	 * @param aSubDir
	 *            以 / 开头
	 * @return boolean 是否成功
	 */
	public static boolean creatDirs(String path) {
		File aFile = new File(path);
		if (!aFile.exists()) {
			return aFile.mkdirs();
		} else {
			return true;
		}
	}

	public static boolean updateFile(File file, String updateKey,
			String updateValue) {
		try {
			BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream(file));
			byte[] buff = new byte[(int) file.length()];
			bin.read(buff);
			FileOutputStream fout = new FileOutputStream(file);
			String str = new String(buff);
			String[] lines = str.split("\n");
			String[] news = new String[lines.length];
			for (int j = 0; j < lines.length; j++) {
				lines[j] = lines[j].replaceAll(updateKey, updateValue);
				news[j] = lines[j];

			}
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("c:/jdbc1.properties")));
			// oos.writeObject(news);
			// oos.flush();
			// oos.close();

			FileWriter fw = new FileWriter(file.getPath());
			for (int i = 0; i < news.length; i++) {
				fw.write(news[i]);
			}
			fw.flush();
			fw.close();

			fout.flush();
			fout.close();
			bin.close();
		} catch (FileNotFoundException ex) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
		return true;

	}

	/**
	 * 取得指定扩展名的文件列表：
	 * 
	 * @param ext
	 *            指定扩展名
	 * @return
	 */
	public static FilenameFilter getFileExtensionFilter(String ext) {
		final String _extension = ext;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean flag = name.endsWith(_extension);
				return flag;
			}
		};
	}

	/**
	 * 取得文件名满足所指定的规则表达式的文件列表
	 * 
	 * @param regex
	 * @return
	 */
	public static FilenameFilter getFileRegexFilter(String regex) {
		final String regex_ = regex;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean flag = name.matches(regex_);
				return flag;
			}
		};
	}

	/**
	 * 返回指定扩展名的文件列表，以;隔开，如.jpg;.gif
	 * 
	 * @param ext
	 *            以;隔开的文件扩展名字符串 如.jpg;.gif
	 * @return
	 */
	public static FilenameFilter getFileExtensionFilterByExpStr(String exp) {
		final String[] expArr = exp.split(";");
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean flag = false;
				for (int i = 0; i < expArr.length; i++) {
					if (name.endsWith(expArr[i]))
						flag = true;
					;
				}
				return flag;
			}
		};
	}

	public static boolean updatePropertiesFile(String propertiesFilePath,
			String key, Object value) {
		if (!StringUtils.isEmpty(key)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(key, value);
			return updatePropertiesFile(propertiesFilePath, map);
		}
		return false;
	}

	public static boolean updatePropertiesFile(String propertiesFilePath,
			Map<String, Object> map) {
		File file = new File(propertiesFilePath);
		Properties pro = new Properties();
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			if (!map.isEmpty()) {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				pro.load(bis);
				FileOutputStream fos = new FileOutputStream(file);
				Set<Map.Entry<String, Object>> set = map.entrySet();
				Iterator<Map.Entry<String, Object>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<java.lang.String, java.lang.Object> entry = (Map.Entry<java.lang.String, java.lang.Object>) it
							.next();
					pro.setProperty(entry.getKey(),
							String.valueOf(entry.getValue()));
				}
				pro.store(fos, null);
				fos.close();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 保存文件到指定destUrl目录下
	 * 
	 * @param srcFile
	 * @param destUrl
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(File srcFile, String destUrl)
			throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			if (srcFile.exists() && srcFile.isFile()) {
				in = new FileInputStream(srcFile);
				File file = new File(destUrl);
				if (file.exists())
					file.delete();
				file.createNewFile();
				out = new FileOutputStream(file);
				int c;
				byte buffer[] = new byte[1024];
				while ((c = in.read(buffer)) != -1) {
					for (int i = 0; i < c; i++)
						out.write(buffer[i]);
				}
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
		return true;
	}

	/**
	 * 文件的写入
	 * @param filePath
	 *            (文件路径)
	 * @param fileName
	 *            (文件名)
	 * @param args
	 *            []
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String[] args)
			throws IOException{
		FileWriter fw = new FileWriter(filePath + fileName);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < args.length; i++){
			out.write(args[i]);
			out.println();
			out.flush();
		}
		fw.close();
		out.close();
	}
	
	/**
	 * 文件的写入
	 * @param filePath
	 *            (文件路径)
	 * @param fileName
	 *            (文件名)
	 * @param args
	 *            []
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String[] args,boolean append)
			throws IOException{
		FileWriter fw = new FileWriter(filePath + fileName,append);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < args.length; i++){
			out.write(args[i]);
			out.println();
			out.flush();
		}
		fw.close();
		out.close();
	}

	/**
	 * 文件的写入
	 * @param filePath
	 *            (文件路径)
	 * @param fileName
	 *            (文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args)
			throws IOException{
		FileWriter fw = new FileWriter(filePath + fileName);
		fw.write(args);
		fw.close();
	}
	
	/**
	 * 文件的写入带编码
	 * @param filePath
	 *            (文件路径)
	 * @param fileName
	 *            (文件名)
	 * @param args
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args,String charSet)
			throws IOException{
		FileOutputStream fos = null;  
        OutputStreamWriter writer= null;  
        try {  
            fos = new FileOutputStream(new File(filePath+fileName)) ;             
            writer = new OutputStreamWriter(fos,charSet);   
            writer.write(args);  
        }catch (IOException ex) {    
            ex.printStackTrace();  
        }finally{  
            if(writer != null){  
                try {  
                    writer.close();  
                } catch (IOException e) {  
                    BaseException.throwsException(FileUtil.class, e);  
                }  
            }  
            if(fos != null){  
                try {  
                    fos.close();  
                } catch (IOException e) {  
                    BaseException.throwsException(FileUtil.class, e);  
                }  
            } 
        }
	}

	/**
	 * 创建与删除文件
	 * @param filePath
	 * @param fileName
	 * @param isDelete
	 * @param isCreate
	 * @return创建成功返回true
	 * @throws IOException
	 */
	public static boolean createAndDeleteFile(String filePath, String fileName,boolean isDelete,boolean isCreate)
			throws IOException{
		boolean result = false;
		File file = new File(filePath, fileName);
		if (file.exists()&&isDelete){
			file.delete();
			result = true;
		}else if(isCreate){
			file.createNewFile();
			result = true;
		}
		return result;
	}

	/**
	 * 创建和删除目录
	 * @param folderName
	 * @param filePath
	 * @param isDelete
	 * @param isCreate
	 * @return删除成功返回true
	 */
	public static boolean createAndDeleteFolder(String folderName, String filePath,boolean isDelete,boolean isCreate){
		boolean result = false;
		try{
			File file = new File(filePath + folderName);
			if (file.exists()&&isDelete){
				file.delete();
				result = true;
			}else if(isCreate){
				file.mkdir();
				result = true;
			}
		}catch (Exception ex){
			result = false;
		}
		return result;
	}

	/**
	 * 检查文件中是否为一个空
	 * @param filePath
	 * @param fileName
	 * @return 为空返回true
	 * @throws IOException
	 */
	public static boolean fileIsNull(String filePath, String fileName)
			throws IOException{
		boolean result = false;
		FileReader fr = new FileReader(filePath + fileName);
		if (fr.read() == -1){
			result = true;
		}
		fr.close();
		return result;
	}

	/**
	 * 一行一行的读取文件中的数据
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static String readLineFile(String filePath, String fileName,String charSet)
			throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath + fileName),charSet));  
		String line = br.readLine();
		StringBuffer str= new StringBuffer();
		while (line != null){
			str.append(line+"\n");
			line = br.readLine();
		}
		br.close();
		return str.toString();
	}
	
	
	public static InputStream getIsByUrl(String urlPath)throws Exception{
		URL url = new URL(urlPath);
		return url.openConnection().getInputStream();
	}

	public static boolean delFileList(List<String> delFileList)throws Exception{
		for (String path : delFileList) {
			File file = new File(path);
			if(file.exists())
				file.delete();
		}
		return true;
	}
	
	public static void main(String[] args) {
		// System.out.println(getFileCount(new File("E:\\size")));
		// System.out.println(decimalFormat(getDirSize(new File("E:\\size"))));
		// System.out.println(decimalFormat(getDirSize(new File("E:\\size")),
		// "0.000"));
		// System.out.println(delFolderAllFile("E:\\size"));

		// File[] files = {
		// new File("E:\\size\\1440Rabbit_1003.jpg"),
		// new File("E:\\size\\1440Rabbit_1004.jpg"),
		// new File("E:\\size\\1440Rabbit_1005.jpg")
		// };
		// System.out.println(uploadFile(files, "c:\\", null));
		// P.pring(copyFolder(new File("G:\\JuFeng\\csv\\莱客商城\\新品上架\\41-94"),
		// "f:\\1\\"));
		// P.pring(new File("F:\\360Downloads").isDirectory());
		// P.pring(new File("F:\\360Downloads").list().length);

		// updateFile(new File("c:/jdbc.properties"), "create", "update");

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("siteName", "中国物流交易平台siteName");
		// map.put("siteTitle", "中国物流交易平台siteTitle");
		// map.put("siteDescription",
		// "中国物流交易平台 全国诚信调车、安全快捷、更实惠 http://zc.0256.cnsiteDescription");
		// map.put("icpNumber", "粤ICP备10216753号icpNumber");
		// updatePropertiesFile("D:\\common_context.properties", map);

		updatePropertiesFile("D:\\common_context.properties", "siteName",
				"中国物流交易平台8888siteName");
	}

}
