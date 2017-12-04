package ru.job4j.threads;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountTest {

    @Test
    public void test() {

        System.out.println("start");

        String text = Count.getText();

        new Thread(new Count.CountTheNumberOfWords(text)).start();

        new Thread(new Count.CountTheNumberOfSpaces(text)).start();

        System.out.println("finish");
    }
}