package com.demo.cross;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

/**
 * 笛卡尔积工具类.
 */
public class CrossUtil {
    public static List<List<Object>> cross(List<List<Object>> crossArgs) {
        // 计算出笛卡尔积行数
        int rows = crossArgs.size() > 0 ? 1 : 0;
        for (List<Object> data : crossArgs) {
            rows *= data.size();
        }
        // 笛卡尔积索引记录
        int[] record = new int[crossArgs.size()];
        List<List<Object>> results = new LinkedList<List<Object>>();
        // 产生笛卡尔积
        for (int i = 0; i < rows; i++) {
            List<Object> row = new LinkedList<Object>();
            // 生成笛卡尔积的每组数据
            for (int index = 0; index < record.length; index++) {
                row.add(crossArgs.get(index).get(record[index]));
            }
            results.add(row);
            crossRecord(crossArgs, record, crossArgs.size() - 1);
        }
        return results;
    }

    /**
     * 产生笛卡尔积当前行索引记录.
     *
     * @param sourceArgs 要产生笛卡尔积的源数据
     * @param record     每行笛卡尔积的索引组合
     * @param level      索引组合的当前计算层级
     */
    private static void crossRecord(List<List<Object>> sourceArgs, int[] record, int level) {
        record[level] = record[level] + 1;
        if (record[level] >= sourceArgs.get(level).size() && level > 0) {
            record[level] = 0;
            crossRecord(sourceArgs, record, level - 1);
        }
    }

    private static class TargetCell {
        private int startRow;
        private int endRow;
        private int startCol;
        private int endCol;

        public TargetCell(int startRow, int endRow, int startCol, int endCol) {
            super();
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCol = startCol;
            this.endCol = endCol;
        }

        @Override
        public String toString() {
            return "TargetCell [startRow=" + startRow + ", endRow=" + endRow + ", startCol=" + startCol + ", endCol=" + endCol
                    + "]";
        }

    }

    private static class DetailData {
        private int start;
        private int end;

        public DetailData(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "DetailData [start=" + start + ", end=" + end + "]";
        }

    }

    public static void main(String[] args) {
    /*List<List<Object>> crossArgs = new ArrayList<List<Object>>();

    List<Object> cells = Lists.newArrayList(new TargetCell(0, 5, 0, 3), new TargetCell(6, 10, 4, 7));

    List<Object> detailData = Lists.newArrayList(new DetailData(0, 100), new DetailData(101, 200));

    crossArgs.add(cells);
    crossArgs.add(detailData);

    List<List<Object>> result = CrossUtils.cross(crossArgs);
    for (List<Object> list : result) {
      TargetCell targetCell = (TargetCell) list.get(0);
      System.out.println(targetCell);
      DetailData detail = (DetailData) list.get(1);
      System.out.println(detail);
    }*/
        List<List<Object>> lists = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<Object> list2 = new ArrayList<>();
        list2.add("d");
        list2.add("e");
        list2.add("f");
        lists.add(list1);
        lists.add(list2);
        List<List<Object>> res = cross(lists);
        res.forEach(list -> {
            System.out.println("size:" + list.size());
            list.forEach(obj -> {
                System.out.println(obj);
            });
        });
    }
}

