package com.j8.number;

/**
 * 数学处理工具类。
 * @author mutou
 * url:http://docs.oracle.com/javase/tutorial/java/data/beyondmath.html
 */
public class MathUtils {
	
	private MathUtils(){}
	
	public static void main(String[] args) {
		double a = -191.635;
        double b = 43.76;
        int c = 16, d = 45;
        float f = 33.45f;

        //去绝对值
        System.out.printf("The absolute value " + "of %.3f is %.3f%n", 
                          a, Math.abs(a));

        //向正无穷取整
        System.out.printf("The ceiling of " + "%.2f is %.0f%n", 
                          b, Math.ceil(b));

        //取不大于的最大整数
        System.out.printf("The floor of " + "%.2f is %.0f%n", 
                          b, Math.floor(b));
        
        //返回其值最接近参数并且是整数的 double 值。如果两个整数的 double 值都同样接近，那么结果取偶数
        System.out.printf("The rint of %.2f " + "is %.0f%n", 
                          b, Math.rint(a));
        System.out.println("----" + Math.rint(a));
        
        //取最大值
        System.out.printf("The max of %d and " + "%d is %d%n",
                          c, d, Math.max(c, d));
        
        //取最小值
        System.out.printf("The min of of %d " + "and %d is %d%n",
                          c, d, Math.min(c, d));
        
        System.out.println("-----四舍五入：" + Math.round(a));
        System.out.println("-----四舍五入：" + Math.round(f));
	}
	
	/**
	 * 取绝对值.
	 * @param o
	 * @param class1	值类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T abs(Object o,Class<T> class1) {
		if (class1 == Double.class) {
			return (T)Double.valueOf(Math.abs((Double)o));
		} else if (class1 == Integer.class) {
			return (T)Integer.valueOf(Math.abs((Integer)o));
		} else if (class1 == Float.class) {
			return (T)Float.valueOf(Math.abs((Float)o));
		} else if (class1 == Long.class) {
			return (T)Long.valueOf(Math.abs((Long)o));
		}
		return null;
	}

}
