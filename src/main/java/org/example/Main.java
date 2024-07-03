package org.example;

public class Main {
    public static void main(String[] args) {
        String str="startTest pNumber=11";
        String numberStr = str.substring(str.lastIndexOf("="));
        Log.e("main","number="+numberStr);

    }
}