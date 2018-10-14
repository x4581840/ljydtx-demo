package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/loadFile")
public class LoadFileController {

    @RequestMapping("loadFile")
    public ByteArrayOutputStream addUser(@RequestBody JSONObject userEntity) throws IOException {
        // 读取文件字节流。
        ByteArrayOutputStream fileStream = new ByteArrayOutputStream(1024);
        FileInputStream file = new FileInputStream("D:/test.txt");

        byte[] readbuff = new byte[1024];
        while(file.read(readbuff) != -1) {
            fileStream.write(readbuff);
        }
        file.close();
        return fileStream;
    }

    @RequestMapping(value = "/getExcel", method = RequestMethod.GET)
    public void createBoxListExcel(HttpServletResponse response) throws Exception {
        //String filePath = "人员数据.xls";
        String fileName = "test.xls";
        //String fileName = new String("人员数据.xls".getBytes(), "ISO-8859-1");
        //设置文件名
        response.addHeader("Content-Disposition", "filename=" + fileName);
        OutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream("D:/test.xls");
        byte[] b = new byte[1024];
        int j;
        while ((j = fileInputStream.read(b)) > 0) {
            outputStream.write(b, 0, j);
        }
        fileInputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("showuser")
    public Object showUser()
    {
        return JSON.toJSONString("hhh");
    }

}
