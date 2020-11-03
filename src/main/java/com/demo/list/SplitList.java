package com.demo.list;

import com.demo.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author longjianyong
 * @Date 2020/7/15 9:11 AM
 * @Version 1.0
 **/

public class SplitList {

    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        test.add("6");
        test.add("7");
        test.add("8");
        test.add("9");
        test.add("10");

        /*List<List<String>> res = groupList(test);
        res.forEach(list -> {
            System.out.println("===");
            list.forEach(str -> {
                System.out.println(str);
            });
        });*/
        /*int MAX_SEND = 100;
        //int limit = test.size()/MAX_SEND + (test.size()%MAX_SEND > 0 ? 1 : 0);
        int limit = 1;
        System.out.println(limit);
        List<List<String>> splitList = Stream.iterate(0, n->n+1).limit(limit).parallel().map(a->
            test.stream().skip(a*MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())
        ).collect(Collectors.toList());

        splitList.forEach(list -> {
            System.out.println("===");
            list.forEach(str -> {
                System.out.println(str);
            });
        });*/

        String str = "\"\"longbaobao\"\"facai\"\"";
        String out = str.replace("\"\"", "\"");
        System.out.println(out);
    }

    public static List<List<String>> groupList(List<String> list) {
        List<List<String>> listGroup = new ArrayList<List<String>>();
        int listSize = list.size();
        //子集合的长度，比如 500
        int toIndex = 3;
        for (int i = 0; i < list.size(); i += 3) {
            if (i + 3 > listSize) {
                toIndex = listSize - i;
            }
            List<String> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

    public  List<List<Student>> groupList1(List<Student> test) {
        List<List<Student>> listGroup = new ArrayList<List<Student>>();
        int MAX_SEND = 3;
        int limit = test.size()/MAX_SEND + (test.size()%MAX_SEND > 0 ? 1 : 0);
        System.out.println(limit);
        List<List<Student>> splitList = Stream.iterate(0, n->n+1).limit(limit).parallel().map(a->
                test.stream().skip(a*MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())
        ).collect(Collectors.toList());
        return listGroup;
    }
}
