package com.xidian.blog.utils;

import java.util.Random;

/**
 * @author 米
 * @date 2020/4/3
 */
public class RandomUtil {
    public static int returnRandomNum(int min,int max){
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}
