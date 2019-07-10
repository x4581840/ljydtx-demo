package com.demo.serialize;

import java.io.*;

/*
序列化ID的作用：
       其实，这个序列化ID起着关键的作用，它决定着是否能够成功反序列化！
       简单来说，java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。
       在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地实体类中的serialVersionUID进行比较，
       如果相同则认为是一致的，便可以进行反序列化，否则就会报序列化版本不一致的异常。等会我们可以通过代码验证一下。


       序列化ID如何产生：
       当我们一个实体类中没有显示的定义一个名为“serialVersionUID”、类型为long的变量时，
       Java序列化机制会根据编译时的class自动生成一个serialVersionUID作为序列化版本比较，
       这种情况下，只有同一次编译生成的class才会生成相同的serialVersionUID。
       譬如，当我们编写一个类时，随着时间的推移，我们因为需求改动，需要在本地类中添加其他的字段，
       这个时候再反序列化时便会出现serialVersionUID不一致，导致反序列化失败。那么如何解决呢？
       便是在本地类中添加一个“serialVersionUID”变量，值保持不变，便可以进行序列化和反序列化。
 */

public class SerializeVersionUIDTest {
    public static void main(String[] args) {

        //首先，我们生成一个本地Person类，里面添加一个字段age，然后将其序列化存于本地test.obj中
        //将Person对象序列化
//        SerializePerson();

        //然后我们在Person类中再添加一个字段，name，然后直接从test.obj中反序列化，再运行一下，看看会出现什么问题,会报序列化失败
        // 反序列化生成Person对象
        Person person = DeserializePerson();
        System.out.println("name :" + person.getName());
        System.out.println("age  :" + person.getAge());
    }


    /**
     * @param
     * @return void
     * @throws
     * @author crazyandcoder
     * @Title: 序列化Person对象，将其存储到 E:/hello.txt文件中
     * @date [2015-8-5 上午11:21:27]
     */
    private static void SerializePerson() {
        Person person = new Person();
        person.setAge(30);
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("test.obj"));
            outputStream.writeObject(person);
            System.out.println("序列化成功。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 执行反序列化过程生产Person对象
     *
     * @param @return
     * @return People
     * @throws
     * @author crazyandcoder
     * @Title: DeserializePerson
     * @date [2015-8-5 下午1:30:12]
     */
    private static Person DeserializePerson() {

        Person person = null;
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("test.obj"));
            try {
                person = (Person) inputStream.readObject();
                System.out.println("执行反序列化过程成功。");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return person;
    }
}

class Person implements Serializable {

//    private static final long serialVersionUID = -5809782578272943999L;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}