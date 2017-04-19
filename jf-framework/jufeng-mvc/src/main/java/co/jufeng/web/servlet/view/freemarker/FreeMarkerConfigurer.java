package co.jufeng.web.servlet.view.freemarker;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import freemarker.ext.servlet.FreemarkerServlet;

@WebServlet(name = "freemarker", urlPatterns = "*.html", initParams = {
		@WebInitParam(name = "TemplatePath", value = "/"),
		@WebInitParam(name = "NoCache", value = "true"),
		@WebInitParam(name = "default_encoding", value = "UTF-8"),
		@WebInitParam(name = "ContentType", value = "text/html;charset=UTF-8"),
		@WebInitParam(name = "url_escaping_charset", value = "UTF-8"),
		@WebInitParam(name = "template_update_delay", value = "0"),
		@WebInitParam(name = "template_exception_handler", value = "rethrow"),
		@WebInitParam(name = "tag_syntax", value = "auto_detect"),
		@WebInitParam(name = "localized_lookup", value = "false"),
		@WebInitParam(name = "locale", value = "zh_CN"),
		@WebInitParam(name = "boolean_format", value = "true,false"),
		@WebInitParam(name = "datetime_format", value = "yyyy-MM-dd HH:mm:ss"),
		@WebInitParam(name = "date_format", value = "yyyy-MM-dd"),
		@WebInitParam(name = "time_format", value = "HH:mm:ss"),
		@WebInitParam(name = "whitespace_stripping", value = "true"),
		@WebInitParam(name = "number_format", value = "0.##") })
public class FreeMarkerConfigurer extends FreemarkerServlet {

	private static final long serialVersionUID = 6096086111350411514L;

}
