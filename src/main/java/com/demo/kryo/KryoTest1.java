//package com.demo.kryo;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.demo.dynamicProxy.StudentSimpleClone;
//
//public class KryoTest1 {
//	public static void main(String[] args) throws FileNotFoundException {
//		List<StudentSimpleClone> list = new ArrayList<>();
//		StudentSimpleClone student = new StudentSimpleClone("hhh");
//		list.add(student);
//		student = new StudentSimpleClone("nnn");
//		list.add(student);
//
//		byte[] bytes = KryoUtils.setSerializableList(list, ArrayList.class);
//		Object object = KryoUtils.getSerializableList(ArrayList.class, bytes);
//		List<StudentSimpleClone> students = (List<StudentSimpleClone>)KryoUtils.getSerializableList(ArrayList.class, bytes);
//		students.forEach(stu -> {
//			System.out.println(stu.getName());
//		});
//	}
//}
