package com.demo.guava.immutablelist;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

public class ImmutableListTest {
    /**
     * 有时候，我们可能需要在代码里面使用一个包含许多常量的list
     * 不可变集合。。。
     */
    static List<String> CONSTANT_LIST =
            new ImmutableList.Builder<String>()
                    .add("平均值")
                    .add("总值")
                    .add("最大值")
                    .add("最小值")
                    .build();

    /*
     * 推荐使用上面的写法，样式好看，且好维护，也就是说，你现在要删除或者添加一条，直接添加一行就好。常量整体也看着条理清晰。
     */
    //List<String> CONSTANT_LIST = ImmutableList.of("平均值","总值","最大值","最小值");

    //List<String> CONSTANT_LIST = ImmutableList.copyOf(Lists.newArrayList("平均值","总值","最大值","最小值"));

    public static void main(String[] args) {

        String str = "最大值";
        if (ImmutableListTest.CONSTANT_LIST.contains(str)) {
            System.out.println("常量list集合包含此string");
        }
        //此常量list不能add,remove,不然会抛异常的。
//        ImmutableListTest.CONSTANT_LIST.add("sss");
//        ImmutableListTest.CONSTANT_LIST.remove(str);

        //ImmutableList是一个不可变、线程安全的列表集合，它只会获取传入对象的一个副本，而不会影响到原来的变量或者对象
        int a = 23;
        ImmutableList<Integer> list = ImmutableList.of(a, 12);
        System.out.println(list);
        a = 232;
        System.out.println(list);
        //输出 [23, 12]
        //    [23, 12]

    }
}
