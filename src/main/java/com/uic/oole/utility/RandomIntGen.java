package com.uic.oole.utility;


import java.util.Random;

/**
 * random integer generator class
 */
public class RandomIntGen {
    /**
     * randomly generates an integer
     * @return
     */
    public static Integer randomIntGenerator(){
        char[] chars = "7689132405".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {

            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        int num=Integer.parseInt(sb.toString());
        return num;
    }
}

