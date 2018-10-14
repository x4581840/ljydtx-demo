package com.demo.testmain;

import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ReadFileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\test.rar");
        //cost: 16毫秒
        //bufferedRandomAccessFileReadLine(file, "utf-8",0l, 500000);
        //cost: 708毫秒
        //testFileInputStream(file);
        //cost: 332
        //testBufferInputStream(file);

        readZip(file);
    }

    /**
     * 读取zip文件内容
     */
    private static void readZip(File file) {
        long begin = System.currentTimeMillis();
        ZipInputStream zipIn = null;
        ZipOutputStream zipOut = null;
        try {
            zipIn = new ZipInputStream(new FileInputStream(file));
            zipOut = new ZipOutputStream(new FileOutputStream("D:\\test_copy1.rar"));
            int len = 0;
            byte[] buf = new byte[1024];
            while((len=zipIn.read(buf))!=-1){
                //System.out.println(new String(buf,0,len));
                zipOut.write(buf);
                zipOut.flush();
            }
            zipOut.close();
            zipIn.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("cost:"+(System.currentTimeMillis()-begin));
    }

    public static void testBufferInputStream(File file) throws IOException {

        if(!file.exists()){
            throw new RuntimeException("要读取的文件不存在");
        }
        long begin = System.currentTimeMillis();
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        FileOutputStream fos = new FileOutputStream(new File("D:\\test_copy.pdf"));
        int len = 0;
        byte[] buf = new byte[1024];
        while((len=fis.read(buf))!=-1){
            //System.out.println(new String(buf,0,len));
            fos.write(buf);
            fos.flush();
        }
        fos.close();
        //关资源
        fis.close();
        System.out.println("cost:"+(System.currentTimeMillis()-begin));
    }

    public static void testFileInputStream(File file) throws IOException {

        if(!file.exists()){
            throw new RuntimeException("要读取的文件不存在");
        }
        long begin = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(file);
        //FileOutputStream fos = new FileOutputStream(new File("D:\\test_copy.pdf"));
        int len = 0;
        byte[] buf = new byte[1024];
        while((len=fis.read(buf))!=-1){
            //System.out.println(new String(buf,0,len));
            //fos.write(buf);
            //fos.flush();
        }
        //fos.close();
        //关资源
        fis.close();
        System.out.println("cost:"+(System.currentTimeMillis()-begin));
    }

    /**
     * 通过BufferedRandomAccessFile读取文件,推荐，
     * 通过BufferedRandomAccessFile读取文件是网上网友实现的
     *
     * @param file     源文件
     * @param encoding 文件编码
     * @param pos      偏移量
     * @param num      读取量
     * @return pins文件内容，pos当前偏移量
     */
    public static Map<String, Object> bufferedRandomAccessFileReadLine(File file, String encoding, long pos, int num) {
        Map<String, Object> res = Maps.newHashMap();
        List<String> pins = Lists.newArrayList();
        res.put("pins", pins);
        BufferedRandomAccessFile reader = null;
        long begin = System.currentTimeMillis();
        try {
            reader = new BufferedRandomAccessFile(file, "r");
            reader.seek(pos);
            for (int i = 0; i < num; i++) {
                String pin = reader.readLine();
                if (StringUtils.isBlank(pin)) {
                    break;
                }
                pins.add(new String(pin.getBytes("8859_1"), encoding));
            }
            res.put("pos", reader.getFilePointer());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        System.out.println("cost:"+(System.currentTimeMillis()-begin));
        return res;
    }
}
