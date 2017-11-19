package com.j8.object;

import java.io.Serializable;

public class MyJavaClass implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    public MyJavaClass() {
        System.out.println("Constructor for MyJavaClass ...");
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            return null;
        }
    }
}