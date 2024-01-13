package com.demo.shejimoshi.danli;


import java.util.Random;

/**
 * 枚举式 单例
 * <p>
 * 使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，并由JVM从根本上提供保障，
 * 绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 */
public enum EnumSingle {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    uniqueInstance;

    /**
     * 单例可以有自己的操作
     */
    public void singletonOperation() {
        //功能处理
    }

    /**
     * 单例可以有自己的操作
     */
    public int generRandom() {
        Random random = new Random();
        int result = random.nextInt(10);
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        //获取单例 EnumSingle.uniqueInstance
        System.out.println(EnumSingle.uniqueInstance.generRandom());
    }
}
