package com.demo.wocao;

/**
 * @Description
 * @Author longjianyong
 * @Date 2020/5/9 4:53 PM
 * @Version 1.0
 **/
public class Main {
    private String name;

    public static void main(String[] args) {
        Main main = new Main();
        main.setName("longjianyong");
        System.out.println(main.getName());
        String str  = "MDove";
        System.out.println("str hashcode " + str.hashCode());
        int count = 66;
        main.fun(str);
        main.fun(count);
        main.setName(main);
        System.out.println(str);
        System.out.println(count);
        System.out.println(main.getName());

        String str1 = "long";
        String str2 = "long";
        //String str2 = str1;
        System.out.println(str1.getBytes());
        System.out.println(str2.getBytes());
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }

    private void fun(int count) {
        count = 666;
    }

    private void fun(String str) {
        str = "MDove is cool.";
        System.out.println("str hashcode " + str.hashCode());
    }

    public void setName(Main main) {
        main.setName("lucky");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
