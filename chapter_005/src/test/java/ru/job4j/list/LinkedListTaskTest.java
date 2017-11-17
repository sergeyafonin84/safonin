package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinkedListTaskTest {

    @Test
    public void whenHasCycleThenFuncHasCycleReturnTrue() {

        LinkedListTask linkedListTask = new LinkedListTask();

        boolean result = linkedListTask.hasCycle();

        boolean expected = true;

        assertThat(result, is(expected));

    }

}
