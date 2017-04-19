package co.jufeng.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.jufeng.util.date.DateStyle;
import co.jufeng.util.date.DateUtil;
import co.jufeng.util.http.HttpUtil;


public class ApiActionTest {
	
	public static void main(String[] args) {
		String url = "http://www.jufeng.co:8081/jufeng-web/api.do?action=userService.getById&jsonString=eyJpZCI6MX0=";
//		String url = "http://www.baidu.cox";
//		String result = HttpUtil.doGet(url);
//		System.out.println(result);
		try {
			/*
			* Java代码计算时间差
			* 现在是：2004-03-26 13：31：40
			* 过去是：2004-01-02 11：30：24
			*/
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			java.util.Date now = df.parse("2004-03-26 13:31:40");
//			java.util.Date date=df.parse("2004-01-02 11:30:24");
//			java.util.Date date= df.parse(DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
			java.util.Date date= new Date();
			
			for (int i = 0; i < 1000000; i++) {
				String result = HttpUtil.doPost(url);
				System.out.println(result);
				System.out.println(i + 1);
				Thread.sleep(5000);
			}
//			java.util.Date now = df.parse(DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
			java.util.Date now = new Date();
			long l=now.getTime()-date.getTime();
			long day=l/(24*60*60*1000);
			long hour=(l/(60*60*1000)-day*24);
			long min=((l/(60*1000))-day*24*60-hour*60);
			long s=(l/1000-day*24*60*60-hour*60*60-min*60);
			System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
			//现在要获得两个日期差，差的形式为：XX天XX小时XX分XX秒
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}

}
