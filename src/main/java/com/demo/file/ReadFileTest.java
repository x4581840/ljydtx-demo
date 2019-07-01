package com.demo.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileTest {
    public static void main(String[] args) throws IOException {
        //时间： 1900 到 2150之间
        //readFile1("D:\\test.xls", "D:\\testFile\\test.xls");
        //时间：1700左右
        //readFile2("D:\\test.xls", "D:\\testFile\\test.xls");
        //readFile3("D:\\test.xls", "D:\\testFile\\test.xls");
        //readFile4("D:\\test.xls", "D:\\testFile\\test.xls");
        readFile5("D:\\test.xls", "D:\\testFile\\test.xls");
        readFile6("D:\\test.xls", "D:\\testFile\\test.xls");
    }

    public static void readFile1(String path, String targetPath) throws IOException {
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(new File(path)));
        byte[] buff = new byte[1024];
        int len = 0;
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream(new File(targetPath)));
        long begin = System.currentTimeMillis();
        while ((len = bufferedInputStream.read(buff)) != -1) {
            bufferedOutputStream.write(buff, 0, len);
            bufferedOutputStream.flush();
        }
        bufferedOutputStream.close();
        bufferedInputStream.close();
        System.out.println("readFile1 cost:" + (System.currentTimeMillis() - begin));
    }

    public static void readFile2(String path, String targetPath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        FileOutputStream outputStream = new FileOutputStream(new File(targetPath));

        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long begin = System.currentTimeMillis();
        while (true) {
            int eof = inChannel.read(byteBuffer);
            if (eof == -1) break;
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        inChannel.close();
        outChannel.close();
        System.out.println("readFile2 cost:" + (System.currentTimeMillis() - begin));
    }

    public static void readFile3(String path, String targetPath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        FileOutputStream outputStream = new FileOutputStream(new File(targetPath));

        FileChannel inChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long begin = System.currentTimeMillis();
        while (true) {
            int eof = inChannel.read(byteBuffer);
            if (eof == -1) break;
            byteBuffer.flip();
            outputStream.write(byteBuffer.array());
            byteBuffer.clear();
        }

        System.out.println("readFile3 cost:" + (System.currentTimeMillis() - begin));
        inChannel.close();
        outputStream.close();
    }

    public static void readFile4(String path, String targetPath) throws IOException {
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(new File(path)));
        byte[] buff = new byte[1024];
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = bufferedInputStream.read(buff)) != -1) {
        }
        System.out.println("readFile4 cost:" + (System.currentTimeMillis() - begin));
        bufferedInputStream.close();
    }

    public static void readFile5(String path, String targetPath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        byte[] buff = new byte[1024];
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = fileInputStream.read(buff)) != -1) {
        }
        System.out.println("readFile5 cost:" + (System.currentTimeMillis() - begin));
        fileInputStream.close();
    }

    public static void readFile6(String path, String targetPath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));

        FileChannel inChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        long begin = System.currentTimeMillis();
        while (true) {
            int eof = inChannel.read(byteBuffer);
            if (eof == -1) break;
            byteBuffer.flip();
            byteBuffer.clear();
        }

        inChannel.close();
        System.out.println("readFile6 cost:" + (System.currentTimeMillis() - begin));
    }
}
