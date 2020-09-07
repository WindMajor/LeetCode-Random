package com.windmajor.question;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution_1001 {

    /* 1047.删除字符串中的所有相邻重复项 解法一：替换函数 replace */
    public String removeDuplicates(String s) {
        HashSet<String> hashSet = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (char i = 'a'; i <= 'z'; i++) {
            builder.setLength(0);
            builder.append(i).append(i);
            hashSet.add(builder.toString());
        }

        int stringLength = -1;
        while (stringLength != s.length()) {
            stringLength = s.length();
            for (String str : hashSet) {
                s = s.replace(str, "");
            }
        }
        return s;
    }

    /* 1047.删除字符串中的所有相邻重复项 解法二：先进后出的栈的思想 */
    public String removeDuplicates2(String s) {
        ArrayList<Character> stack = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.add(0, c);
            } else {
                if (c == stack.get(0)) {
                    stack.remove(0);
                } else {
                    stack.add(0, c);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Character c : stack) {
            builder.append(c);
        }
        return builder.reverse().toString();
    }
}
