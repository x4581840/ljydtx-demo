package com.demo.test.main;

import org.apache.lucene.util.RamUsageEstimator;
import org.roaringbitmap.RoaringBitmap;

import java.util.Iterator;

public class TestMain {
    public static void main(String[] args) {
       /* RoaringBitmap bitmap = new RoaringBitmap();
        bitmap.add(12);
        bitmap.add(123);
        bitmap.add(122);
        bitmap.add(124);
//        System.out.println(bitmap.serializedSizeInBytes());
//        System.out.println(bitmap.getSizeInBytes());
        System.out.println(bitmap);

        Iterator<Integer> iterable = bitmap.iterator();

        while (iterable.hasNext()) {
            System.out.println(iterable.next());
        }*/

        RoaringBitmap bitmap = new RoaringBitmap();
        for(int i=0;i<100000;i++) {
            bitmap.add(i);
        }
        long size = RamUsageEstimator.sizeOf(bitmap);
        System.out.println(size);
        System.out.println(size/1024);
        System.out.println(size/(1024*1024));

        /*Map<String, int[]> map = new HashMap<>();
        int[] detail = new int[1000000];
        for(int i=0;i<1000000;i++) {
            detail[i] = i;
        }
        map.put("odps1", detail);
        long size = RamUsageEstimator.sizeOf(map);
        System.out.println(size);
        System.out.println(size/(1024*1024));//3M*/

        /*Map<String, int[]> map = new HashMap<>();
        int[] detail = new int[100000];
        for(int i=0;i<100000;i++) {
            detail[i] = i;
        }
        map.put("odps1", detail);
        long size = RamUsageEstimator.sizeOf(map);
        System.out.println(size);
        System.out.println(size/1024);//390K*/
/*
//        int[] test = new int[250000000];
//        List<Integer> test = new ArrayList<>();
        BitSet bitSet = new BitSet();
        for(int i=0;i<100000;i++) {
//            test[i] = i;
//            test.add(i);
            bitSet.set(i);
        }

        long size = RamUsageEstimator.sizeOf(bitSet);
        System.out.println(size);
        System.out.println(size/1024);
        System.out.println(size/(1024*1024));//*/
    }
}
