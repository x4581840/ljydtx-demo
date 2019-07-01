package com.demo.jdk.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        Dog d1 = new Dog("red");
        Dog d2 = new Dog("black");
        Dog d3 = new Dog("white");
        Dog d4 = new Dog("white");

        hashMap.put(d1, 10);
        hashMap.put(d2, 15);
        hashMap.put(d3, 5);
        hashMap.put(d4, 20);

        //print size
        System.out.println(hashMap.size());

        //loop HashMap
        Set<Entry> entrySet = hashMap.entrySet();
        for (Entry entry : entrySet) {
            System.out.println(entry.getKey().toString() + " - " + entry.getValue());
        }
    }
}

/*class Dog {
    String color;

    Dog(String c) {
        color = c;
    }
    public String toString(){
        return color + " dog";
    }
}*/

class Dog {
    String color;

    Dog(String c) {
        color = c;
    }

    public boolean equals(Object o) {
        boolean reult = ((Dog) o).color == this.color;
        System.out.println("color " + this.color + ",equals result:" + reult);
        return reult;
    }

    public int hashCode() {
        return color.length();
    }

    public String toString() {
        return color + " dog";
    }
}
