package co.jufeng;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class GetMethod {
   public static void main(String[] args) {
       getMethodInfo("co.jufeng.api.user.UserService");
   }

   /**
    * 传入全类名获得对应类中所有方法名和参数名
    */
   @SuppressWarnings("rawtypes")
   private static void getMethodInfo(String pkgName) {
       try {
           Class clazz = Class.forName(pkgName);
//           Method[] methods = clazz.getMethods();
//           for (Method method : methods) {
//               String methodName = method.getName();
//               System.out.println("方法名称:" + methodName);
//               Class<?>[] parameterTypes = method.getParameterTypes();
//               Parameter[] parameters = method.getParameters();
//               for (int i = 0; i < parameters.length; i++) {
//				System.out.println(parameters[i].getName());
//			}
//               for (Class<?> clas : parameterTypes) {
////                   String parameterName = clas.getName();
////                   System.out.println("参数名称:" + parameterName);
//                   System.out.println("参数名称:" + clas.getSimpleName());
//               }
//               System.out.println("*****************************");
//           }
           
           TypeVariable[] v = clazz.getTypeParameters();  
           System.out.println(v.length);
           for (TypeVariable vv : v) {  
               System.out.println(vv.getName());  
               Type[] tt = vv.getBounds();  
               for (Type c : tt) {  
                   System.out.println(((Class) c).getName());  
               }  
           }  
           
           Method getPayments = null;  
           try {  
               getPayments = GetMethod.class.getMethod("getPayments", new Class[]{String[].class,Integer[].class, List.class});  
           } catch (NoSuchMethodException e) {  
               e.printStackTrace();  
           }  
           Type[] types = getPayments.getGenericParameterTypes();  
           System.out.println("The first parameter of this method is GenericArrayType."+ types[0].getClass());  
           ParameterizedType gType = (ParameterizedType)types[2];  
           System.out.println("The GenericArrayType's component is String." +((Class)gType.getActualTypeArguments()[0]).getName());  
     
           
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
		}
	}
//
//	protected static String[] getParamName(Class<?> clazz, String methodName) {
//		String[] paramNames = null;
//		try {
//			CtMethod cm = initClassPool(clazz, methodName);
//			LocalVariableAttribute attr = getMethodInfo(cm);
//			if (attr == null) {
//			}
//			paramNames = new String[cm.getParameterTypes().length];
//			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
//			for (int i = 0; i < paramNames.length; i++) {
//				paramNames[i] = attr.variableName(i + pos);
//			}
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//		return paramNames;
//	}
//
//	protected staticCtMethod initClassPool(Class<?> clazz, String methodName)
//			throws NotFoundException {
//		ClassPool pool = ClassPool.getDefault();
//		pool.insertClassPath(new ClassClassPath(clazz));
//		CtClass cc = pool.get(clazz.getName());
//		CtMethod cm = cc.getDeclaredMethod(methodName);
//		return cm;
//	}
//
//	protected LocalVariableAttribute getMethodInfo(CtMethod cm) {
//		MethodInfo methodInfo = cm.getMethodInfo();
//		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
//		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
//				.getAttribute(LocalVariableAttribute.tag);
//		return attr;
//	}
}