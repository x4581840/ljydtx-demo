package com.demo.mianshiti;

import java.util.HashSet;
import java.util.Random;

public class GenerRandom {

    public int generOneRandomLessTen() {
        Random random = new Random();
        int result = random.nextInt(10);
        System.out.println(result);
        return result;
    }

    /**
     * 生成四位不相同的随机数(注意首位不能为0)
     * @return
     */
    public String generFourRandom() {
        String result = "";
        HashSet<Integer> sets = new HashSet<>();
        int i = 0;
        while (result.length() < 4) {
            int r = generOneRandomLessTen();
            if(i == 0 && r == 0) {
                i++;
                continue;
            }
            if(!sets.contains(r)) {
                result += r;
                sets.add(r);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GenerRandom generRandom = new GenerRandom();
        System.out.println(generRandom.generFourRandom());
    }
}
