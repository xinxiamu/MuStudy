package com.j8.object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * java创建对象方法。五种方式。
 */
public class ObjectCreate {
    public static void main(String[] args) throws Exception {
        // 用new关键字
        MyJavaClass object1 = new MyJavaClass();
        System.out.println("object1:" + object1);

        // 用 Class.forName()
        MyJavaClass object2 = (MyJavaClass) Class.forName("com.j8.object.MyJavaClass")
                .newInstance();
        System.out.println("object2:" + object2);

        // Using newInstance() method of Constructor class
        Constructor<MyJavaClass> constructor = MyJavaClass.class.getConstructor();
        MyJavaClass object3 = constructor.newInstance();
        System.out.println("object3:" + object3);

        // 用 clone() method
        MyJavaClass object4 = (MyJavaClass) object3.clone();
        System.out.println("object4:" + object4);

        //对象系列化，反系列化
        // Using Object Deserialization
        // Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("mydata.o"));
        out.writeObject(object4);
        out.close();
        // Deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("mydata.o"));
        MyJavaClass object5 = (MyJavaClass) in.readObject();
        in.close();

        System.out.println("object5:" + object5);
    }
}
