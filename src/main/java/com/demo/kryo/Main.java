package com.demo.kryo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        testKryoArrayList();

    }

    private static void testKryoArrayList() {
        StudentsWrap stusWrap = new StudentsWrap();
        List<Student> stuList = new ArrayList<>();
        Student stu = new Student();
        stu.setId("1");
        stu.setName("tom");
        stu.setAge(18);
        stu.setAddress("hunan");
        stuList.add(stu);
        stu = new Student();
        stu.setId("2");
        stu.setName("lisi");
        stu.setAge(28);
        stu.setName("zhuzhou");
        stuList.add(stu);
        stusWrap.setStus(stuList);

        ISerialize serialize = new StudentsWrapKryoSerialize();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serialize.serialize(stusWrap, out);

        StudentsWrap studentsWrap = serialize.deserialize(StudentsWrap.class, new ByteArrayInputStream(out.toByteArray()));
        System.out.println(studentsWrap.getStus().get(0).getName());
        System.out.println(studentsWrap.getStus().get(0).getAddress());

        System.out.println(studentsWrap.getStus().get(1).getName());
        System.out.println(studentsWrap.getStus().get(1).getAddress());
    }

    private static void testKryoHashMap() {
        Map<String, Cell> cellMap = new HashMap<>();
        Cell cell = new Cell();
        cell.setName("longjianyong");
        cell.setTime(200);
        cell.setTest("test");
        List<SubCell> subCells = new ArrayList<>();
        SubCell subCell = new SubCell();
        subCell.setStyle("style");
        subCell.setFormat("format");
        subCell.setAge(20);
        subCell.setTest("test");
        subCells.add(subCell);
        subCell = new SubCell();
        subCell.setStyle("style1");
        subCell.setFormat("format1");
        subCell.setAge(30);
        cell.setSubCells(subCells);
        cellMap.put("SUB", cell);

        CellsWrap cellsWrap = new CellsWrap();
        cellsWrap.setMaps(cellMap);

//        String s = KryoTest.serializationMap(cellMap, Cell.class);
//        Map<String,Cell> res = KryoTest.deserializationMap(s, Cell.class);
//
//        System.out.println(res.get("SUB").getSubCells().get(0).getStyle());


        KryoCellMapSerializeStrategy serializeStrategy = new KryoCellMapSerializeStrategy();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serializeStrategy.serializeCell(cellsWrap, out);

        System.out.println(out.size());

        CellsWrap cellsWrap1 =
                serializeStrategy.deserializeCell(new ByteArrayInputStream(out.toByteArray()));
        System.out.println(cellsWrap1.getMaps().get("SUB").getSubCells().get(0).getStyle());
        System.out.println(cellsWrap1.getMaps().get("SUB").getSubCells().get(0).getTest());
        System.out.println(cellsWrap1.getMaps().get("SUB").getName());
        System.out.println(cellsWrap1.getMaps().get("SUB").getTest());
    }
 }
