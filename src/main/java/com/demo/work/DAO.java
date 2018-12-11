package com.demo.work;

import java.util.List;

public interface DAO {
	
	//解析excel的头文件的单元格，包括页维和列维
	public List<TargetCell> analyzeExcelHeaderFromFile(long worksheetId, int branchVersion, int changeVersion) throws Exception;

	//解析按行连续的单元格
	List<TargetCell> analyzeExcelSerialFromFile(long worksheetId, int branchVersion, int changeVersion, int startRow, int endRow,
			int rowOffset, int colOffset, String type) throws Exception;
	
	//mr写入单元格（只会写入数据文件）
	void writeCellsToFile(long worksheetId, int branchVersion, int changeVersion, List<TargetCell> cells, int colOffset) throws Exception;
}
