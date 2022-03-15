package com.voltdevelopers.lotto.src.random;

import com.voltdevelopers.lotto.src.exception.InputException;

import java.util.Arrays;

public class StdRandom {

    public static int[] getRandomArray(int num, int maxRandom) throws InputException {
        if (num < 1) {
            throw new InputException("The number is less than 1");
        }
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = (int) ( 1 + Math.random() * maxRandom);
            System.out.println(Arrays.toString(array));
            if (isAlreadyDrawn(array, array[i])) {
                i--;
            }
        }
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
