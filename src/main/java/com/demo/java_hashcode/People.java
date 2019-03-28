package com.demo.java_hashcode;

import java.util.HashMap;

public class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return name.hashCode()*37+age;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.name.equals(((People)obj).name) && this.age== ((People)obj).age;
    }


    public static void main(String[] args) {

        //重写了equals，没有重写hashcode
        /*People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode()); //396873410

        p1.setName("tom");
        p1.setAge(13);
        System.out.println(p1.age);
        System.out.println(p1.hashCode()); //396873410 改变元素，hashCode不变

        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);

        People people = new People("Jack", 12);
        System.out.println(people.hashCode());
//        System.out.println(hashMap.get(new People("Jack", 12)));
        System.out.println(hashMap.get(people)); //null  重写了equals,没有重写hashcode

        System.out.println(hashMap.get(p1)); //1*/

        //重写了equals，重写hashCode
        /*People p3 = new People("Jack", 12);
        System.out.println(p3.hashCode());

        HashMap<People, Integer> hashMap1 = new HashMap<People, Integer>();
        hashMap1.put(p3, 1);

        p3.setAge(13);

        System.out.println(hashMap1.get(p3));*/


        //没有重写equals和hashcode

        /*Person p3 = new Person("Jack", 12);
        System.out.println(p3.hashCode());

        HashMap<Person, Integer> hashMap1 = new HashMap<Person, Integer>();
        hashMap1.put(p3, 1);

        System.out.println(hashMap1.get(p3));

        p3.setAge(13);
        System.out.println(p3.hashCode());
        System.out.println(hashMap1.get(p3));*/

        //若重写equals(Object obj)方法，有必要重写hashcode()方法，
        // 确保通过equals(Object obj)方法判断结果为true的两个对象具备相等的hashcode()返回值

        Person p1 = new Person("jack", 1);

        Person p2 = new Person("jack", 2);

        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode()+","+p2.hashCode());

    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

    //下面这段话摘自Effective Java一书：
       /*在程序执行期间，只要equals方法的比较操作用到的信息没有被修改，那么对这同一个对象调用多次，
            hashCode方法必须始终如一地返回同一个整数。
            如果两个对象根据equals方法比较是相等的，那么调用两个对象的hashCode方法必须返回相同的整数结果。
            如果两个对象根据equals方法比较是不等的，则hashCode方法不一定得返回不同的整数。
        　　对于第二条和第三条很好理解，但是第一条，很多时候就会忽略。在《Java编程思想》一书中的P495页也有同第一条类似的一段话：
        　　“设计hashCode()时最重要的因素就是：无论何时，对同一个对象调用hashCode()都应该产生同样的值。
        如果在讲一个对象用put()添加进HashMap时产生一个hashCdoe值，而用get()取出时却产生了另一个hashCode值，
        那么就无法获取该对象了。所以如果你的hashCode方法依赖于对象中易变的数据，用户就要当心了，因为此数据发生变化时，
        hashCode()方法就会生成一个不同的散列码”。*/

