package com.demo.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;


public class StudentSerializer extends Serializer<Student> {

    @Override
    public void write(Kryo kryo, Output output, Student object) {
        String id = object.getId();
        String name = object.getName();
        int age = object.getAge();
        output.writeString(id);
        output.writeString(name);
        output.writeInt(age);
    }

    @Override
    public Student read(Kryo kryo, Input input, Class<Student> type) {
        String id = input.readString();
        String name = input.readString();
        int age = input.readInt();
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
