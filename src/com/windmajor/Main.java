package com.windmajor;

import com.windmajor.question.Solution_001;
import com.windmajor.question.Solution_701;

public class Main {

    public static void main(String[] args) {

//        int[] result = new Solution_001().twoSum2(new int[]{2, 7, 11, 15}, 9);
//        System.out.println("result[0] = " + result[0]);
//        System.out.println("result[1] = " + result[1]);

        int result = new Solution_701().numJewelsInStones("z", "ZZ");
        System.out.println(result);
    }
}
