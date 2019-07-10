package com.demo.jdk.cloneable;

import java.io.Serializable;


public class StudentDeepClone_02 implements Serializable {

    private String id;
    private String name;

    public StudentDeepClone_02(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }
}
