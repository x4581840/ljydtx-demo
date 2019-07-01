package com.demo.serialize;

import java.io.*;
import java.text.MessageFormat;

/**
 * 序列化和反序列化前后的对象不一致，内存地址不同，但是内容相同
 */
public class SerializeTest {
    public static void main(String[] args) throws Exception {
        SerializePerson();//序列化Person对象
        Father p = DeserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}", p.getName(), p.getAge(), p.getSex()));
    }

    private static void SerializePerson() throws FileNotFoundException, IOException {
        Father father = new Father();
        father.setName("gacl");
        father.setAge(25);
        father.setSex("男");
        Son son = new Son();
        son.setAge(18);
        System.out.println("father before : " + father);
        System.out.println("son before : " + son);
        father.setSon(son);
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("test.obj")));
        oo.writeObject(father);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    private static Father DeserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("test.obj")));
        Father father = (Father) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}", father.getName(), father.getAge(), father.getSex()));
        System.out.println("father after : " + father);
        System.out.println("son after : " + father.getSon());
        return father;
    }
}

class Father implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private int age;
    private String name;
    private String sex;
    private Son son;

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

class Son implements Serializable {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}