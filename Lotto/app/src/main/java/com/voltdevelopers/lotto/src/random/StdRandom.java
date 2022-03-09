package com.voltdevelopers.lotto.src.random;

import com.voltdevelopers.lotto.src.exception.InputException;

public class StdRandom {

    public static int[] getRandom (int num) throws InputException {
        if(num < 1){
            throw new InputException("The number is less than 1");
        }
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = (int) ( Math.random() * num );
        }
        return array;
    }
}
