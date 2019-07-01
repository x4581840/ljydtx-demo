package com.demo.java._Integer;

public class IntegerTest {
    public static void main(String[] args) {
        objPoolTest2();
    }

    //结论
//    Integer a=127;
//    Integer b=127;
//    Integer c=128;
//    Integer d=128;
//    a==b true
//    c==d false
//
//    Integer a=new Integer (127);
//    Integer b=new Integer (127);
//    Integer c=new Integer (128);
//    Integer d=new Integer (128);
//    a==b false
//    c==d false

    //Integer 内部有一个-128到127的缓存池，但是如果是new出来的，那每一个对象都会去新建，不会用到缓存池的数据
    //实测其他基本数据类型的包装类都有这个缓存池，包括：Byte,Short,Long


    //Integer已经默认创建了数值【-128-127】的Integer缓存数据。所以使用Integer i1=40时，
    // JVM会直接在该在对象池找到该值的引用。   也就是说这种方式声明一个Integer对象时，
    // JVM首先会在Integer对象的缓存池中查找有木有值为40的对象，如果有直接返回该对象的引用；
    // 如果没有，则使用New keyword创建一个对象，并返回该对象的引用地址。
    // 因为Java中【==】比较的是两个对象是否是同一个引用（即比较内存地址），
    // i2和i2都是引用的同一个对象，So i1==i2结果为”true“；而使用new方式创建的i4=new Integer(40)、i5=new Integer(40)，
    // 虽然他们的值相等，但是每次都会重新Create新的Integer对象，不会被放入到对象池中，所以他们不是同一个引用，输出false。

    //对于i1==i2+i3、i4==i5+i6结果为True，是因为，Java的数学计算是在内存栈里操作的，
    // Java会对i5、i6进行拆箱操作，其实比较的是基本类型（40=40+0），他们的值相同，因此结果为True。
    public static void objPoolTest() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2 \t" + (i1 == i2)); //true
        System.out.println("i1=i2+i3 \t" + (i1 == i2 + i3)); //true  有计算
        System.out.println("i4=i5 \t" + (i4 == i5)); //false
        System.out.println("i4=i5+i6 \t" + (i4 == i5 + i6)); //true  有计算

        System.out.println();
    }

    public static void objPoolTest1() {
        Integer i1 = 400;
        Integer i2 = 400;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2 \t" + (i1 == i2)); //false，超出常量缓冲的数据的大小，-128到127，所以不是同一个对象,比较的是对象
        System.out.println("i1=i2+i3 \t" + (i1 == i2 + i3)); //true  有计算
        System.out.println("i4=i5 \t" + (i4 == i5)); //false
        System.out.println("i4=i5+i6 \t" + (i4 == i5 + i6)); //true  有计算

        System.out.println();
    }

    public static void objPoolTest2() {
        Integer i1 = 400;
        Integer i2 = 400;
        Integer i4 = new Integer(400);
        Integer i5 = new Integer(400);

        Integer i6 = 40;
        Integer i7 = 40;

        System.out.println("i1==i2 \t" + (i1 == i2)); //false 比较的是对象,超出常量缓冲的数据的大小，-128到127，所以不是同一个对象,
        System.out.println("i1.equals \t" + (i1.equals(i2))); //true
        System.out.println("i4=i5 \t" + (i4 == i5)); //false
        System.out.println("i4=i5+i6 \t" + (i4.equals(i5))); //true

        System.out.println("i5==i6 \t" + (i6 == i7)); //true 比较的是对象，没有超出常量缓冲的数据的大小，-128到127，所以是同一个对象,
        System.out.println("i1.equals \t" + (i6.equals(i7))); //true

        System.out.println();

        //包装类 比较里面的值，都要用equals，不要用==
    }
}
