package com.demo.work;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("test 0");
		list.add("test 1");
		list.add("test 2");
		list.add("test 3");
		list.add("test 4");
		Student student = new Student();
		student.setList(list);
		List<String> list2 = student.getList();
		list2.forEach(line -> {
			System.out.println(line);
		});
		
		for(int i=0;i<list2.size();i++) {
			list2.set(i, "sdfa");
			
		}
		
		list2.forEach(line -> {
			System.out.println(line);
		});
	}
	
	
}

class Student {
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
