package com.j8.number;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberUtilsTest {
	
	@Test
	public void customFormatTest() {
		String n1 = NumberUtils.customFormat("###,###.###", 123456.789);
		String n2 = NumberUtils.customFormat("###.##", 123456.789);	//四舍五入,保留两位小数
		String n3 = NumberUtils.customFormat("000000.000", 123.78);
		String n4 = NumberUtils.customFormat("$###,###.###", 123456.789);
		assertEquals(n1,"123,456.789");
		assertEquals(n2,"123456.79");
		assertEquals(n3,"000123.780");
		assertEquals(n4,"$123,456.789");
		
		System.out.println("---::" + NumberUtils.customFormat("###.##", 903.01)); 
	}
	
	@Test
	public void test() {
		BigDecimal bigDecimal = new BigDecimal(490);
		System.out.println(String.valueOf(bigDecimal.doubleValue())); 
	}

}
