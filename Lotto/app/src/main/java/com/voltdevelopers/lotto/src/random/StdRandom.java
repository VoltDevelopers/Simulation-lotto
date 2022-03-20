package com.voltdevelopers.lotto.src.random;

import com.voltdevelopers.lotto.src.exception.InputException;

import java.util.Arrays;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class StdRandom {

    public static int[] getRandomArray(int num, int maxRandom) throws InputException {
        if (num < 1) {
            throw new InputException("The number is less than 1");
        }
        int[] array = new int[num];
        int val = 0;
        for (int i = 0; i < num; i++) {
            val = (int) ( new XoRoShiRo128PlusRandom().nextInt(maxRandom - 1) + 1);
            if (isAlreadyDrawn(array, val)) {
                i--;
            }else{
                array[i] = val;
            }
        }
        System.out.println("RANDOM ---> " + Arrays.toString(array));
        return array;
    }

    private static boolean isAlreadyDrawn(int[] bet, int val) {
        for (int j : bet) {
            if (j == val) {
                return true;
            }
        }
        return false;
    }
}
