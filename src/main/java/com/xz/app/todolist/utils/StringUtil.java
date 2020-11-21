package com.xz.app.todolist.utils;

/**
 * @Author: xz
 * @Date: 2020/11/22
 */
public class StringUtil {
    // StringBuffer
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
