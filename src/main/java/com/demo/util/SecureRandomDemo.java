package com.demo.util;

import java.security.SecureRandom;

public class SecureRandomDemo {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();

        System.out.println(secureRandom.nextInt());
    }
}
