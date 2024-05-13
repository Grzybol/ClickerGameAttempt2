package com.example.clickergameattempt2.util;
import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();

    public static double randomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }
}
