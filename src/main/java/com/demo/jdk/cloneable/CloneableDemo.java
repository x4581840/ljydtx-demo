package com.demo.jdk.cloneable;

import com.demo.dynamicProxy.Student;

import java.util.ArrayList;
import java.util.List;

public class CloneableDemo {


    public static void main(String[] args) throws CloneNotSupportedException {
        //浅拷贝
        /**
         * 可以看出s1与s2的hashcode不同，也就是说clone方法并不是把s1的引用赋予s2，而是在堆中重新开辟了一块空间，将s1复制过去，将新的地址返回给s2。   
         * 但是s1中stu的hashcode与s2中stu的hashcode相同，也就是这两个指向了同一个对象，修改s2中的stu会造成s1中stu数据的改变。
         * 但是修改s2中的基本数据类型与Stirng类型时，不会造成s1中数据的改变，基本数据类型例如int，在clone的时候会重新开辟一个四个字节的大小的空间，
         * 将其赋值。而String则由于String变量的唯一性，如果在s2中改变了String类型的值，则会生成一个新的String对象，对之前的没有影响。  这就是浅度克隆。
         */
        /*SchoolSimpleClone s1 = new SchoolSimpleClone();
        s1.setSchoolName("实验小学");
        s1.setStuNums(100);
        StudentSimpleClone stu1 = new StudentSimpleClone();
        stu1.setAge(20);
        stu1.setName("zhangsan");
        s1.setStu(stu1);
        System.out.println("s1: "+s1+" s1的hashcode:"+s1.hashCode()+"  s1中stu1的hashcode:"+s1.getStu().hashCode());
        SchoolSimpleClone s2 = s1.clone();  //调用重写的clone方法，clone出一个新的school---s2
        System.out.println("s2: "+s2+" s2的hashcode:"+s2.hashCode()+" s2中stu1的hashcode:"+s2.getStu().hashCode());*/


        //深拷贝 01
        /*SchoolDeepClone_01 s01 = new SchoolDeepClone_01();
        s01.setSchoolName("实验小学");
        s01.setStuNums(100);
        StudentDeepClone_01 stu01 = new StudentDeepClone_01();
        stu01.setAge(20);
        stu01.setName("zhangsan");
        stu01.setSex(new StringBuffer("男"));
        s01.setStu(stu01);
        System.out.println("s01: "+s01+" s1的hashcode:"+s01.hashCode()+"  s01中stu01的hashcode:"+s01.getStu().hashCode());
        SchoolDeepClone_01 s02 = s01.clone();  //调用重写的clone方法，clone出一个新的school---s2
        System.out.println("s02: "+s02+" s2的hashcode:"+s02.hashCode()+" s02中stu01的hashcode:"+s02.getStu().hashCode());

        //修改s2中的值,看看是否会对s1中的值造成影响
        s02.setSchoolName("希望小学");
        s02.setStuNums(200);
        StudentDeepClone_01 stu02 = s02.getStu();
        stu02.setAge(30);
        stu02.setName("lisi");
        stu02.setSex(stu02.getSex().append("6666666"));
        s02.setStu(stu02);

        //再次打印两个school，查看结果
        //这里可以看到两个stu的hashcode已经不同了，说明这已经是两个对象了，但是在s2中修改sex的值，为什么还会影响到s1呢？
        //原因在于sex的类型是Stringbuffer，在clone的时候将StringBuffer对象的地址传递了过去，而StringBuffer类型没有实现cloneable接口，也没有重写clone方法。
        //这种情况应该怎么解决呢？
        //1.只实现浅度clone
        //2.stu2.setSex(new StringBuffer("newString"));  在设置stu2的sex时创建一个新的StringBuffer对象。
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("s01: "+s01+" hashcode:"+s01.hashCode()+"  s01中stu1的hashcode:"+s01.getStu().hashCode());
        System.out.println("s02: "+s02+" hashcode:"+s02.hashCode()+" s02中stu1的hashcode:"+s02.getStu().hashCode());
*/


        //深拷贝 02 序列化实现
        Teacher teacher = new Teacher();
        teacher.setName("张**");
        teacher.setTid("111");
        List<StudentDeepClone_02> stus = new ArrayList<>();
        StudentDeepClone_02 student = new StudentDeepClone_02("001", "朱**");
        stus.add(student);
        teacher.setStudent(stus);

        Teacher teacher1 = (Teacher) teacher.clone();
        System.out.println(teacher1.getName());
        System.out.println(teacher1.getTid());
        List<StudentDeepClone_02> ss = teacher1.getStudent();
        StudentDeepClone_02 s = ss.get(0);
        s.setId("002");
        s.setName("朱**222");
        System.out.println(teacher.getStudent().get(0).getId() + "-" + teacher.getStudent().get(0).getName());
        System.out.println(teacher1.getStudent().get(0).getId() + "-" + teacher1.getStudent().get(0).getName());

    }


}
