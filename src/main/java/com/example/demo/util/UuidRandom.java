package com.example.demo.util;

import java.util.Random;

public class UuidRandom {
    public static String randomHexString()  {
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<16;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
