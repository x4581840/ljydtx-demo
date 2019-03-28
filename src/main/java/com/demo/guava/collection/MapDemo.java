package com.demo.guava.collection;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
//        test_1();
//        test_2();
        test_3();
    }

    //求两个map的交集，左集，右集
    private static void test_1() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "上海");
        map.put("2", "北京");
        map.put("3", "香港");

        Map<String, String> map1 = Maps.newHashMap();
        map1.put("1", "上海");
        map1.put("2", "北京");
        map1.put("4", "深圳");

        MapDifference<String, String> mapd = Maps.difference(map, map1);

        //左集(即差集，返回只存在于map独有的数据)
        mapd.entriesOnlyOnLeft().entrySet().forEach(entry -> {
            System.out.println("entriesOnlyOnLeft "+entry.getKey()+":"+entry.getValue());
        });
        //entriesOnlyOnLeft 3:香港

        //右集(即差集，返回只存在于map1独有的数据)
        mapd.entriesOnlyOnRight().entrySet().forEach(entry -> {
            System.out.println("entriesOnlyOnleft "+entry.getKey()+":"+entry.getValue());
        });
        //entriesOnlyOnleft 4:深圳

        //交集
        mapd.entriesInCommon().entrySet().forEach(entry -> {
            System.out.println("entriesInCommon "+entry.getKey()+":"+entry.getValue());
        });
        //entriesInCommon 1:上海
        //entriesInCommon 2:北京
    }

    //set的交集，并集，差集
    private static void test_2() {
        HashSet<Integer> setF = Sets.newHashSet(1, 2, 3);
        HashSet<Integer> setT = Sets.newHashSet(3, 4, 5);

        //并集，结果【1，2，3，4，5】
        Sets.SetView<Integer> union = Sets.union(setF, setT);
        union.forEach(i -> {
            System.out.println("并集元素："+i);
        });

        //差集，返回只存在于setF独有的数据，结果【1，2】
        Sets.SetView<Integer> difference = Sets.difference(setF, setT);
        difference.forEach(i -> {
            System.out.println("差集元素："+i);
        });

        //交集，结果【3】
        Sets.SetView<Integer> intersection = Sets.intersection(setF, setT);
        intersection.forEach(i -> {
            System.out.println("交集元素："+i);
        });
    }

    private static void test_3() {
        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List list2 =new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        //并集
        //list1.addAll(list2);
        //交集,只有retainAll，没有retain方法
        //list1.retainAll(list2);
        //差集
        list1.removeAll(list2);
        //无重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);

        Iterator<String> it=list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }
    }

    private static void test_4() {
        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        Map map = new HashMap();

    }
}
