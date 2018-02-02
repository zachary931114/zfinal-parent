package me.zhoubl.zfinal.common.utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @author zhoubl
 */
public class UtilRandom {

    private final static SecureRandom srd = new SecureRandom();
    private static final String INT = "0123456789";
    private static final String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomStr(int length) {
        return random(length, RndType.STRING);
    }

    public static String randomInt(int length) {
        return random(length, RndType.INT);
    }

    public static String randomAll(int length) {
        return random(length, RndType.ALL);
    }

    public static String randomTlInt(int length) {
        return random(length, RndType.TLINT);
    }


    private static String random(int length, RndType rndType) {
        StringBuilder s = new StringBuilder();
        char num = 0;
        for (int i = 0; i < length; i++) {
            if (rndType.equals(RndType.INT))
                num = INT.charAt(srd.nextInt(INT.length()));// 产生数字0-9的随机数
            else if (rndType.equals(RndType.STRING))
                num = STR.charAt(srd.nextInt(STR.length()));// 产生字母a-z的随机数
            else if (rndType.equals(RndType.TLINT))
                num = INT.charAt(ThreadLocalRandom.current().nextInt(9));
            else {
                num = ALL.charAt(srd.nextInt(ALL.length()));
            }
            s.append(num);
        }
        return s.toString();
    }

    public static enum RndType {
        INT, STRING, ALL, TLINT
    }

    public static void main(String[] args) {
        System.out.println(randomInt(8));
    }
}
