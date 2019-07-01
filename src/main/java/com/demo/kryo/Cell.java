package com.demo.kryo;

import java.io.Serializable;
import java.util.List;

public class Cell implements Serializable {
    private String name;
    private int time;
    List<SubCell> subCells;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    private String test;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<SubCell> getSubCells() {
        return subCells;
    }

    public void setSubCells(List<SubCell> subCells) {
        this.subCells = subCells;
    }
}
