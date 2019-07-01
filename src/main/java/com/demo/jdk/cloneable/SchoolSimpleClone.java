package com.demo.jdk.cloneable;

/**
 * 浅克隆
 */
public class SchoolSimpleClone implements Cloneable {

    private String schoolName;   //学校名称
    private int stuNums;         //学校人数
    private StudentSimpleClone stu;         //一个学生

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

    public StudentSimpleClone getStu() {
        return stu;
    }

    public void setStu(StudentSimpleClone stu) {
        this.stu = stu;
    }

    @Override
    protected SchoolSimpleClone clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (SchoolSimpleClone) super.clone();
    }

    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", stuNums=" + stuNums + ", stu=" + stu + "]";
    }
}
