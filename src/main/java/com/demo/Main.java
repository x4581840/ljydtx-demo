package com.demo;

import com.google.common.collect.Lists;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000; //iterations迭代次数
    private static final int KEY_LENGTH = 256;

    private Main() {}

    public static void objPoolTest() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2\t" + (i1 == i2));
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
        System.out.println("i4=i5\t" + (i4 == i5));
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));

        System.out.println();
    }

    public static void main(String[] args) {

//        objPoolTest();

        /*final Demo demo = new Demo(1);
        System.out.println(demo.getCount());
        demo.setCount(2);
        System.out.println(demo.getCount()); //可以改变count的值

        //demo = new Demo(3); 会报错

        int t1=57;
        int t2=67;
        int t3=124;
        int t4=124;

        Integer i1=new Integer(t1);
        Integer i2=new Integer(t2);
        Integer i3=new Integer(t3);
        Integer i4=new Integer(t4);


        Boolean ri1 = i1.equals(i2); // false
        Boolean ri2 = i3.equals(i1+i2); // true
        Boolean ri3 = i3.equals(i4); // true

        System.out.println("/n/n-----【i1.equals(i2)】"+ri1+"/n-----【i3.equals(i1+i2)】"+ri2+"/n-----【i3.equals(i4)】"+ri3);

        String st1="wasiker ";
        String st2="is super man";
        String st3="wasiker is super man";
        String st4="wasiker is super man";

        Boolean b1=(st1==st2); // false
        Boolean b2=(st1+st2)==st3; // false
        Boolean b3=(st3==st4); // true

        System.out.println("/n/n-----【st1==st2】"+b1+"/n-----【(st1+st2)==st3】"+b2+"/n-----【st3==st4】"+b3);*/

//        test1();

        /*
        String salt = getSalt(20);
        String password = "LONGJIANYONG_333";
        String res = generateSecurePassword(password, salt);
        System.out.println(res);
        System.out.println(res.length());*/

        /*List<Student> stus = Lists.newArrayList(new Student(1,"tom"),new Student(2,"tom"),new Student(null,"tom"));
        List<Integer> ids = stus.stream().map(Student::getId).collect(Collectors.toList());
        ids.removeAll(Collections.singletonList(null));
        System.out.println(ids.size());*/

        /*Float f = 12345678.43f;
        System.out.println(f.toString());
        System.out.println(String.valueOf(f));
        String res = f.toString();
        System.out.println(Float.parseFloat(res)/10);*/

        /*double a=2.8d;
        float a1=2.8f;
        double a2=a1;
        System.out.println("a1==a2:"+(a1==a2));
        System.out.println("a==a2:"+(a==a2));
        System.out.println(a1);
        System.out.println(a2);*/

        /*double a=2.8d;
        float a1=2.8f;
        double a2=Double.valueOf(String.valueOf(a1));
        System.out.println("a1==a2:"+(a1==a2));//java自动将a1转为double
        System.out.println("a==a2:"+(a==a2));
        System.out.println(a1);
        System.out.println(a2);

        Float s = 23422.23f;
        Double s1 = Double.valueOf(s);
        System.out.println(s1);

        Double s2 = Double.valueOf(String.valueOf(s));
        System.out.println(s2);*/
/*
        Float f = -222222.22f;
        System.out.println(50.32%10000);

        BigDecimal b = new BigDecimal(f.toString());

        BigDecimal b1 = new BigDecimal("13");
        System.out.println(b1.divide(new BigDecimal(100)));

        System.out.println(b.multiply(b1.divide(new BigDecimal(100))));


        Double d = Double.valueOf(String.valueOf(f));

        BigDecimal b2 = new BigDecimal(d.toString());
        BigDecimal r = b2.multiply(b1.divide(new BigDecimal(100)));
        System.out.println(r);

        System.out.println(r.doubleValue());*/

        /*List<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(1l);
        list.add(2l);
        System.out.println(list.size());
        list = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(res.size());
        System.out.println(list.size());*/

        System.out.println(System.currentTimeMillis());

        List<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        System.out.println(list);
        list.remove("a");
        System.out.println(list);

    }

    static class Student{

        private Integer id;
        private String name;

        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        System.out.println("securePassword:"+securePassword.length);
        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }


    public static void test1() {
        String str = "abcdef1234093ABC/}];'";

        char[] chars = str.toCharArray();
        for(char c : chars) {
            System.out.print(c);
        }
        System.out.println();
        Arrays.fill(chars, Character.MIN_VALUE); //把chars都填充为字符最小的值

        System.out.println(Character.MIN_VALUE);
        System.out.println('\u0000');
        System.out.println( );
        for(char c : chars) {
            System.out.print(c);
        }

        System.out.println("=======");
        byte[] bytes = str.getBytes();
        for(byte b : bytes) {
            System.out.println(b);
        }

    }

    static class Demo {
        private int count;

        public Demo(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
