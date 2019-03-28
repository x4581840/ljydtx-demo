package com.demo.suanfa.str_01;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SubStringTest {
    public static void main(String[] args) {
        /*String s = "abcdaAadfaljdflakd;fajgljeoijaiosdfjioasjfifal;sdflakfjkljoijios" +
                "asdf;asdfj;4oe[erg0qe0-iiradvl,jadgd;ldlongjianyongls;dfouueie34930402-2-2-dl" +
                "asdfasijofpqeurabcdefghijklmnopqrstuvwxyz";*/
        String s = "abcddal";
        long begin = System.currentTimeMillis();
        System.out.println(lengthOfLongestSubstring1(s));
        System.out.println("cost:"+(System.currentTimeMillis()-begin));
    }

    public static int lengthOfLongestSubstring1(String s) {
        if(StringUtils.isBlank(s)) {
            return 0;
        }
        int max=0;

        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if(StringUtils.isBlank(s)) {
            return 0;
        }
        int L=0,R=0;
        int max=0;
        String tempStr="";
        Map<Character, Integer> map = new HashMap<>();
        while(R < s.length() && L <= R) {
            char c = s.charAt(R);
            //collection.get(c)<L，c可能是在L左边
            if(!map.containsKey(c) || map.get(c) < L) {
                map.put(c,R);
                R++;
            }else {
                if((R-L)>max) {
                    tempStr = s.substring(L, R);
                }
                max = Math.max(max, R-L);
                L = map.get(c) + 1;
                map.put(c, R);
                R++;
            }
        }
        if((R-L)>max) {
            tempStr = s.substring(L, R);
        }
        System.out.println("longest substring is: "+tempStr);
        return Math.max(R-L, max);
    }
}
