package com.uic.oole.utility;

import java.util.Random;

/**
 * random string generator class
 */
public class RandomStrGen {
    /**
     * randomly generates a string
     * @return
     */
    public static String randomStrGenerator(){

        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 3; i < 9; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}