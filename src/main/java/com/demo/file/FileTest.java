package com.demo.file;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileTest {

    private static Integer code = 0;
    private static Integer codeComments = 0;
    private static Integer codeBlank = 0;

    public static void main(String[] args) {
        /*//获取netty文件下所有的文件
        List<File> allFile = getallfile("D:\\code\\netty");
        allFile.forEach((file) -> {
            System.out.println(file.getPath());
        });*/

        //统计代码行数
        File file = new File("D:\\code\\netty");
        factFiles(file);
        System.out.println("代码行数" + code);
        System.out.println("空白行数" + codeBlank);
        System.out.println("注释行数" + codeComments);

    }

    public static void factFiles(File file) {
        BufferedReader br = null;
        String s = null;

        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f : files) {
                factFiles(f);
            }
        } else {
            try {
                br = new BufferedReader(new FileReader(file));
                boolean comm = false;
                while((s = br.readLine()) != null) {
                    if(s.startsWith("/*") && s.endsWith("*/")) {
                        codeComments++;
                    } else if(s.trim().startsWith("//")) {
                        codeComments++;
                    }/* else if(s.trim().startsWith("*")) {
                        codeComments++;
                    }*/ else if(s.trim().startsWith("@Override")) {
                        codeComments++;
                    } else if(s.startsWith("/*") && !s.endsWith("*/")) {
                        codeComments++;
                        comm = true;
                    } else if(!s.startsWith("/*") && s.endsWith("*/")) {
                        codeComments++;
                        comm = false;
                    } else if(comm) {
                        codeComments++;
                    } else if(s.trim().length() < 1) {
                        codeBlank++;
                    } else {
                        code++;
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Title: getallfile
     * @Description: 获取当前路径下的所有文件
     * @author ruby
     * @return void 返回类型
     * @date 2018年1月4日 下午2:15:38 @throws
     */
    public static List<File> getallfile(String path) {
        List<File> allfilelist = new ArrayList<File>();
        return getallfile(new File(path), allfilelist);
    }

    /**
     * @Title: getallfile
     * @Description: 获取当前文件夹下的所有文件
     * @author ruby
     * @return void 返回类型
     * @date 2018年1月4日 下午2:15:38 @throws
     */
    public static List<File> getallfile(File file, List<File> allfilelist) {
        if (file.exists()) {
            //判断文件是否是文件夹，如果是，开始递归
            if (file.isDirectory()) {
                File f[] = file.listFiles();
                for (File file2 : f) {
                    getallfile(file2, allfilelist);
                }
            } else {
                allfilelist.add(file);
            }
        }
        return allfilelist;
    }
}
