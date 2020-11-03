package com.demo.encrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/10/23 3:40 PM
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(11111);
//        list1.add(2);
        list1.add(22222);
//        list1.add(4);
        list1.add(33333);
//        list1.add(6);
        List<Integer> list2 = new ArrayList<>();
        list2.add(11111);
        list2.add(22222);
        list2.add(4);
        list2.add(6);
        System.out.println(list2.containsAll(list1));

//        list1.removeAll(list2);
//        for (Integer integer : list1) {
//            System.out.println(integer);
//        }
//        for (Integer integer : list2) {
//            System.out.println(integer);
//        }

//        list2.stream().forEach(j -> {
//
//            if(j == 5) {
//                for (Integer i : list1) {
//                    if (i == 3) {
//                        return;
//                    }
//                }
//            }
//            System.out.println(j);
//
//        });
//
//
//        System.out.println("ss");
    }
}
