package com.demo.rectangle.cross;

/**
 * 表示excel的sheet中的单元格的区域
 */
public class SheetRegion {
    // 开始行号
    private int startRow;
    // 结束行号
    private int endRow;
    // 开始列号
    private int startCol;
    // 结束列号
    private int endCol;

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }
}
