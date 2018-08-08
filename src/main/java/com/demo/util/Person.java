package com.demo.util;

import java.util.Date;

public class Person {
    private Long id;
    private String name;
    private Date birthday;

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthday + "]";
    }

    public Person() {

    }

    public Person(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
