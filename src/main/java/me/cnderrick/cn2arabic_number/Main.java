package me.cnderrkc.cn2arabic_number;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Main {

  private static final Map<Character, Integer> CHINESE_TO_NUMBER_LIST =
            ImmutableMap.<Character, Integer>builder().put('零', 0).put('一', 1).put('二', 2).put('两', 2).put('俩', 2).put('三', 3)
                    .put('仨', 3).put('四', 4).put('五', 5).put('六', 6).put('七', 7).put('八', 8).put('九', 9).put('十', 10)
                    .put('百', 100).put('千', 1000).put('万', 10000).put('亿', 100000000).build();

  public int translateChineseNumberToArabicNumber(String str) {
        int code = 0;
        char[] levelStrArray = str.toCharArray();
        int beforeNum = -1;
        for (char temp : levelStrArray) {
            // 过滤汉字型数字，转换为阿拉伯数据
            Integer charNum = CHINESE_TO_NUMBER_LIST.get(temp);
            if (null == charNum) {
                log.error("Unknown Chinese Character to Arabic Number char is {}", temp);
            } else {
                if (charNum >= 10) {
                    if (beforeNum == -1) {
                        if (code == 0) {
                            code += charNum;
                        } else {
                            code *= charNum;
                        }
                    } else {
                        if (code > charNum) {
                            code += charNum * beforeNum;
                            beforeNum = -1;
                        } else if (code < charNum) {
                            code = (code + beforeNum) * charNum;
                            beforeNum = -1;
                        }
                    }
                } else {
                    beforeNum = charNum;
                }
            }
        }
        if (beforeNum > 0) {
            code += beforeNum;
        }
        return code;
    }
    
    public static void main(String[] args) {
        Main a = new Main();
        System.print.out(a.translateChineseNumberToArabicNumber("三千七百五十八亿零二百三十六万八千四百二十五"));
    }
}
