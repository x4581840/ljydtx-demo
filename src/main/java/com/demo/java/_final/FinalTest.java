package com.demo.java._final;

class Value {
    int v;

    public Value(int v) {
        this.v = v;
    }
}

public class FinalTest {

    final int f1 = 1;
    final int f2;

    public FinalTest() {
        f2 = 2;
    }

    public static void main(String[] args) {
        final_test_1();

    }

    //修饰类，表示类无法被继承
    final class FinalClass {

    }

    //修饰方法,表示方法不能覆盖
    public static final void final_test_3() {

    }

    //修饰方法参数
    private static void final_test_2(final String name, final Value value) {
        System.out.println(name);
        //如果对name进行操作，也会报错
//        name = "2";
        //不能改变value的值
        //value = new Value(2);
        value.v = 4;//可以改变引用对象的值
    }

    //修饰数据
    private static void final_test_1() {
        final int value1 = 1;
        System.out.println("value1:" + value1);
        // value1 = 4;
        final double value2;
        value2 = 2.0;
        System.out.println("value2:" + value2);
        final Value value3 = new Value(1);
        //如果把其他的对象的引用赋值给value3，则会报错
        //value3 = new Value(2);
        System.out.println("value3 of v:" + value3.v);
        value3.v = 4;
        System.out.println("value3 of v:" + value3.v);
    }


}
