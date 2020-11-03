package com.demo.model;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author longjianyong
 * @Date 2020/10/21 10:31 AM
 * @Version 1.0
 **/
public class ValidationObject {

    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Boolean areBeautiful;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAreBeautiful() {
        return areBeautiful;
    }

    public void setAreBeautiful(Boolean areBeautiful) {
        this.areBeautiful = areBeautiful;
    }
}
