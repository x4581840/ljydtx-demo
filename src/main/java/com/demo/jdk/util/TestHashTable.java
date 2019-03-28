package com.demo.jdk.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class TestHashTable {
    public static void main(String[] args) {
        Hashtable map = new Hashtable();
        map.put("1", "one");
        map.put("2", "two");

        Enumeration e = map.elements();
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
        //two
        //one

        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            String key = (String)it.next();
            System.out.println(map.get(key));
        }
        //two
        //one

        StringBuffer s1 = new StringBuffer("a");
        StringBuffer s2 = new StringBuffer("a");
        System.out.println(s1.equals(s2));//false
        //StringBuffer类中没有重新定义equals这个方法，因此这个方法就来自Object类，
        //而Object类中的equals方法是用来比较“地址”的，所以等于false.


        String s5,s6,s3 = "abc", s4 ="abc" ;
        System.out.println(s3==s4); //true

        A obj1 = new A();
        A obj2 = new A();
        System.out.println(obj1==obj2); //false
        System.out.println(obj1.equals(obj2));//false
    }
}

class A{

}
