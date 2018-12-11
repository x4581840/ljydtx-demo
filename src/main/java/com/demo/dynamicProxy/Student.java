package com.demo.dynamicProxy;

public class Student implements Person1 {

    private String name;

    public Student(String name) {
        this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name + "上交班费50元");
    }
}
