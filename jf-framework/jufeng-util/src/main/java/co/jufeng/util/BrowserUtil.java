package co.jufeng.util;

import java.lang.reflect.Method;

public class BrowserUtil {
	
	
	protected Process open(String url) throws Exception{
		Process process = Runtime.getRuntime().exec("cmd /c start " + url); 
		process .waitFor();
		return process;
	}
	
	protected Process opens(String url) throws Exception {
		String osName = System.getProperty("os.name");
		Process p = null;
		int exitCode = 1;
		if (osName.startsWith("Mac")) {
			Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
			Method openURL = fileMgr.getDeclaredMethod("openURL",
					new Class[] { String.class });
			openURL.invoke(null, new Object[] { url });
		} else if (osName.startsWith("Windows")) {
			p = Runtime.getRuntime().exec("cmd /c start " + url); 
			exitCode = p.waitFor();
		} else {
			String[] browsers = { "firefox", "opera", "konqueror",
					"epiphany", "mozilla", "netscape" };
			String browser = null;
			for (int count = 0; count < browsers.length && browser == null; count++) {
				if (Runtime.getRuntime()
						.exec(new String[] { "which", browsers[count] })
						.waitFor() == 0) {
					browser = browsers[count];
				}
			}
			if (browser == null) {
				throw new Exception("Could not find web browser" + exitCode);
			} else {
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		}
		return p;
	}


}
