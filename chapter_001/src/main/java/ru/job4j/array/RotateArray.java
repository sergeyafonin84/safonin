package ru.job4j.array;

public class RotateArray {

    public int[][] rotate(int[][] array) {

        int[][] rotatedArray = new int[array.length][array.length];

        for (int i = 0; i <= array.length-1; i++) {

            for (int k = 0; k <= array.length-1; k++) {

                rotatedArray[k][i] = array[array.length - 1 - i][k];

            }
        }

        return rotatedArray;

    }

}
