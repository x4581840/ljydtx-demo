package com.demo.kryo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.demo.dynamicProxy.Student;

public class KryoTest1 {
	public static void main(String[] args) throws FileNotFoundException {
		List<Student> list = new ArrayList<>();
		Student student = new Student("hhh");
		list.add(student);
		student = new Student("nnn");
		list.add(student);
		
		byte[] bytes = KryoUtils.setSerializableList(list, ArrayList.class);
		Object object = KryoUtils.getSerializableList(ArrayList.class, bytes);
		List<Student> students = (List<Student>)KryoUtils.getSerializableList(ArrayList.class, bytes);
		students.forEach(stu -> {
			System.out.println(stu.getName());
		});
	}
}
