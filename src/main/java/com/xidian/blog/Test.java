package com.xidian.blog;

import java.util.Random;

/**
 * @author 米
 * @date 2020/3/23
 */
public   class Test {

    public static void main(String[] args) {

        int max=5;
        int min=0;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);
    }
}
