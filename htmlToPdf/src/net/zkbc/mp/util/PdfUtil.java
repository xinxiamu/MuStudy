package net.zkbc.mp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

/**
 * 对html的规范要求极高,例如：页面中<mate></mate>必须闭合，必须: <br />
 * 
 * <pre>
 * <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 * "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> <html
 * xmlns="http://www.w3.org/1999/xhtml">
 * 
 * @author zsj
 *
 */
public class PdfUtil {

	public static void main(String[] args) throws Exception {
		// String filePath = PdfUtil.class.getClassLoader().getResource("")
		// .toString()
		// + "simsun.ttc";
		// System.out.println(filePath.replaceAll("file:/", ""));
		// System.out.println(new File(filePath));
		htmlToPdf("/data/fs/sinspector/BJ20150522001.pdf",
				"http://localhost:9080/account/observerInforeport/BJ20150522001");
	}

	/**
	 * 把URL转换为PDF
	 * 
	 * @param outputFile
	 *            ， 示例：/data/fs/inspector/BJ20150522001.pdf
	 * @param url
	 *            ，示例：http :xxxx
	 * @return
	 * @throws Exception
	 */
	public static boolean htmlToPdf(String outputFile, String url)
			throws Exception {
		File outFile = new File(outputFile);
		if (!outFile.exists()) {
			outFile.getParentFile().mkdirs();
		}
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();

		renderer.setDocument(url);
		// String fontPath = PdfUtil.class.getClassLoader().getResource("/")
		// .getPath();
		String fontPath = PdfUtil.class.getClassLoader().getResource("")
				.toString().replaceAll("file:/", "")
				+ "simsun.ttc";
		System.out.println(fontPath);
		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return true;
	}

	public static void htmlToPdf(OutputStream os, String url)
			throws DocumentException, IOException {
		ITextRenderer renderer = new ITextRenderer();

		renderer.setDocument(url);
		String fontPath = PdfUtil.class.getClassLoader()
				.getResource("/simsun.ttc").getPath();
		System.out.println(fontPath);
		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
	}
}
