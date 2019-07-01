package com.demo.file;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@RestController
@RequestMapping("/fileService")
public class FileServer {

    @RequestMapping(value = "/getCommonFile", method = RequestMethod.POST)
    public void getCommonFile(HttpServletRequest request, HttpServletResponse response) {
        BufferedInputStream buffInputStream = null;
        OutputStream outputStream = null;
        try {
            buffInputStream = new BufferedInputStream(new FileInputStream(new File("D:\\testFile\\test.txt")));
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
            int len = 0;
            while ((len = buffInputStream.read(buff)) > 0) {
                //把文件流写入到response的输出流中，供请求端请求
                outputStream.write(buff, 0, len);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffInputStream != null) {
                    buffInputStream.close();
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

    @RequestMapping(value = "/getZipFile", method = RequestMethod.POST)
    public void getZipFile(HttpServletRequest request, HttpServletResponse response) {
        BufferedInputStream buffInputStream = null;
        OutputStream outputStream = null;
        try {
            buffInputStream = new BufferedInputStream(new FileInputStream(new File("D:\\testFile\\test.gz")));
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
            int len = 0;
            while ((len = buffInputStream.read(buff)) > 0) {
                //把文件流写入到response的输出流中，供请求端请求
                outputStream.write(buff, 0, len);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffInputStream != null) {
                    buffInputStream.close();
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

    @RequestMapping(value = "/getCommontToZipFile", method = RequestMethod.POST)
    public void getCommontToZipFile(HttpServletRequest request, HttpServletResponse response) {
        BufferedInputStream buffInputStream = null;
        OutputStream outputStream = null;
        GZIPOutputStream zipOut = null;
        try {
            outputStream = response.getOutputStream();
            buffInputStream = new BufferedInputStream(new FileInputStream(new File("D:\\testFile\\test.txt")));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //压缩文件
            zipOut = new GZIPOutputStream(byteArrayOutputStream);
            byte[] buff = new byte[1024 * 1024]; //如果是稍微大的文件，这里配置的大一些
            int len = 0;
            while ((len = buffInputStream.read(buff)) > 0) {
                //把文件流写入到byteArrayOutputStream里面
                zipOut.write(buff, 0, len);
                zipOut.flush();
            }
            outputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffInputStream != null) {
                    buffInputStream.close();
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
            try {
                if (zipOut != null) {
                    zipOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/getMultiZipFile", method = RequestMethod.POST)
    public void getMultiZipFile(HttpServletRequest request, HttpServletResponse response) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        List<File> files = new ArrayList<File>();
        File file = new File("D:\\testFile\\test1.gz");
        files.add(file);
        file = new File("D:\\testFile\\test2.gz");
        files.add(file);

        for (File file1 : files) {
            readDetailDataToByteArray(byteArray, file1);
        }
    }

    public static void readDetailDataToByteArray(ByteArrayOutputStream byteArray, File file) {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[1024 * 1024];
            int j;
            while ((j = bufferedInputStream.read(b)) > 0) {
                byteArray.write(b, 0, j);
                byteArray.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (Exception e) {

            }
        }
    }
}
