package com.oaktwister.services;

import java.util.Random;

public class Randomizer {

    public static final int ASCII_LOWERCASE_A = 97;
    public static final int ASCII_LOWERCASE_Z = 122;

    private static final Random random = new Random();

    public String randomAlphabeticString(int length) {
        return randomBoundedString(length, ASCII_LOWERCASE_A, ASCII_LOWERCASE_Z);
    }

    public String randomBoundedString(int length, int asciiMin, int asciiMax) {
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomInt = asciiMin + (int)(random.nextFloat() * (asciiMax - asciiMin + 1));
            buffer.append((char) randomInt);
        }
        return buffer.toString();
    }

    public double randomDouble(double min, double max) {
        return random.nextDouble() * (max - min + 1) + min;
    }

    public int randomInt(int min, int max) {
        return random.nextInt(min, max);
    }

}
