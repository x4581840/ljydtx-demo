package com.demo.stream;

import lombok.Data;

@Data
public class Student {
    private Integer id ;
    private String name;
    private Integer age;
    private String address;

    public Student(Integer id, String name, Integer age, String address) {
        this.id = id ;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
