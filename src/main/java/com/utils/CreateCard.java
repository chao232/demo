package com.utils;

import cn.hutool.core.util.StrUtil;

public class CreateCard {
    private static final int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    public static void main(String[] args) {
        System.out.println(getName());
    }
    public static String getrCeateCard(){
        StringBuffer sb=new StringBuffer();
        sb.append(RandomUtils.getRandom1(6)).append("19771203").append(RandomUtils.getRandom1(3));
        String card=sb.toString();
        sb.append(getCheckCode18(card));
        return sb.toString();
    }
    private static char getCheckCode18(String code17) {
        int sum = getPowerSum(code17.toCharArray());
        return getCheckCode18(sum);
    }
    static int getPowerSum(char[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                iSum += Integer.parseInt(String.valueOf(iArr[i])) * power[i];
            }
        }
        return iSum;
    }
    private static char getCheckCode18(int iSum) {
        switch (iSum % 11) {
            case 10:
                return '2';
            case 9:
                return '3';
            case 8:
                return '4';
            case 7:
                return '5';
            case 6:
                return '6';
            case 5:
                return '7';
            case 4:
                return '8';
            case 3:
                return '9';
            case 2:
                return 'x';
            case 1:
                return '0';
            case 0:
                return '1';
            default:
                return StrUtil.C_SPACE;
        }
    }

    public static String getName(){
        String name = "";
        int chineseNameNum = 3;
        for(int i=1; i<=chineseNameNum;i++){
            name += getChinese();
        }
        return name;
    }
    private static String getChinese(){
        String chinese = "";
        int i = (int)(Math.random()*40 + 16);
        int j = (int)(Math.random()*94 + 1);
        if(i == 55){
            j = (int)(Math.random()*89 + 1);
        }
        byte[] bytes = {(byte) (i+160),(byte) (j+160)};
        try {
            chinese =  new String(bytes, "gb2312");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chinese;
    }
}
