package com.j8.tmp;

public class Test {

	public static void main(String[] args) {
		// User u1 = new User();
		// User u2 = new User();
		//
		// u1.setName("李");
		// u1.setAge(13);
		// u1.setSex("男");
		//
		//// u2.setAge(99);
		//// u2.setName("张");
		//
		// System.out.println("--------u1" + u1.getSex()) ;
		// System.out.println("-------u2" + u2.getSex());
		// u2.setSex("dddd");
		// System.out.println("--------u1" + u1.getSex()) ;
		// System.out.println("-------u2" + u2.getSex());

//		for (float z = 1.5f; z > -1.5f; z -= 0.05f) {
//			for (float x = -1.5f; x < 1.5f; x += 0.025f) {
//				float v = f(x, 0.0f, z);
//				if (v <= 0.0f) {
//					float y0 = h(x, z);
//					float ny = 0.01f;
//					float nx = h(x + ny, z) - y0;
//					float nz = h(x, z + ny) - y0;
//					float nd = (float) (1.0f / Math.sqrt(nx * nx + ny * ny + nz * nz));
//					float d = (nx + ny - nz) * nd * 0.5f + 0.5f;
//					System.out.println(".:-=+*#%@" + "[" + (int) (d * 5.0f) + "]");
//				} else
//					System.out.println(' ');
//			}
//			System.out.println('\n');
//		}
		
		for (float y = 1.5f; y > -1.5f; y -= 0.1f) {  
	        for (float x = -1.5f; x < 1.5f; x += 0.05f) {  
	            float a = x * x + y * y - 1;  
	            System.out.println(a * a * a - x * x * y * y * y <= 0.0f ? '*' : ' ');  
	        }  
	        System.out.println('\n');  
	    }  

	}

	private static float f(float x, float y, float z) {
		float a = x * x + 9.0f / 4.0f * y * y + z * z - 1;
		return a * a * a - x * x * z * z * z - 9.0f / 80.0f * y * y * z * z * z;
	}

	private static float h(float x, float z) {
		for (float y = 1.0f; y >= 0.0f; y -= 0.001f)
			if (f(x, y, z) <= 0.0f)
				return y;
		return 0.0f;
	}

}
