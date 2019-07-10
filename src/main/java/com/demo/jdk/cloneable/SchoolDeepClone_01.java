package com.demo.jdk.cloneable;

/**
 * 深克隆
 */
public class SchoolDeepClone_01 implements Cloneable {

    private String schoolName;   //学校名称
    private int stuNums;         //学校人数
    private StudentDeepClone_01 stu;         //一个学生

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStuNums() {
        return stuNums;
    }

    public void setStuNums(int stuNums) {
        this.stuNums = stuNums;
    }

    public StudentDeepClone_01 getStu() {
        return stu;
    }

    public void setStu(StudentDeepClone_01 stu) {
        this.stu = stu;
    }

    @Override
    protected SchoolDeepClone_01 clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        //把成员属性是引用类型的数据，手动clone一份
        SchoolDeepClone_01 s = null;
        s = (SchoolDeepClone_01) super.clone();
        s.stu = stu.clone();
        return s;
    }

    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", stuNums=" + stuNums + ", stu=" + stu + "]";
    }
}
