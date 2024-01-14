package com.demo.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student Student1 = new Student(1,"熊大",1,"森林第一个小屋");
        Student Student2 = new Student(2,"熊二",2,"森林第二个小屋");
        Student Student3 = new Student(3,"光头强",3,"森林第三个小屋");
        Student Student4 = new Student(4,"熊大",4,"森林第四个小屋");
        list.add(Student1);
        list.add(Student2);
        list.add(Student3);
        list.add(Student4);

//        StreamTest.ListToMap(list);
        StreamTest.ListFilter(list);
    }

    // List转成Map
    public static void ListToMap(List<Student> stuList) {
//        1, List 转成Map<String,Object>
//        如果key有相同的值，会报Duplicate key错误
//        Map<String, Student> map = stuList.stream().collect(Collectors.toMap(Student::getName,stu->stu));

//        如果key有相同的值，不会报错，只会覆盖
        Map<String, Student> map = stuList.stream().collect(Collectors.toMap(Student::getName, stu->stu, (val1,val2)->val1));
        System.out.println(JSON.toJSONString(map));

//        2,List 转成Map<String,String>
        Map<String, String> map1 = stuList.stream().collect(Collectors.toMap(Student::getName, Student::getAddress, (val1,val2)->val1));
        System.out.println(JSON.toJSONString(map1));

//        3,List 转成Map<String,List>
        Map<String, List<Student>> map2 = stuList.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(JSON.toJSONString(map2));

    }

    // List 过滤
    public static void ListFilter(List<Student> stuList) {
//        list 根据对象属性过来
        List<Student> list1 = stuList.stream().filter(stu -> "熊大".equals(stu.getName())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list1));

//        获取id，并且去null
        List<Integer> list2 = stuList.stream().map(Student::getId).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list2));

//        获取某一个属性的list
        List<String> list3 = stuList.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list3));

    }
}
