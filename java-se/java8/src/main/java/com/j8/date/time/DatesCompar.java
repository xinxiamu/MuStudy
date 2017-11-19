package com.j8.date.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 时间比较。
 */
public class DatesCompar {

    public static void main(String[] args) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("=== Date Info (day-month-year) ===");
        LocalDate date1 = LocalDate.of(2017, 11, 01);
        LocalDate date2 = LocalDate.of(2018, 01, 1);

        System.out.println("date1 : " + dateFormat.format(date1));
        System.out.println("date2 : " + dateFormat.format(date2));

        System.out.println("=== Is Checking ===");
        System.out.println(date1 + " is before " + date2 + " --> " + date1.isBefore(date2));
        System.out.println(date1 + " is after " + date2 + " --> " + date1.isAfter(date2));
        System.out.println(date1 + " is equal " + date2 + " --> " + date1.isEqual(date2));

        System.out.println("=== Compare ===");
        System.out.println("date1.compareTo(date2) -> " + date1.compareTo(date2));
        System.out.println("date2.compareTo(date1) -> " + date2.compareTo(date1));

    }
}
