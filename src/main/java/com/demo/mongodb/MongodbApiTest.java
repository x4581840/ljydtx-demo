package com.demo.mongodb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/11/5 4:31 PM
 * @Version 1.0
 **/
@Slf4j
public class MongodbApiTest {

    public static void main(String[] args) {
//        Document document = new Document();
//        document.put("test",1);
//
//        System.out.println(document.get("test"));
//        System.out.println(document.getInteger("test", 0));
//        System.out.println(document.getInteger("test"));
//        System.out.println(document.get("t"));
//        System.out.println(document.getInteger("t",100));

//        B b = new B();
//        b.setD(1000);
//        C c = new C();
//        c.setD(1);
//        c.setC(2);
//
//        BeanUtils.copyProperties(b, c);
//
//        System.out.println(c.getD());
//        System.out.println(c.getC());

//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(int index=0;index<10;index++) {
//            final int i = index;
//            list.forEach(str -> {
//                System.out.println(str+i);
//            });
//        }
//        System.out.println("sssssssss");

        A a = new A();
        System.out.println(a.getI());
        List<Object> scoreList = new ArrayList<>();
        scoreList.add(12.2);
        scoreList.add(12.3);
        System.out.println(StringUtils.join(scoreList, ","));
    }

    public static void test3(CompletionArgument c) {
        System.out.println(c.getAssessmentId());
    }

//    public static void test1(A a) {
//        if(a instanceof B) {
//            System.out.println(((B)a).getD());
//        }
//        if(a instanceof C) {
//            System.out.println(((C)a).getD());
//        }
//    }
//
//    public static void test(A a) {
//        if(a instanceof B) {
//            System.out.println(((B)a).getD());
//        }
//        if(a instanceof C) {
//            System.out.println(((C)a).getD());
//        }
//        System.out.println(a.getA());
//    }

}



class A {
    Float i;
    int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Float getI() {
        return i;
    }

    public void setI(Float i) {
        this.i = i;
    }
}

class B {
    int d;

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}

class C{
    int d;
    int c;

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}

