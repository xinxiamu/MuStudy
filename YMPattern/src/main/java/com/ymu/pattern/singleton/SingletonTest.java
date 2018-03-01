package com.ymu.pattern.singleton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingletonTest {

	@Test
	public void test() {
		SingletonClass s1 = SingletonClass.getInstance();
		SingletonClass s2 = SingletonClass.getInstance();
		assertEquals(s1, s2);
	}

	@Test
	public void testE1() {
		SingletonClassE1 e1 = SingletonClassE1.getInstance();
		SingletonClassE1 e11 = SingletonClassE1.getInstance();
		System.out.println("e1=" + e1);
		System.out.println("e11=" + e11);
		assertEquals(e1,e11);
	}
}
