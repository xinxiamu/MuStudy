package five;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPDF {
	public static void main(String[] args) {
		try {
			Document document = new Document(PageSize.LETTER);
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream("G:/testpdf.pdf"));  
			document.open();
			document.addAuthor("ysjiang");
			document.addCreator("ysjiang");
			document.addSubject("test");
			document.addCreationDate();
			document.addTitle("XHTML to PDF");

			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

			String str = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
					+ "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
					+ "<title>HTML 2 PDF</title>"
					+ "<style type=\"text/css\">"
					+ "<!--"
					+ "body {"
					+ "	margin: 20px;"
					+ "}"
					+ "-->"
					+ "</style>"
					+ "</head>"
					+ "<body>"
					+ "<div style=\"width:90%; height:160px;\">多情浪漫的人，其实内心的情感是非常脆弱的，感情的末梢，有那么一点儿敏感，还有那么一点儿想入非非。所以和浪漫多情的人在一起，言语一定要斯文，说话不能像火炮，态度一定要温柔、语气也要婉转。遇到对方的一个眼神，也许有些人并没发现什么端倪，但是放在懂得浪漫的人身上，就会体会出万种滋味，百般柔情来。"
					+ "</div>"
					+ "<hr/>"
					+ "<table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%; border:1px;\">"
					+ "  <tr>"
					+ "  <td style=\"width:30%\">table中的中文显示及换行"
					+ "  </td>"
					+ "  <td>多情浪漫的人，其实内心的情感是非常脆弱的，感情的末梢，有那么一点儿敏感，还有那么一点儿想入非非。所以和浪漫多情的人在一起，言语一定要斯文，说话不能像火炮，态度一定要温柔、语气也要婉转。遇到对方的一个眼神，也许有些人并没发现什么端倪，但是放在懂得浪漫的人身上，就会体会出万种滋味，百般柔情来。"
					+ "  </td>"
					+ "  </tr>"
					+ "  <tr>"
					+ "  <td colspan=\"2\">"
					+ "  	<img src=\"0.jpg\" />"
					+ "  </td>"
					+ "  </tr>"
					+ "</table>" + "</body>" + "</html>";
			// worker.parseXHtml(pdfWriter, document, new FileInputStream("C:\\tmp\\a.htm"), null, new AsianFontProvider());
			worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(str.getBytes()), null, new AsianFontProvider());
			document.close();
			System.out.println("Done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
