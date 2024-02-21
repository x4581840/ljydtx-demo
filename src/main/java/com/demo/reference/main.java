package com.demo.reference;

public class main {
    public static void main(String[] args) {

        testStrongReference();
    }

    // 强引用  -Xms2m -Xmx3m -Xmn2m
    private static void testStrongReference() {
        // 当 new byte为 1M 时，程序运行正常
//        byte[] buff = new byte[1024 * 1024 * 1];

        // 当 new byte为 2M 时，程序运行报错 ，内存不够使用，程序直接报错，强引用并不会被回收
        byte[] buff = new byte[1024 * 1024 * 2];
    }

    class T {
        private int a;
        private int b;
        private int c;
        private int d;
    }
}
