package com.demo.mianshiti;

import org.apache.commons.lang3.StringUtils;

public class InvertStr {

    public static void main(String[] args) {
        String originStr = "i love you";
        //逆置 字符串，底层用的是new StringBuilder(str).reverse
        System.out.println(StringUtils.reverse(originStr));

        InvertStr invertStr = new InvertStr();
        invertStr.invertStr(originStr);
    }

    /**
     * 不使用中间变量，逆置字符串
     * @param originStr
     * @return
     */
    public String invertStr(String originStr) {
        String result = "";

        for(int i=originStr.length()-1;i>=0;i--){
            System.out.print(originStr.charAt(i));
        }


        return result;
    }


}
