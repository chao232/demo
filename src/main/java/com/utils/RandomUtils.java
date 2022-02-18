package com.utils;

import java.util.Random;

public class RandomUtils {

    /**
     * 生成指定长度的随机字符串
     *
     * @param strLength
     * @return
     */
    public static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    public static char getRandomCharacter(char ch1, char ch2) {
        return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));//因为random<1.0，所以需要+1，才能取到ch2
    }

    public static char getRandomLowerCaseLetter() {
        return getRandomCharacter('a', 'z');
    }

    public static char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    public static char getRandomDigitLetter() {
        return getRandomCharacter('0', '9');
    }

    public static char getRandomCharacter() {
        return getRandomCharacter('\u0000', '\uFFFF');
    }

    @SuppressWarnings("static-access")
    public static String getRandom6Num() {
        String s = "1234567890";
        Random r = new Random();
        String result = "";
        for (int i = 0; i < 14; i++) {
            int n = r.nextInt(8);
            result += s.valueOf(n);
        }
        return result;
    }

    //指定位数，获取随机数,包含字母数字
    public static String getRandom(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //指定位数，获取随机数,只包含数字
    public static String getRandom1(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成知指定长度随机数字符串
     *
     * @param numLength
     * @return
     */
    public static String randomNum(int numLength) {
        Random random = new Random();
        StringBuilder finalStr = new StringBuilder();
        while (numLength > 0) {
            int beginNum = random.nextInt(9) + 1;
            if (numLength >= 9) {
                finalStr.append((int) ((Math.random() + beginNum) * Math.pow(10, 9 - 1)));
                numLength -= 9;
            } else {
                finalStr.append((int) ((Math.random() + beginNum) * Math.pow(10, numLength - 1)));
                numLength = 0;
            }
        }
        return finalStr.toString();
    }

    public static int randomNum(int begin, int end) {
        return (int) (Math.random() * end + begin);
    }
}