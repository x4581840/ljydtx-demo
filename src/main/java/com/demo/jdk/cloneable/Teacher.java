package com.demo.jdk.cloneable;

import java.io.*;
import java.util.List;

/**
 * @author
 * @version 1.0
 * description:Teacher类，用序列化和反序列化实现深克隆，clone方法中对象写到内存中，再从内存中读出
 * date: 2018-06-29 15:26
 */

public class Teacher implements Serializable, Cloneable {

    private String tid; //教工编码
    private String name; //姓名
    private List<StudentDeepClone_02> student; //学生

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTid() {

        return tid;
    }

    public void setStudent(List<StudentDeepClone_02> student) {

        this.student = student;
    }

    public List<StudentDeepClone_02> getStudent() {

        return student;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object t = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            t = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return t;
    }
}
