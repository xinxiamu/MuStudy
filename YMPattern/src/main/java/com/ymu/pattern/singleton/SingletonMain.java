package com.ymu.pattern.singleton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingletonMain {
	@Test
	public void test() {
		SingletonClass s1 = SingletonClass.getInstance();
		SingletonClass s2 = SingletonClass.getInstance();
		assertEquals(s1, s2);
	}
}
