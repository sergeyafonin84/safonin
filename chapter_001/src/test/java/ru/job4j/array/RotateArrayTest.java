package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RotateArrayTest {
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {

        int[][] testArray = {{1,2}, {3,4}};
        int[][] expected = {{3,1}, {4,2}};

        RotateArray rotateArray = new RotateArray();
        int[][]  result = rotateArray.rotate(testArray);
        assertThat(result, is(expected));
    }

    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {

        int[][] testArray = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] expected = {{7,4,1}, {8,5,2}, {9,6,3}};

        RotateArray rotateArray = new RotateArray();
        int[][]  result = rotateArray.rotate(testArray);
        assertThat(result, is(expected));
    }
}