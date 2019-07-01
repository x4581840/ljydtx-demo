package com.demo.util;


import org.junit.Test;

import java.util.BitSet;
import java.util.Random;

/**
 * 有1千万个随机数，随机数的范围在1到1亿之间。
 * 现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来？
 */
public class BitSetTest2 {
    public static final int NUM = 10000000;//10;
    public static final int NUM2 = 10 * NUM;

    @Test
    public void test() {
        int[] input = new int[NUM2 + 1];
        Random random = new Random();
        for (int i = 1; i < NUM + 1; i++) {
            input[random.nextInt(NUM2) + 1] = 1;
        }
        for (int i = 1; i < NUM2 + 1; i++) {
            if (input[i] == 0)
                System.out.println(i);
        }
    }

    @Test
    public void test2() {
        Boolean[] input = new Boolean[NUM2 + 1];
        Random random = new Random();
        for (int i = 1; i < NUM + 1; i++) {
            input[random.nextInt(NUM2) + 1] = true;
        }
        for (int i = 1; i < NUM2 + 1; i++) {
            if (input[i] == false)
                System.out.println(i);
        }
    }

    @Test
    public void test3() {
        BitSet input = new BitSet(NUM2 + 1);
        Random random = new Random();
        int temp;
        for (int i = 0; i < NUM; i++) {
            temp = random.nextInt(NUM2) + 1;
            while (input.get(temp)) {
                temp = random.nextInt(NUM2) + 1;
            }
            input.set(temp);
        }
        System.out.println(input.cardinality());

        int j = 1;
        for (int i = 1; i < NUM2 + 1; i++) {
            if (!input.get(i)) {
                if (j++ % 10 != 0)
                    System.out.print(i + ",");
                else {
                    j = 1;
                    System.out.println(i);
                }
            }
        }
    }
}
