package com.technology.verify;

public class Test {
    /**
     * char和二进制转换测试
     * @param args
     */
    public static void main(String[] args) {
        String binary = toBinary("he");
        System.out.println(binary);
        String string = toString(binary);
        System.out.println(string);
    }
    /**
     *   把字符串转成字符数组
     */
    public static String toBinary(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            //toBinaryString(int i)返回变量的二进制表示的字符串 //toHexString(int i) 八进制 //toOctalString(int i) 十六进制
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    public static String toString(String binary) {
        String[] tempStr = binary.split(" ");
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    //将二进制字符串转换成int数组
    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    //将二进制转换成字符
    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }
}
