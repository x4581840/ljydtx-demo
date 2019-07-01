package com.demo.file;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class FileClient {

    private static final String FILE_URL = "";

    public void getCommonFile() throws IOException {
        //从服务器请求文件流，具体代码就不贴了
        CloseableHttpResponse response = HttpSender.toPost(FILE_URL, null);
        InputStream inputStream = response.getEntity().getContent();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
        int len = 0;
        while ((len = inputStream.read(buff)) > 0) {
            //把从服务端读取的文件流保存到ByteArrayOutputSteam中
            byteArray.write(buff, 0, len);
            byteArray.flush();
        }
        inputStream.close();
        response.close();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(byteArray.toByteArray()), "utf-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public void getZipFile() throws IOException {
        //从服务器请求文件流，具体代码就不贴了
        CloseableHttpResponse response = HttpSender.toPost(FILE_URL, null);
        InputStream inputStream = response.getEntity().getContent();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
        int len = 0;
        while ((len = inputStream.read(buff)) > 0) {
            //把从服务端读取的文件流保存到ByteArrayOutputSteam中
            byteArray.write(buff, 0, len);
            byteArray.flush();
        }
        inputStream.close();
        response.close();

        //GZIPInputstream解压文件，然后读取文件
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                        new ByteArrayInputStream(byteArray.toByteArray())), "utf-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void writeFile(ByteArrayOutputStream byteArray) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray.toByteArray());
        BufferedOutputStream outputStream = null;
        byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
        int len = 0;
        try {
            outputStream = new BufferedOutputStream(
                    new FileOutputStream(new File("D:\\testFile\\test1.txt")));
            while ((len = inputStream.read(buff)) > 0) {
                //写文件
                outputStream.write(buff, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
