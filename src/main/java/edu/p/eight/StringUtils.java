package edu.p.eight;

public class StringUtils {
    static String add(String a, String b) {
        return a + b;
    }

    static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
