package com.demo.serialize;

import java.io.*;

public class ExternalizableTest {
    public static void main(String args[]) {
        try {

            FileOutputStream fileOut = new FileOutputStream("test.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);

            Student s1 = new Student("张三", 40);
            oos.writeObject(s1);

            oos.close();


            FileInputStream fileIn = new FileInputStream("test.obj");
            ObjectInputStream ois = new ObjectInputStream(fileIn);

            Student s3 = (Student) ois.readObject();

            System.out.println("是否为同一个对象:" + (s1 == s3));//false
            //发现并不能保证序列化和反序列化之后的对象是一致的，因为我们在反序列化的过程中，
            // 是先创建一个对象，然后再通过对对象进行赋值来完成对象的反序列化，这样问题就来了，
            // 在创建了一个新的对象之后，对象引用和原本的对象并不是指向同一个目标。
            // 因此我们只能保证他们的数据和版本一致，并不能保证对象一致。

            System.out.println(s3);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}

class Student implements Externalizable {
    public static final long serializableUID = 42L;
    private String name;
    private int age;

    public Student() {
        System.out.println("调用Student无参数的构造方法");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("调用Student带参的构造方法");
    }

    public String toString() {
        return "姓名：" + name + " 年龄：" + age;
    }

    //序列化对象中的属性
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(age);
    }

    //反序列化对象中的属性
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = (Integer) in.readObject();
    }

}
