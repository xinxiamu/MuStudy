package com.mu.utils.date.json;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mu.utils.PrintUtils;
import com.mu.utils.json.GsonUtils;

public class GsonUtilsTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Test
	public void toJson() {
		PrintUtils
				.println("**************************************bean对象转化成json字符串 ");
		User jack = new User("张三", "123456", "已婚", "男");
		User marry = new User("李四", "888888", "未婚", "女");

		PrintUtils
				.println("---------------------------------------------jsonArray");
		List userList = new LinkedList();
		userList.add(jack);
		userList.add(marry);
		Type targetType = new TypeToken<List<User>>() {
		}.getType();
		String sUserList1 = GsonUtils.toJson(userList, targetType);
		String sUserList2 = GsonUtils.toJson(userList, targetType, false);
		String sUserList3 = GsonUtils.toJson(userList, targetType, 1.1d, true);
		PrintUtils.println(sUserList1);
		PrintUtils.println(sUserList2);
		PrintUtils.println(sUserList3);

		PrintUtils
				.println("---------------------------------------------json对象");
		Type targetType1 = new TypeToken<User>() {
		}.getType();
		String userJsonStr1 = GsonUtils.toJson(jack, targetType1);
		String userJsonStr11 = GsonUtils.toJson(jack);
		String userJsonStr2 = GsonUtils.toJson(jack, true);
		String userJsonStr3 = GsonUtils.toJson(jack, 1.1d, false);
		jack.setBornDate(new Date());
		String userJsonStr12 = GsonUtils.toJson(jack, "yyyy-MM-dd");
		PrintUtils.println(userJsonStr1);
		PrintUtils.println(userJsonStr11);
		PrintUtils.println(userJsonStr12);
		PrintUtils.println(userJsonStr2);
		PrintUtils.println(userJsonStr3);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void formJson() {
		PrintUtils
				.println("******************************** json字符串转对象 ******************");
		User jack = new User("张三", "123456", "已婚", "男");
		User marry = new User("李四", "888888", "未婚", "女");
		List userList = new LinkedList();
		userList.add(jack);
		userList.add(marry);
		jack.setBornDate(new Date());
		String jsonArrayStr = GsonUtils.toJson(userList);
		String jsonObjectStr = GsonUtils.toJson(jack);
		PrintUtils.println(jsonArrayStr);
		PrintUtils.println(jsonObjectStr);
		
		User user = GsonUtils.fromJson(jsonObjectStr,User.class);
		PrintUtils.println(user.toString());
		List<User> list = GsonUtils.fromJson(jsonArrayStr, List.class);
		PrintUtils.println(list.toString());
	}
}
