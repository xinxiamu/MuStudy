package com.j8.string;

public class JavaStringIntConverter {

    public static void main(String[] args) {
        //===============================
        // I. Convert String to Int & Integer
        //===============================
        System.out.println("# I. Convert String to Int & Integer");

        // 1. Convert String to Int
        System.out.println("# -> 1. Convert String to Int - use Integer.parseInt");
        String s = "1";
        int intValue = Integer.parseInt(s);
        System.out.println(intValue);

        // 2. Convert String to Integer
        System.out.println("# -> 2. Convert String to Integer - use Integer.valueOf");
        Integer integerValue = Integer.valueOf(s);
        System.out.println(integerValue);

        // 3. NumberFormatException Exception
        s = "test";

        // 3.1 With Integer.parseInt
        try{
            int i = Integer.parseInt(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        // 3.2 With Integer.valueOf
        try{
            Integer integer = Integer.valueOf(s);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        //===============================
        // II. Convert Int & Integer to String
        //===============================
        System.out.println("# II. Convert Int & Integer to String");
        // 1. Convert Int to String
        System.out.println("# -> 1. Convert Int to String");
        // Method 1
        System.out.println("# -> Method 1 - use String.valueOf");
        int i = 1;
        s = String.valueOf(i);
        System.out.println(s);

        // Method 2
        System.out.println(" -> Method 2 - use Integer.toString");
        s = Integer.toString(i);
        System.out.println(s);

        // Method 3
        System.out.println(" -> Method 3 ");
        s = "" + i;
        System.out.println(s);

        // 2. Convert Integer to String
        System.out.println("# -> 2. Convert Integer to String");
        // Method 1
        System.out.println("# -> Method 1 - use String.valueOf");
        Integer integer = new Integer(1);
        s = String.valueOf(integer);
        System.out.println(s);

        // Method 2
        System.out.println("# -> Method 2 - use integer.toString");
        s = integer.toString();
        System.out.println(s);

        // Method 3;
        System.out.println("# -> Method 3");
        s = "" + integer;
        System.out.println(s);
    }
}
