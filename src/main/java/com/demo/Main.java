package com.demo;

public class Main {

    private Main() {}

    public static void objPoolTest() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2\t" + (i1 == i2));
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
        System.out.println("i4=i5\t" + (i4 == i5));
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));

        System.out.println();
    }

    public static void main(String[] args) {

        objPoolTest();

        final Demo demo = new Demo(1);
        System.out.println(demo.getCount());
        demo.setCount(2);
        System.out.println(demo.getCount()); //可以改变count的值

        //demo = new Demo(3); 会报错

        int t1=57;
        int t2=67;
        int t3=124;
        int t4=124;

        Integer i1=new Integer(t1);
        Integer i2=new Integer(t2);
        Integer i3=new Integer(t3);
        Integer i4=new Integer(t4);


        Boolean ri1 = i1.equals(i2); // false
        Boolean ri2 = i3.equals(i1+i2); // true
        Boolean ri3 = i3.equals(i4); // true

        System.out.println("/n/n-----【i1.equals(i2)】"+ri1+"/n-----【i3.equals(i1+i2)】"+ri2+"/n-----【i3.equals(i4)】"+ri3);

        String st1="wasiker ";
        String st2="is super man";
        String st3="wasiker is super man";
        String st4="wasiker is super man";

        Boolean b1=(st1==st2); // false
        Boolean b2=(st1+st2)==st3; // false
        Boolean b3=(st3==st4); // true

        System.out.println("/n/n-----【st1==st2】"+b1+"/n-----【(st1+st2)==st3】"+b2+"/n-----【st3==st4】"+b3);
    }

    static class Demo {
        private int count;

        public Demo(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
