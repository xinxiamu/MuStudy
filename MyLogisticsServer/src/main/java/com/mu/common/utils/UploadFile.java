package com.mu.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mu.common.app.SystemInfo;

/**
 * 
 * @类描述：http协议下上传文件
 * 
 */
public class UploadFile {

	public interface UploadFileCallBack {

		/**
		 * 返回简单类型参数。
		 * 
		 * @param parsMap
		 *            对应request的key-value值
		 */
		public void parameters(Map<String, String> parsMap);

		/**
		 * 多文件上传返回文件保存的全路径
		 * 
		 * @param filsURL
		 */
		public void filesURL(Map<String, String> filsURL);
	}

	/**
	 * 保存客户端传上来的图片。 可以多文件上传。文件保存路径：项目下的/resources/uploadFiles
	 * @param request	先传文件要保存文件夹，key:folder,再传文件类型。
	 * @param callBack
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void saveFileFromClient(HttpServletRequest request, UploadFileCallBack callBack) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items = upload.parseRequest(request);

			Map<String, String> parMap = new HashMap<String, String>();
			Map<String, String> filesUrlMap = new HashMap<String, String>();

			String dir = request.getSession().getServletContext()
					.getRealPath("/resources/uploadFiles");
			
			for (FileItem item : items) {
				if (item.isFormField()) {// 如果文本类型参数
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);

					parMap.put(name, value);
				} else {// 如果是文件类型参数
					String a = MapUtils.getStr(parMap, "folder", "");
					dir = a.equals("") ? dir : dir + File.separator + a;
					File dirFile = new File(dir);
					if (!dirFile.exists())
						dirFile.mkdirs();
					
					
					System.out.println(dir);
					File saveFile = new File(dirFile, item.getName());
					item.write(saveFile);

					//他妹子的，这里的路径好像是硬编码了，写到配置文件或者更好的方法么？
					String url_root = SystemInfo.getBasePath(request)
							+ "resources/uploadFiles/";
					filesUrlMap.put(
							FileUtil.getFileNameNoEx(item.getName()),
							a.equals("") ? url_root +  item.getName() : url_root + a + "/" + item.getName());
				}
			}

			// 回调
			callBack.parameters(parMap);
			callBack.filesURL(filesUrlMap);

		}
	}

}
