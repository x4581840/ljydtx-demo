package com.demo.guava.cache;

import java.util.Arrays;

public class CacheTest2 {
    public static void main(String[] args) {
        Dao dao = new Dao();

        for (int i = 0; i < 3; i++) {
            System.out.println("--- " + i + " ---");
            System.out.println(Arrays.toString(dao.getPOIList("0101").toArray()));
            System.out.println(Arrays.toString(dao.getPOIList("0102").toArray()));
            System.out.println(Arrays.toString(dao.getPOIList("0103").toArray()));
            System.out.println();
        }
    }
}
