package com.windmajor.question;

import java.util.Arrays;
import java.util.HashSet;

public class Solution_801 {

    /* 820.单词的压缩编码 方法一：存储后缀*/
    public int minimumLengthEncoding(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        int result = 0;
        for (String word : set) {
            result += word.length() + 1;
        }
        return result;
    }
}
