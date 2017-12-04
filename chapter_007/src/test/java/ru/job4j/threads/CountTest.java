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

    @Test
    public void testThreadsForWaiting() throws InterruptedException {

        String text = Count.getText();

        Thread thread1 = new Thread(new Count.ShowStart());
        Thread thread2 = new Thread(new Count.CountTheNumberOfWords(text));
        Thread thread3 = new Thread(new Count.CountTheNumberOfSpaces(text));
        Thread thread4 = new Thread(new Count.ShowFinish());

        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        thread3.start();

        try {
            thread3.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread4.start();

        try {
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}