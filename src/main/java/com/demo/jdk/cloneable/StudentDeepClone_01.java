package com.demo.jdk.cloneable;

public class StudentDeepClone_01 implements Cloneable {
    private String name;
    private int age;
    private StringBuffer sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StringBuffer getSex() {
        return sex;
    }

    public void setSex(StringBuffer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }

    @Override
    protected StudentDeepClone_01 clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (StudentDeepClone_01) super.clone();
    }

}
