package co.jufeng;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import co.jufeng.json.JSONArray;
import co.jufeng.json.JSONObject;

public class S {

		// TODO Auto-generated method stub
	public static void main(String[] args) throws Exception {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("citys.txt");
			String jsonString = IOUtils.toString(is);
			JSONArray jsonArray = JSONObject.parseArray(jsonString);
			for (int i = 0; i < 30; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int code = jsonObject.getInteger("code");
					System.out.println(jsonObject);
					System.out.println();
			}
	}

}
