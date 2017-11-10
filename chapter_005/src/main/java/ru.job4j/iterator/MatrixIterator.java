package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int index = 0;
    private int maxIndex = 0;

    public MatrixIterator(int[][] values) {

        int maxIndex = 0;

        this.values = values;

        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values[i].length; k++) {
                maxIndex = maxIndex + 1;
            }
        }

        this.maxIndex = maxIndex;
    }

    @Override
    public boolean hasNext() {
        return maxIndex > index;
    }

    @Override
    public Object next() {

        if (!this.hasNext()) {
                throw new NoSuchElementException();
        }

        int currentIndex = 0;
        int i = 0;
        int k = 0;

        for (i = 0; i < values.length;) {

            for (k = 0; k < values[i].length - 1; k++) {

                if (currentIndex == index) {

                    break;
                }

                currentIndex++;
            }

            if (currentIndex != index) {
                i++;
                k = -1;
                currentIndex++;
            } else {
                break;
            }
        }

        index++;
        return values[i][k];
    }
}