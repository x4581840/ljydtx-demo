package com.demo.mianshiti;

import java.util.HashSet;
import java.util.Set;

public class SecondBig {

    public static void main(String[] args) {
        SecondBig sb = new SecondBig();
        sb.max2rd();
    }

    /**
     * 10000个数字求第二大的数字，不许用排序算法
     */
    public void max2rd() {
        Set<Integer> sets = new HashSet<>();
        for(int i=1;i<=10000;i++) {
            sets.add(i);
        }
        long start = System.currentTimeMillis();
        int max1 = 0; //表示第一大的数
        int max2 = 0; //表示第二大的数
        for(Integer x : sets) {
            if(x > max1) {
                max2 = max1;
                max1 = x;
            }else if(max2 < x && x < max1) {
                max2 = x;
            }
        }
        System.out.println("cost:"+(System.currentTimeMillis()-start));
        System.out.println(max1);
        System.out.println(max2);
    }
}
