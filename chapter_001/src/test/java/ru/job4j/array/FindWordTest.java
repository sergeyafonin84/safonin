package ru.job4j.array;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindWordTest {

    @Test
    public void whenFindsInAnoterWord() {
        //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
        String testWord = "Привет";
        String subTestWord = "При";
        FindWord findWord = new FindWord();
        boolean result = findWord.FindSubWordInWord(testWord, subTestWord);
        boolean expected = true;
        assertThat(result, is(expected));
    }

}
