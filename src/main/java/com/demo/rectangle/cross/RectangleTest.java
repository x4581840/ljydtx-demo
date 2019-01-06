package com.demo.rectangle.cross;

public class RectangleTest {
    public static void main(String[] args) {

        SheetRegion sheetRegion = new SheetRegion();
        sheetRegion.setStartRow(10);
        sheetRegion.setEndRow(20);
        sheetRegion.setStartCol(10);
        sheetRegion.setEndCol(20);

        SheetRegion reqRegion = new SheetRegion();
        reqRegion.setStartRow(15);
        reqRegion.setEndRow(25);
        reqRegion.setStartCol(5);
        reqRegion.setEndCol(15);
        System.out.println(isCross(reqRegion, sheetRegion));

        SheetRegion reqRegion1 = new SheetRegion();
        reqRegion1.setStartRow(5);
        reqRegion1.setEndRow(15);
        reqRegion1.setStartCol(5);
        reqRegion1.setEndCol(15);
        System.out.println(isCross(reqRegion1, sheetRegion));


    }


    /**
     * 判断请求的长方形单元格区域是否和目标区域重叠
     * 特别说明，这里不能直接套用两个矩形相交的算法，因为这里是excel，一个单元格也
     * 可以代表一个区域，所以必须加上等于的条件
     * @param reqReg 请求区域
     * @param reg  已有区域
     * @return
     */
    private static boolean isCross(SheetRegion reqReg, SheetRegion reg) {
        return reqReg.getEndRow()>=reg.getStartRow() && reg.getEndRow()>=reqReg.getStartRow()
                && reqReg.getEndCol()>=reg.getStartCol() && reg.getEndCol()>=reqReg.getStartCol();
    }
}
