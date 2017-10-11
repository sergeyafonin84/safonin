package ru.job4j.array;
/**
 *  sorting an array by the method of permutation.
 *
 * @author Afonin Sergey (afonin1c@mail.ru)
 * @version 01
 * @since 11.10.2017
 */
public class BubbleSort {

    /**
     * Method sort sorting an array by the method of permutation.
     * @param array array to by sorted.
     * @return sorted array.
     */
    public int[] sort(int[] array) {

        boolean needToBeSorted = true;

        while (needToBeSorted) {

            needToBeSorted = false;

            for (int i = 0; i < array.length - 1; i++) {
               if (array[i] > array[i + 1]) {
                   needToBeSorted = true;
                   int rightElementOfArray = array[i + 1];
                   int leftElementOfArray = array[i];

                   array[i + 1] = leftElementOfArray;
                   array[i] = rightElementOfArray;

               }
            }

        }

        return array;

    }

}
