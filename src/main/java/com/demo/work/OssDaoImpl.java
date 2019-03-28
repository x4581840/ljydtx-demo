package com.demo.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class OssDaoImpl implements DAO {

    private static final Logger logger = LoggerFactory.getLogger(OssDaoImpl.class);

    private static final String HEADER_PREFIX = "header";
    private static final String DIMR_PREFIX = "dimr";
    private static final String CONTENT_PREFIX = "content";

    private static final String UNDERLINE = "_";

    private OssProxy ossProxy;

    @Override
    public List<TargetCell> analyzeExcelHeaderFromFile(long worksheetId, int branchVersion, int changeVersion) throws Exception {
        String ossFileKey = getHeaderOssKey(worksheetId, branchVersion, changeVersion);
        List<DownLoadResult> downloadResults = ossProxy.download(Arrays.asList(ossFileKey));
        if(CollectionUtils.isEmpty(downloadResults) || !downloadResults.get(0).isSuccess()) {
            return null;
        }
        List<TargetCell> result = new ArrayList<>();
        List<String> datas = downloadResults.get(0).getDatas();
        for(int i=0;i<datas.size();i++) {
            JSONArray jsonArray = JSONArray.parseArray(datas.get(i));
            for(int j=0;j<jsonArray.size();j++) {
                JSONObject jsonObject = (JSONObject)jsonArray.get(j);
                if(jsonObject == null || jsonObject.toString().equals("{}")) {
                    continue;
                }
                TargetCell cell = jsonObject.toJavaObject(TargetCell.class);
                cell.setRow(i);
                cell.setCol(j);
                result.add(cell);
            }
        }
        return result;
    }

    @Override
    public List<TargetCell> analyzeExcelSerialFromFile(long worksheetId, int branchVersion, int changeVersion,
                                                    int startRow, int endRow, int rowOffset, int colOffset, String type) throws Exception {
    	List<TargetCell> result = new ArrayList<>();
        //得到所有的分割的文件的文件名称信息
    	List<String> cellFileNameLists = new ArrayList<>();
    	//得到行维文件或者内容文件的文件名称上面的startrow和endrow信息
    	Map<String, Pair<Integer, Integer>> cellFileNameStartEndRowMap = getFileNameStartEndRow(cellFileNameLists, type);
        //根据startRow和endRow算出要读哪几个文件,以及要读的内容在文件中的起始位置和结束位置
    	Map<String, Pair<Integer, Integer>> cellFileContentStartEndRowMap = getFileContentStartAndEndRow(cellFileNameStartEndRowMap, startRow, endRow);
    	//下载所需的文件
    	List<DownLoadResult> downLoadResults = ossProxy.download(new ArrayList<>(cellFileContentStartEndRowMap.keySet()));
    	//读出需要的单元格
    	downLoadResults.forEach(downLoadResult -> {
    		Pair<Integer, Integer> cellFileContentStartEndPair = cellFileContentStartEndRowMap.get(downLoadResult.getKey());
    		int i = cellFileNameStartEndRowMap.get(downLoadResult.getKey()).getLeft();
    		for(String line : downLoadResult.getDatas()) {
    			if(i == cellFileContentStartEndPair.getLeft() && i <= cellFileContentStartEndPair.getRight()) {
    				JSONArray jsonArray = JSONArray.parseArray(line);
    	            for(int j=0;j<jsonArray.size();j++) {
    	                JSONObject jsonObject = (JSONObject)jsonArray.get(j);
    	                if(jsonObject == null || jsonObject.toString().equals("{}")) {
    	                    continue;
    	                }
    	                TargetCell cell = jsonObject.toJavaObject(TargetCell.class);
    	                cell.setRow(i+rowOffset);
    	                cell.setCol(j+colOffset);
    	                result.add(cell);
    	            }
    			}
    			i++;
    		}
    	});
        return result;
    }
    
    @Override
	public void writeCellsToFile(long worksheetId, int branchVersion, int changeVersion, List<TargetCell> cells, int colOffset)
			throws Exception {
    	//得到所有的分割的文件的文件名称信息
    	List<String> cellFileNameLists = new ArrayList<>();
    	//内容文件的文件名称上面的startrow和endrow信息
    	Map<String, Pair<Integer, Integer>> cellFileNameStartEndRowMap = getFileNameStartEndRow(cellFileNameLists, "CONTENT");
    	//得到要写入的文件和要写入的位置,map是文件中的行号和列号，跟excel中的对应，具体处理的时候，要注意offset
    	Map<String, Map<Integer, Map<Integer, TargetCell>>> insertPositionMap = getInsertPosition(cellFileNameStartEndRowMap, cells);
    	List<DownLoadResult> cellFiles = ossProxy.download(new ArrayList<>(insertPositionMap.keySet()));
    	Map<String, List<String>> uploadFileContent = new HashMap<>();
    	cellFiles.forEach(downLoadResult -> {
    		int i = cellFileNameStartEndRowMap.get(downLoadResult.getKey()).getLeft();
    		Map<Integer, Map<Integer, TargetCell>> positionMap = insertPositionMap.get(downLoadResult.getKey());
    		List<String> datas = downLoadResult.getDatas();
    		for(int j=0;j<datas.size();j++) {
    			if(positionMap.containsKey(i)) {
    				JSONArray jsonArray = JSONArray.parseArray(datas.get(j));
    				for(Integer col : positionMap.get(i).keySet()) {
    					jsonArray.set(col-colOffset, positionMap.get(i).get(col));
    				}
    	            datas.set(j, jsonArray.toJSONString());
    			}
    			i++;
    		}
    		uploadFileContent.put(downLoadResult.getKey(), datas);
    	});
	}

    private Long getCellIndex(int row, int col) {
        return ((long)row << 32) + (long)col;
    }
    
  //得到要写入的文件和要写入的位置,map是文件中的行和列，跟excel中的对应，具体处理的时候，要注意offset
    private Map<String, Map<Integer, Map<Integer, TargetCell>>> getInsertPosition(Map<String, Pair<Integer, Integer>> cellFileNameStartEndRowMap, List<TargetCell> cells) {
    	Map<String, Map<Integer, Map<Integer, TargetCell>>> result = new HashMap<>();
    	cellFileNameStartEndRowMap.forEach((key,value) -> {
    		Map<Integer, Map<Integer, TargetCell>> rowMap = new HashMap<>();
    		cells.forEach(cell -> {
    			if(value.getLeft() <= cell.getRow() && cell.getRow() <= value.getRight()) {
    				Map<Integer, TargetCell> colMap = rowMap.computeIfAbsent(cell.getRow(), k -> new HashMap<Integer, TargetCell>());
    				colMap.put(cell.getCol(), cell);
    			}
    		});
    		if(!CollectionUtils.isEmpty(rowMap)) {
    			result.put(key, rowMap);
    		}
    	});
    	return result;
    }
    
    //得到要写入的文件和要写入的位置,pair是文件中的行和列，跟excel中的对应，具体处理的时候，要注意offset
    /*private Map<String, List<Pair<Integer, Integer>>> getInsertPosition(Map<String, Pair<Integer, Integer>> cellFileNameStartEndRowMap, List<TargetCell> cells) {
    	Map<String, List<Pair<Integer, Integer>>> result = new HashMap<>();
    	cellFileNameStartEndRowMap.forEach((key,value) -> {
    		List<Pair<Integer, Integer>> positions = result.computeIfAbsent(key, k -> new ArrayList<>());
    		cells.forEach(cell -> {
    			if(value.getLeft() <= cell.getRow() && cell.getRow() <= value.getRight()) {
    				positions.add(Pair.of(cell.getRow(), cell.getCol()));
    			}
    		});
    	});
    	return result;
    }*/
    
    //得到要写入的文件和要写入的位置,map是文件中的行和列，跟excel中的对应，具体处理的时候，要注意offset
    /*private Map<String, Map<Integer, List<Integer>>> getInsertPosition(Map<String, Pair<Integer, Integer>> cellFileNameStartEndRowMap, List<TargetCell> cells) {
    	Map<String, Map<Integer, List<Integer>>> result = new HashMap<>();
    	cellFileNameStartEndRowMap.forEach((key,value) -> {
    		Map<Integer, List<Integer>> collection = new HashMap<>();
    		cells.forEach(cell -> {
    			if(value.getLeft() <= cell.getRow() && cell.getRow() <= value.getRight()) {
    				List<Integer> colLists = collection.computeIfAbsent(cell.getRow(), k -> new ArrayList<>());
    				colLists.add(cell.getCol());
    			}
    		});
    		if(!CollectionUtils.isEmpty(collection)) {
    			result.put(key, collection);
    		}
    	});
    	return result;
    }*/
    
    //根据参数startRow和endRow，得到要读的文件和文件内容的开始位置和结束位置
    private Map<String, Pair<Integer, Integer>> getFileContentStartAndEndRow(Map<String, Pair<Integer, Integer>> cellFileNameStartAndEndRowMap,
                                                                              int startRow, int endRow) {
    	Assert.assertTrue(startRow <= endRow);
    	Map<String, Pair<Integer, Integer>> result = new HashMap<>();
    	
    	cellFileNameStartAndEndRowMap.forEach((key,value) -> {
    		if(value.getLeft() <= startRow && endRow <= value.getRight()) {
    			result.put(key, Pair.of(startRow, endRow));
    		}
    		if(value.getLeft() <= startRow && startRow <= value.getRight() && value.getRight() < endRow) {
    			result.put(key, Pair.of(startRow, value.getRight()));
    		}
    		if(value.getLeft() <= endRow && endRow <= value.getRight() && value.getLeft() > startRow) {
    			result.put(key, Pair.of(value.getLeft(), endRow));
    		}
    		if(value.getLeft() > startRow && value.getRight() < endRow) {
    			result.put(key, value);
    		}
    	});
    	
    	return result;
    }

    //得到行维文件或者内容文件命名上面的startrow和endrow信息
    private Map<String, Pair<Integer, Integer>> getFileNameStartEndRow(List<String> cellFileNameLists, String type) {
        Map<String, Pair<Integer, Integer>> cellFileStartEndRowMap = new HashMap<>();
        for(String fileName : cellFileNameLists) {
            if(fileName.contains("header") || !fileName.contains(type)) {
                continue;
            }
            if( ("DIMR".equals(type) && !fileName.contains(DIMR_PREFIX)) 
            		|| (("CONTENT".equals(type) && !fileName.contains(CONTENT_PREFIX))) ) {
            	continue;
            }
            String[] splits = fileName.split("_");
            int start = Integer.parseInt(splits[splits.length-2]);
            int end = Integer.parseInt(splits[splits.length-1]);
            cellFileStartEndRowMap.put(fileName, Pair.of(start, end));
        }
        return cellFileStartEndRowMap;
    }
    
    public static void main(String[] args) {
    	List<String> fileNameInfos = new ArrayList<>();
    	fileNameInfos.add("header_333");
    	fileNameInfos.add("dimr_333_0_5");
    	fileNameInfos.add("dimr_333_6_10");
    	fileNameInfos.add("dimr_333_11_16");
    	fileNameInfos.add("dimr_333_17_27");
    	fileNameInfos.add("content_333_0_5");
    	fileNameInfos.add("content_333_6_10");
    	fileNameInfos.add("content_333_11_16");
    	fileNameInfos.add("content_333_17_27");
    	
    	int startRow = 2;
    	int endRow = 2;
    	
    	Map<String, Pair<Integer, Integer>> fileStartEndMap = new HashMap<>();
        for(String fileName : fileNameInfos) {
        	//dimr和content文件按行的切割的比例是一样的，所以只需要得到dimr文件名字的信息就可以了
            if(fileName.contains("header")/* || fileName.equals("content")*/) {
                continue;
            }
            String[] splits = fileName.split("_");
            int start = Integer.parseInt(splits[splits.length-2]);
            int end = Integer.parseInt(splits[splits.length-1]);
            fileStartEndMap.put(fileName, Pair.of(start, end));
        }
        
//        fileStartEndMap.forEach((key,value) -> {
//        	System.out.println("fileStartEndMap=="+key+":"+value.getLeft()+"_"+value.getRight());
//        });
        
        Map<String, Pair<Integer, Integer>> result = new HashMap<>();
        
        fileStartEndMap.forEach((key,value) -> {
    		if(value.getLeft() <= startRow && endRow <= value.getRight()) {
    			result.put(key, Pair.of(startRow, endRow));
    		}
    		if(value.getLeft() <= startRow && startRow <= value.getRight() && value.getRight() < endRow) {
    			result.put(key, Pair.of(startRow, value.getRight()));
    		}
    		if(value.getLeft() <= endRow && endRow <= value.getRight() && value.getLeft() > startRow) {
    			result.put(key, Pair.of(value.getLeft(), endRow));
    		}
    		if(value.getLeft() > startRow && value.getRight() < endRow) {
    			result.put(key, value);
    		}
    	});
        
        result.forEach((key,value) -> {
        	System.out.println("result=="+key+":"+value.getLeft()+"_"+value.getRight());
        });
        
	}

    private String getHeaderOssKey(long worksheetId, int branchVersion, int changeVersion) {
        StringBuffer ossKey = new StringBuffer();
        ossKey.append(worksheetId).append(UNDERLINE).append(branchVersion).append(UNDERLINE)
                .append(changeVersion).append(UNDERLINE);
        return ossKey.toString();
    }

    private String getContentOrDimrOssKey(long worksheetId, int branchVersion, int changeVersion,
                                    int startRow, int endRow) {
        StringBuffer ossKey = new StringBuffer();
        ossKey.append(worksheetId).append(UNDERLINE).append(branchVersion).append(UNDERLINE)
                .append(changeVersion).append(UNDERLINE).append(startRow).append(UNDERLINE)
                .append(endRow).append(UNDERLINE);
        return ossKey.toString();
    }

}
