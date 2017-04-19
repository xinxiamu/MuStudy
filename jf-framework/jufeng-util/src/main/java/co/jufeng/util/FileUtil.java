package co.jufeng.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileUtil {

	public static final void writeClassFile(String saveFilePath,
			String className, String content) {
		writeFile(saveFilePath, className + ".java", content);
	}

	public static final void writeFile(String path, String name, String content) {
		File file = new File(path + "/" + name);
		if (file.isFile()) {
			file.delete();
		}
		try {
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			OutputStreamWriter osWriter = new OutputStreamWriter(os);
			BufferedWriter buffWriter = new BufferedWriter(osWriter);
			buffWriter.write(content);
			buffWriter.flush();
			buffWriter.close();
			osWriter.close();
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(
					"Create failed, check that the disk is not written permission or path is wrong."
							+ e.getMessage(), e);
		}
	}

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

	public static String decimalFormat(double sum) {
		double size = 0D;
		size = (sum + 0.0) / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(size);
	}

	public static String decimalFormat(double sum, String reserveDecimalPoint) {
		double size = 0D;
		size = (sum + 0.0) / (1024 * 1024);
		if ("" == reserveDecimalPoint || null == reserveDecimalPoint) {
			reserveDecimalPoint = "0.00";
		}
		DecimalFormat df = new DecimalFormat(reserveDecimalPoint);
		return df.format(size);
	}

	public static int getFileCount(File file) {
		file.mkdirs();
		return file.listFiles().length;
	}

	public static boolean delFolder(String folderPath) {
		try {
			delFolderAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

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

	public static boolean uploadFile(File[] files, String savePath) {
		return uploadFile(files, savePath, null);
	}

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
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean mkdir(String path) {
		return new File(path).mkdirs();
	}

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
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	public static String getFileNameNoEx(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length()))) {
				return fileName.substring(0, dot);
			}
		}
		return fileName;
	}

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
			e.printStackTrace();
			return false;
		}
		return true;
	}

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

	public static FilenameFilter getFileExtensionFilter(String ext) {
		final String _extension = ext;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean flag = name.endsWith(_extension);
				return flag;
			}
		};
	}

	public static FilenameFilter getFileRegexFilter(String regex) {
		final String regex_ = regex;
		return new FilenameFilter() {
			public boolean accept(File file, String name) {
				boolean flag = name.matches(regex_);
				return flag;
			}
		};
	}

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
		if (key == null || key.equals("")) {
			return false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return updatePropertiesFile(propertiesFilePath, map);
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

	public static String getFileNameFromUrl(String url) {
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}
		return name;
	}

	public static String inputStreamToStrin(InputStream is) {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static InputStream stringToInputStream(String str) {
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
		return stream;
	}

	public static InputStream fileToInputStream(File file) {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

	public static File inputstreamTofile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}