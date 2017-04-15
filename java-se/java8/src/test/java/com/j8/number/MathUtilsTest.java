package com.j8.number;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {
	
	@Test
	public void absTest() {
		 double d = MathUtils.abs(-3.1d, Double.class);
		 assertEquals(3.1d,d, -3.1d);
		 assertEquals(3, MathUtils.abs(-3, Integer.class).intValue(),1);
		 
	}

}
