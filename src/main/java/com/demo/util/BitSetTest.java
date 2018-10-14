package com.demo.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.BitSet;

public class BitSetTest {

    //全量bitset
    private static BitSet allBitSet = new BitSet();
    //偶数bitset
    private static BitSet evenBitSet = new BitSet();
    //奇数bitset
    private static BitSet oddBitSet = new BitSet();
    //空bitset
    private static BitSet emptyBitSet = new BitSet();

    @BeforeClass
    public static void init(){
        for (int i = 0; i < 63; i++) {
            allBitSet.set(i);
            if (i % 2 == 0) {
                evenBitSet.set(i);
            } else {
                oddBitSet.set(i);
            }
        }
    }

    //测试初始化
    @Test
    public void testInit() {
        //断点进去看
        BitSet initBitSet1 = new BitSet(55);
        BitSet initBitSet2 = new BitSet(129);
    }

    //测试基础的and\or\xor运算
    //如果a、b两个值不相同，则异或结果为1。如果a、b两个值相同，异或结果为0。s
    @org.junit.Test
    public void testOper() {
        //System.out.println(evenBitSet.toByteArray());
        evenBitSet.and(allBitSet);
        System.out.println("偶数Bit and 全量Bit：" + evenBitSet);
        evenBitSet.xor(allBitSet);
        System.out.println("偶数Bit xor 全量Bit：" + evenBitSet);
        evenBitSet.or(allBitSet);
        System.out.println("偶数Bit or 全量Bit：" + evenBitSet);
    }

    //测试动态扩展，每次是以64位为单位
    @org.junit.Test
    public void testExpand() {
        testSize();
        allBitSet.set(100000000);
        System.out.println("全量Bit-设置64之后大小：" + allBitSet.size() / 8 / 1024 / 1024 + "m");
        System.out.println("全量Bit-设置64之后长度：" + allBitSet.length());
        System.out.println("全量Bit-设置64之后实际true的个数：" + allBitSet.cardinality());
    }

    //oddBitSet过滤掉evenBitSet
    @Test
    public void testOddFilterEvenBitSet() {
        oddBitSet.set(2);
        oddBitSet.set(4);
        oddBitSet.set(6);
        System.out.println("过滤前：oddBitSet:" + oddBitSet);
        evenBitSet.and(oddBitSet);
        oddBitSet.xor(evenBitSet);
        System.out.println("oddBitSet过滤evenBitSet相同的元素的结果：" + oddBitSet);
    }

    //偶数和奇数bitset合并去重之后和allbitSet内容一致
    @Test
    public void testOddAndEventBitSet() {
        oddBitSet.set(2);
        oddBitSet.set(4);
        oddBitSet.set(6);
        System.out.println("偶数BitSet合并前 ：" + evenBitSet);
        System.out.println("奇数BitSet合并前 ：" + oddBitSet);
        System.out.println("------------------------");
        oddBitSet.or(evenBitSet);
        System.out.println("偶数BitSet合并后 ：" + evenBitSet);
        System.out.println("奇数BitSet合并后 ：" + oddBitSet);
        System.out.println("全亮BitSet内容是 ：" + allBitSet);
        Assert.assertTrue(oddBitSet.equals(allBitSet));
    }


    //返回true的个数
    @org.junit.Test
    public void testCardinality() {
        System.out.println("偶数Bit-true的个数：" + evenBitSet.cardinality());
    }

    //判断是否为空
    @org.junit.Test
    public void testIsEmpty() {
        System.out.println("全量Bit-判断非空：" + allBitSet.isEmpty());
        System.out.println("空  Bit-判断非空：" + emptyBitSet.isEmpty());
    }

    //根据下表开始结束获取
    @org.junit.Test
    public void testGetFromEnd() {
        System.out.println("全量Bit-[0,5]：" + allBitSet.get(0, 5));
        System.out.println("空  Bit-[0,5]：" + emptyBitSet.get(0, 5));
    }

    //判断是否存在bitset
    @org.junit.Test
    public void testGet() {
        System.out.println("全量Bit-下标为2是否存在:" + allBitSet.get(2));
        System.out.println("偶数Bit-下标为1是否存在:" + evenBitSet.get(1));
        System.out.println("偶数Bit-下标为2是否存在:" + evenBitSet.get(2));
    }

    //计算bitset内存大小
    @org.junit.Test
    public void testSize() {
        System.out.println("空  Bit-大小：:" + emptyBitSet.size() + "byte");
        System.out.println("偶数Bit-大小：" + evenBitSet.size() + "byte");
        System.out.println("全量Bit-大小：" + allBitSet.size() + "byte");
    }

    //计算bitset长度（bitset最大数+1）
    @org.junit.Test
    public void testLength() {
        System.out.println("全量Bit-长度：" + allBitSet.length());
        System.out.println("偶数Bit-长度：" + evenBitSet.length());
    }
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        BitSet bitSet = new BitSet();
        // true--64
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());
        bitSet.set(0);
        // false--64
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());
        bitSet.set(1);
        // false--64
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());
        // false
        System.out.println(bitSet.get(65));
        // false--64
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());
        bitSet.set(65);
        // false--128
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());

        System.out.println(63>>6); //0

        bitSet.set(100909);
        // true-- 100928
        System.out.println(bitSet.isEmpty()+"--"+bitSet.size());
        // true
        System.out.println(bitSet.get(100909));
        // false
        System.out.println(bitSet.get(100908));

        System.out.println(bitSet.cardinality());//4,上面设置了4个index

        System.out.println(bitSet.toString()); //{0, 1, 65, 100909}

        System.out.println(Integer.MAX_VALUE);

        BitSet b = new BitSet();// Integer.MAX_VALUE=2147483647
        System.out.println(b.size());
    }

}
