package com.demo.test.main;

public class TestMain {
    public static void main(String[] args) {
        int parll = 4;
        int s = 0;
        for(int i=1;i<=parll;i++) {
            for(int j=1;j<=100;j++) {
                int k = parll%j;
                System.out.println(s++);
                if(k==i) {

                }
            }
        }
    }
}
